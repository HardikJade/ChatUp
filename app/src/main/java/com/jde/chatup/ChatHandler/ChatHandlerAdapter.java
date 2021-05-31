package com.jde.chatup.ChatHandler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jde.chatup.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatHandlerAdapter extends RecyclerView.Adapter {
    public static final int SNEEK_PEEK = 0;
    public static final int CHAT_SEND = 1;
    public static final int CHAT_RECIEVE = 2;

    private int lastPos = -1;
    //Constructor Here
    //********************************************************
    private ArrayList<ChatModel> list;
    private Context context;
    public ChatHandlerAdapter(ArrayList<ChatModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    //********************************************************

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getViewType()){
            case SNEEK_PEEK:
                return SNEEK_PEEK;
            case CHAT_SEND:
                return CHAT_SEND;
            case CHAT_RECIEVE:
                return CHAT_RECIEVE;
            default:
                return -1;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case SNEEK_PEEK:
                View chat_SneekPeek = LayoutInflater.from(context).inflate(R.layout.chat_activity_sneek_peek,parent,false);
                return new SneekPeekClass(chat_SneekPeek);
            case CHAT_SEND:
                View chat_sendView = LayoutInflater.from(context).inflate(R.layout.chat_send,parent,false);
                return new ChatSendClass(chat_sendView);
            case CHAT_RECIEVE:
                View chat_ReciveView = LayoutInflater.from(context).inflate(R.layout.chat_recieve,parent,false);
                return new ChatRecieveClass(chat_ReciveView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getViewType()){
            case SNEEK_PEEK:
                ((SneekPeekClass) holder).SetData(list.get(position).getSneekPeekMatter());
                break;
            case CHAT_SEND:
                ((ChatSendClass) holder).SetData(
                        list.get(position).getChatSendMatter(),
                        list.get(position).getSendingState(),
                        list.get(position).getChatSendTime(),
                        list.get(position).isChatSendErr());
                break;
            case CHAT_RECIEVE:
                ((ChatRecieveClass)holder).SetData(list.get(position).getChatReciveMatter(),
                        list.get(position).getRecieveTime());
                break;
            default:
                return;
        }

//        Adding Animation on View

        if(lastPos < position){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {return list.size();}


    class SneekPeekClass extends RecyclerView.ViewHolder{
        private TextView sneekPeekMatter;
        public SneekPeekClass(@NonNull View itemView) {
            super(itemView);
            this.sneekPeekMatter = itemView.findViewById(R.id.sneekPeekText);
        }
        public void SetData(String sneekPeekText){this.sneekPeekMatter.setText(sneekPeekText);}

    }
    class ChatSendClass extends RecyclerView.ViewHolder {
        private TextView chatSendMatter;
        private ImageView sendingState;
        private TextView chatSendTime;
        private ImageView ErrView;

        private static final int SENT = 0;
        private static final int DELIVERED = 1;
        private static final int READ = 2;


        public ChatSendClass(@NonNull View itemView) {
            super(itemView);
            chatSendMatter = itemView.findViewById(R.id.chatSendMatter);
            sendingState = itemView.findViewById(R.id.sendingState);
            chatSendTime = itemView.findViewById(R.id.chat_send_time);
            ErrView = itemView.findViewById(R.id.errView);
        }
        public void SetData(String sendingMatter,int sendingState,String chatSendTime,boolean Err){
            this.chatSendMatter.setText(sendingMatter);
            this.chatSendTime.setText(chatSendTime);
            switch (sendingState){
                case SENT:
                    this.sendingState.setImageResource(R.drawable.done);
                    break;
                case DELIVERED:
                    //Change The Item Here
                    this.sendingState.setImageResource(R.drawable.done);
                    break;
                case READ:
                    //Change The Item Here
                    this.sendingState.setImageResource(R.drawable.done);
                    break;
                default:
                    break;
            }
            if(Err){this.ErrView.setImageResource(R.drawable.cancel);
                try {this.ErrView.setVisibility(View.VISIBLE);}
                catch (Exception E){}finally {}
            }
            else{this.ErrView.setVisibility(View.GONE);}
        }
    }
    class ChatRecieveClass extends RecyclerView.ViewHolder{
        private TextView chatRecieveMatter;
        private TextView chatRecieveTime;
        public ChatRecieveClass(@NonNull View itemView) {
            super(itemView);
            chatRecieveMatter = itemView.findViewById(R.id.chat_recieve_matter);
            chatRecieveTime = itemView.findViewById(R.id.chat_recieve_time);
        }
        public void SetData(String RecieveMatter,String RecieveTime){
            this.chatRecieveMatter.setText(RecieveMatter);
            this.chatRecieveTime.setText(RecieveTime);
        }
    }
}
