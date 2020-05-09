import json
import time
import uuid
from threading import Thread

import pika
import pygame

IP = '34.254.177.17'
PORT = 5672
VIRTUAL_HOST = 'dar-tanks'
USERNAME = 'dar-tanks'
PASSWORD = '5orPLExUYnyVYZg48caMpX'

pygame.init()
screen = pygame.display.set_mode((1000, 800))
font = pygame.font.Font('freesansbold.ttf', 32)


def serialize(body):
    return json.dumps(body)


def deserialize(data):
    return json.loads(data)


class TankRpcClient:

    def __init__(self):
        self.connection = pika.BlockingConnection(
            pika.ConnectionParameters(
                host=IP,
                port=PORT,
                virtual_host=VIRTUAL_HOST,
                credentials=pika.PlainCredentials(
                    username=USERNAME,
                    password=PASSWORD
                )
            )
        )
        self.channel = self.connection.channel()
        queue = self.channel.queue_declare(
            queue='',
            exclusive=True,
            auto_delete=True
        )
        self.callback_queue = queue.method.queue
        self.channel.queue_bind(
            exchange='X:routing.topic',
            queue=self.callback_queue
        )

        self.channel.basic_consume(
            queue=self.callback_queue,
            on_message_callback=self.on_response,
            auto_ack=False)

        self.corr_id = None
        self.response = None
        self.tank_id = None
        self.token = None
        self.room_id = None

    def on_response(self, ch, method, props, body):
        if self.corr_id == props.correlation_id:
            self.response = deserialize(body)

    def call(self, key, message={}):
        if self.token:
            message.update(token=self.token)
        self.response = None
        self.corr_id = str(uuid.uuid4())
        self.channel.basic_publish(
            exchange='X:routing.topic',
            routing_key=key,
            properties=pika.BasicProperties(
                reply_to=self.callback_queue,
                correlation_id=self.corr_id,
            ),
            body=serialize(message)
        )
        while self.response is None:
            self.connection.process_data_events()

    def check_server_status(self):
        self.call('tank.request.healthcheck')
        print(self.response['message'])

    def obtain_token(self, room_id):
        message = {
            'roomId': room_id
        }
        self.call('tank.request.register', message)
        if 'token' in self.response:
            self.token = self.response['token']
            self.tank_id = self.response['tankId']
            self.room_id = self.response['roomId']
            print('Tank registered', self.tank_id)

    def turn_tank(self, direction):
        data = {
            'direction': direction
        }
        self.call('tank.request.turn', data)
        print('Tank turned: ', self.response)


class TankConsumerClient(Thread):

    def __init__(self, current_room='room-5'):
        super().__init__()
        self.connection = pika.BlockingConnection(
            pika.ConnectionParameters(
                host=IP,
                port=PORT,
                virtual_host=VIRTUAL_HOST,
                credentials=pika.PlainCredentials(
                    username=USERNAME,
                    password=PASSWORD
                )
            )
        )

        self.response = None
        self.channel = self.connection.channel()
        result = self.channel.queue_declare(queue='',
                                            exclusive=True,
                                            auto_delete=True)
        event_listener = result.method.queue
        self.channel.queue_bind(exchange='X:routing.topic',
                                queue=event_listener,
                                routing_key='event.state.{}'.format(current_room))

        self.channel.basic_consume(
            queue=event_listener,
            on_message_callback=self.on_response,
            auto_ack=False)

    def on_response(self, ch, method, props, body):
        self.response = deserialize(body)

    def run(self):
        self.channel.start_consuming()


UP = 'UP'
LEFT = 'LEFT'
RIGHT = 'RIGHT'
DOWN = 'DOWN'

MOVE_KEYS = {
    pygame.K_w: UP,
    pygame.K_a: LEFT,
    pygame.K_d: RIGHT,
    pygame.K_s: DOWN,
}


def draw_tank(client_tank_id, x, y, width, height, direction, **kwargs):
    tank_c = (x + int(width / 2), y + int(width / 2))
    pygame.draw.rect(screen, (255, 0, 0), (x, y, width, height), 2)
    pygame.draw.circle(screen, (0, 255, 255), tank_c, int(width / 2))


class Button:

    def __init__(self, text, x, y, width, height, func):
        self.x = x
        self.y = y
        self.width = width
        self.height = height
        self.execute = func
        self.text = text

    def draw(self):
        font = pygame.font.Font('freesansbold.ttf', 32)
        text = font.render('GeeksForGeeks', True, (255, 255, 255))
        textRect = text.get_rect()
        textRect.center = (1000 // 2, 800 // 2)
        screen.blit(text, textRect)


def execute():
    print('execute button')


def game_start():
    button = Button('asd', 500, 400, 100, 100, execute)
    mainloop = True
    while mainloop:
        screen.fill((0, 0, 0))
        pos = pygame.mouse.get_dpos()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                mainloop = False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE:
                    mainloop = False
                if event.key in MOVE_KEYS:
                    client.turn_tank(MOVE_KEYS[event.key])
            if event.type == pygame.MOUSEBUTTONDOWN:
                print(pos)
                if button.x <= pos[0] <= button.x + button.width and \
                        button.y <= pos[1] <= button.y + button.height:
                    print('in execute')
                    button.execute()
        button.draw()
        if listener.response:
            for tank in listener.response['gameField']['tanks']:
                draw_tank(client.tank_id, **tank)
            text = font.render('Remaining time: {}'.format(listener.response['remainingTime']), True, (255, 255, 255))
            textRect = text.get_rect()
            textRect.center = (1000 // 2, 600 // 2)
            screen.blit(text, textRect)
        pygame.display.flip()

    pygame.quit()


client = TankRpcClient()
client.check_server_status()
client.obtain_token('room-5')
if client.token:
    listener = TankConsumerClient(client.room_id)
    listener.start()
    game_start()
