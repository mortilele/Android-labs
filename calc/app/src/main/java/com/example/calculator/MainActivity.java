package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.spec.ECField;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView NumberInput;

    private Button buttonNumber1;
    private Button buttonNumber2;
    private Button buttonNumber3;
    private Button buttonNumber4;
    private Button buttonNumber5;
    private Button buttonNumber6;
    private Button buttonNumber7;
    private Button buttonNumber8;
    private Button buttonNumber9;
    private Button buttonNumber0;
    private Button buttonDot;



    private Button buttonPlus;
    private Button buttonEqual;
    private Button buttonMinus;
    private Button buttonMul;
    private Button buttonDiv;
    private Button buttonRoot;
    private Button buttonPOW;
    private Button buttonFACTORIAL;
    private Button buttonPERCENT;
    private Button buttonNumberE;
    private Button buttonLog;
    private Button buttonSIN;
    private Button buttonCOS;
    private Button buttonTAN;
    private Button buttonClEAR;
    private Button buttonD;




    private double number1;
    private double number2;

    private double result2;

    private double eq;
    private double dot;
    private double check;
    long factorial = 1;



    enum Sign {
        PLUS, MINUS, MUL, DIV, POW
    }
    private Sign sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lifecycle", "onCreate");

        NumberInput = findViewById(R.id.NumberInput);

        buttonNumber1 = findViewById(R.id.buttonNumber1);
        buttonNumber2 = findViewById(R.id.buttonNumber2);
        buttonNumber3 = findViewById(R.id.buttonNumber3);
        buttonNumber4 = findViewById(R.id.buttonNumber4);
        buttonNumber5 = findViewById(R.id.buttonNumber5);
        buttonNumber6 = findViewById(R.id.buttonNumber6);
        buttonNumber7 = findViewById(R.id.buttonNumber7);
        buttonNumber8 = findViewById(R.id.buttonNumber8);
        buttonNumber9 = findViewById(R.id.buttonNumber9);
        buttonNumber0 = findViewById(R.id.buttonNumber0);
        buttonDot = findViewById(R.id.buttonDot);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonRoot = findViewById(R.id.buttonROOT);
        buttonPOW = findViewById(R.id.buttonPOW);
        buttonFACTORIAL = findViewById(R.id.buttonFACTORIAL);
        buttonPERCENT = findViewById(R.id.buttonPERCENT);
        buttonNumberE = findViewById(R.id.buttonNumberE);
        buttonLog = findViewById(R.id.buttonLOG);
        buttonSIN = findViewById(R.id.buttonSIN);
        buttonCOS = findViewById(R.id.buttonCOS);
        buttonTAN = findViewById(R.id.buttonTAN);
        buttonClEAR = findViewById(R.id.buttonCLEAR);
        buttonD = findViewById(R.id.buttonD);
        buttonDot = findViewById(R.id.buttonDot);


        buttonNumber1.setOnClickListener(this);
        buttonNumber2.setOnClickListener(this);
        buttonNumber3.setOnClickListener(this);
        buttonNumber4.setOnClickListener(this);
        buttonNumber5.setOnClickListener(this);
        buttonNumber6.setOnClickListener(this);
        buttonNumber7.setOnClickListener(this);
        buttonNumber8.setOnClickListener(this);
        buttonNumber9.setOnClickListener(this);
        buttonNumber0.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonRoot.setOnClickListener(this);
        buttonPOW.setOnClickListener(this);
        buttonFACTORIAL.setOnClickListener(this);
        buttonPERCENT.setOnClickListener(this);
        buttonNumberE.setOnClickListener(this);
        buttonLog.setOnClickListener(this);
        buttonSIN.setOnClickListener(this);
        buttonCOS.setOnClickListener(this);
        buttonTAN.setOnClickListener(this);
        buttonClEAR.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonDot.setOnClickListener(this);

        if(savedInstanceState != null){
            number1 = savedInstanceState.getDouble("number1");
            number2 = savedInstanceState.getDouble("number2");
            result2 = savedInstanceState.getDouble("result2");
            NumberInput.setText(savedInstanceState.getString("numberInput"));

        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("NumberInput", NumberInput.getText().toString());
        outState.putDouble("number1", number1);
        outState.putDouble("number2", number2);
        outState.putDouble("result2", result2);

        Log.d("lifecycle", "onSaveInstanceState " + result2);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("NumberInput");
        savedInstanceState.getString("number1");
        savedInstanceState.getString("number2");
        savedInstanceState.getString("result2");

        Log.d("lifecycle", "onRestoreInstanceState " + result2);
        NumberInput.setText(String.valueOf(result2));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart");
    }


    public void CheckLength(){
        if (NumberInput.length()>8){
            check=0;
        }else{
            check=1;
        }
    }

    public boolean isValid() {
        return NumberInput.getText().toString().equals("Wrong") || NumberInput.getText().toString().equals("NaN") || NumberInput.getText().toString().equals("Infinity");
    }

    public boolean isEmpty() {
        return  NumberInput.length() < 1 || eq==1;
    }

    @Override
    public void onClick(View view){
        CheckLength();
        if(isValid()){
            NumberInput.setText("");
        }
        switch (view.getId()) {
            case R.id.buttonNumber1:{
                if (check==1) {
                    NumberInput.append("1");
                }
                break;
            }
            case R.id.buttonNumber2:{
                if (check==1) {
                    NumberInput.append("2");
                }
                break;
            }
            case R.id.buttonNumber3:{
                if (check==1) {
                    NumberInput.append("3");
                }

                break;
            }
            case R.id.buttonNumber4:{
                if (check==1) {
                    NumberInput.append("4");
                }
                break;
            }
            case R.id.buttonNumber5:{
                if (check==1) {
                    NumberInput.append("5");
                }
                break;
            }
            case R.id.buttonNumber6:{
                if (check==1) {
                    NumberInput.append("6");
                }
                break;
            }
            case R.id.buttonNumber7:{
                if (check==1) {
                    NumberInput.append("7");
                }
                break;
            }
            case R.id.buttonNumber8:{
                if (check==1) {
                    NumberInput.append("8");
                }
                break;
            }
            case R.id.buttonNumber9:{
                if (check==1) {
                    NumberInput.append("9");
                }
                break;
            }
            case R.id.buttonNumber0:{
                if (check==1) {
                    NumberInput.append("0");
                }
                break;
            }

            case R.id.buttonDot:{
                if (dot!=1) {
                    NumberInput.append(".");
                    dot = 1;
                }
                break;
            }
            case R.id.buttonD:{
                if (isEmpty()) {
                    break;
                }

                NumberInput.setText(NumberInput.getText().toString().substring(0, NumberInput.getText().toString().length() - 1));
                break;
            }

            case R.id.buttonCLEAR:{
                NumberInput.setText("");
                number1 = 0;
                number2 = 0;
                result2 = 0;
                break;
            }


            case R.id.buttonPlus:{
                if (isEmpty()) {
                    break;
                }
                if ( dot==1) {
                    NumberInput.append("0");
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                NumberInput.setText("");
                sign = Sign.PLUS;
                break;
            }
            case R.id.buttonMinus: {
                if (isEmpty()) {
                    break;
                }
                if ( dot==1) {
                    NumberInput.append("0");
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                NumberInput.setText("");
                sign = Sign.MINUS;
                break;
            }
            case R.id.buttonMul:{
                if (isEmpty()) {
                    break;
                }
                if ( dot==1) {
                    NumberInput.append("0");
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                NumberInput.setText("");
                sign = Sign.MUL;
                break;
            }
            case R.id.buttonDiv:{
                if (isEmpty()) {
                    break;
                }
                eq = 0;
                if ( dot == 1) {
                    NumberInput.append("0");
                }
                dot = 0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                NumberInput.setText("");
                sign = Sign.DIV;
                break;

            }
            case R.id.buttonROOT:{
                if (isEmpty()) {
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                if (number1<0){
                    NumberInput.setText("Wrong");
                    break;
                }
                number1 = Math.sqrt(number1);
                if((number1 == Math.floor(number1)) && !Double.isInfinite(number1)) {
                    NumberInput.setText(String.valueOf((int)(number1)));
                }else {
                    NumberInput.setText(String.valueOf((Math.floor((number1) * 1000000)) / (1000000)));
                }
                break;
            }
            case R.id.buttonPOW: {
                if (isEmpty()) {
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                NumberInput.setText("");
                sign = Sign.POW;
                break;
            }
            case R.id.buttonFACTORIAL:{
                if (isEmpty()) {
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                NumberInput.setText(String.valueOf(calcFactorial()));
                break;
            }
            case R.id.buttonPERCENT:{
                if (NumberInput.length()<1 || eq==1){
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                result2 = number1 / 100;
                NumberInput.setText(String.valueOf(result2));
                break;
            }
            case R.id.buttonNumberE:{
                if (isEmpty()) {
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                double e = 2.27;
                result2 = Math.pow(e, number1);
                NumberInput.setText(String.valueOf(result2));
                break;
            }
            case R.id.buttonLOG:{
                if (NumberInput.length()<1 || eq==1){
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());
                result2 = Math.log(number1);
                NumberInput.setText(String.valueOf(result2));
                break;
            }
            case R.id.buttonSIN:{
                if (NumberInput.length()<1 || eq==1){
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());

                result2 = Math.sin(Math.toRadians(number1));
                    NumberInput.setText(String.valueOf(result2));
                    break;

            }

            case R.id.buttonCOS:{
                if (NumberInput.length()<1 || eq==1){
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());

                result2 = Math.cos(Math.toRadians(number1));
                NumberInput.setText(String.valueOf(result2));
                break;
            }
            case R.id.buttonTAN:{
                if (NumberInput.length()<1 || eq==1){
                    break;
                }
                dot=0;
                number1 = Double.parseDouble(NumberInput.getText().toString());

                result2 = Math.tan(Math.toRadians(number1));
                NumberInput.setText(String.valueOf(result2));
                break;
            }

            case R.id.buttonEqual:{
                if(isValid()) {
                    NumberInput.setText("");
                    break;
                }
                if (dot==1) {
                    NumberInput.append("0");
                }
                dot=0;
                if (NumberInput.length()<1 || eq==1){
                    eq = 0;
                    break;
                }
                eq = 0;
                number2 = Double.parseDouble(NumberInput.getText().toString());



                if(sign == Sign.PLUS) {
                    result2 = number1 + number2;
                }
                else if(sign == Sign.MINUS) {
                    result2 = number1 - number2;
                }
                else if(sign == Sign.MUL) {
                    result2 = number1 * number2;
                }
                else if (sign == Sign.POW) {
                    result2 = Math.pow(number1, number2);
                }
                else if (sign == Sign.DIV){
                    result2 = number1 / number2;
                }
                if((result2 == Math.floor(result2)) && !Double.isInfinite(result2)) {
                    NumberInput.setText(String.valueOf((int) result2));
                    if(NumberInput.getText().toString().equals("NaN")){
                        NumberInput.setText("");
                    }
                    else if(NumberInput.getText().toString().equals("Infinity")){
                        NumberInput.setText("");
                    }
                }else {
                    NumberInput.setText(String.valueOf((Math.floor(result2 * 1000000)) / (1000000)));
                }
                break;

            }

        }
    }



    private long calcFactorial(){
        long factorial = 1;
        for(int i = 1; i<=number1; i++){
            factorial = i * factorial;
        }
        return factorial;
    }

}
