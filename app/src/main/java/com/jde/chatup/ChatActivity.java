package com.jde.chatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jde.chatup.ChatHandler.ChatHandlerAdapter;
import com.jde.chatup.ChatHandler.ChatModel;

import java.util.ArrayList;

import static com.jde.chatup.R.layout.chat_pin_holder;

public class ChatActivity extends AppCompatActivity {

    private ImageButton chatActPin;
    private ImageView chatActSmile;
    private ImageButton chatActSend;

    private RecyclerView mainRecyclerView;
    private ArrayList<ChatModel> list = new ArrayList<ChatModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatActPin = findViewById(R.id.chatactPin);
        chatActSmile = findViewById(R.id.chatActSmile);
        chatActSend = findViewById(R.id.chatActSend);
        View view = LayoutInflater.from(this).inflate(chat_pin_holder,null);
        mainRecyclerView = findViewById(R.id.chatActivitRecyclerParent);
        
        initData();
        ChatHandlerAdapter adapter = new ChatHandlerAdapter(list,this);
        mainRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(linearLayoutManager);

        AlertDialog alertDialog = new AlertDialog.Builder(this,R.style.AlertDialogTheme)
                .create();
        alertDialog.setView(view);
        chatActPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

    }

    private void initData() {
        //Add The Dynamic List Here
        list.add(new ChatModel(0,"Welcome To Chat Up"));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",true));
        list.add(new ChatModel(1,"Hello How Are You?",0,"12:00am",false));

        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));
        list.add(new ChatModel(2,"I am Fine...","1:00am"));

    }
}