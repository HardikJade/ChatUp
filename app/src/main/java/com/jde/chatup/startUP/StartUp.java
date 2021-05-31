package com.jde.chatup.startUP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jde.chatup.MainActivity;
import com.jde.chatup.R;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

//        Add The Code For The Splash Screen Here

        Thread th = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(2000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent it = new Intent(StartUp.this,MainActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        };th.start();

    }

}