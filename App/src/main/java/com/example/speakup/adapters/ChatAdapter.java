package com.example.speakup.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.speakup.R;
import com.example.speakup.models.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<Message> messageList;
    private Activity activity;
    private Context context;
    private static TextToSpeech textToSpeech;

    public ChatAdapter(List<Message> messageList, Activity activity, Context context) {
        this.messageList = messageList;
        this.activity = activity;
        this.context = context;
    }

    @NonNull @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_message_one, parent, false);
        return new MyViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String message = messageList.get(position).getMessage();
        boolean isReceived = messageList.get(position).getIsReceived();
        if(isReceived){
            holder.messageReceive.setVisibility(View.VISIBLE);
            holder.messageSend.setVisibility(View.GONE);
            holder.messageReceive.setText(message);

            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                        String s = holder.messageReceive.getText().toString();
                        Log.d("Output", s);
                        String utteranceId = this.hashCode() + "";
                        Bundle params = new Bundle();
                        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
                        int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, params, utteranceId);
                    }
                }
            });
        }else {
            holder.messageSend.setVisibility(View.VISIBLE);
            holder.messageReceive.setVisibility(View.GONE);
            holder.messageSend.setText(message);

        }
    }

    @Override public int getItemCount() {
        return messageList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView messageSend;
        TextView messageReceive;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            messageSend = itemView.findViewById(R.id.message_send);
            messageReceive = itemView.findViewById(R.id.message_receive);
        }
    }

}
