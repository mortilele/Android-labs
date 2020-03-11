package com.example.fragmentlab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentlab.data.model.Mail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailViewHolder> {

    private List<Mail> mails;

    private MailItemClickListener listener;

    public MailAdapter(List<Mail> mails, @Nullable MailItemClickListener listener) {
        this.mails =mails;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mail_item, parent, false);
        return new MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MailViewHolder holder, final int position) {
        final Mail mail = mails.get(position);
        holder.from.setText(mail.from);
        holder.title.setText(mail.title);
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        holder.date.setText(df.format(mail.date));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, mail);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mails.size();
    }

    public class MailViewHolder extends RecyclerView.ViewHolder {
        TextView from;
        TextView title;
        TextView content;
        TextView date;
        ImageView avatar;
        public MailViewHolder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.mail_list_from);
            title = itemView.findViewById(R.id.mail_list_title);
            date = itemView.findViewById(R.id.mail_list_date);
            avatar = itemView.findViewById(R.id.mail_list_avatar);
        }
    }

    interface MailItemClickListener {
        void itemClick(int position, Mail mail);
    }
}
