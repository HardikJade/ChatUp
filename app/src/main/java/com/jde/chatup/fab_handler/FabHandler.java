package com.jde.chatup.fab_handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.jde.chatup.R;
import com.jde.chatup.fab_handler.Fab_Adapter.Fab_Adapter;
import com.jde.chatup.fab_handler.Fab_Model.Fab_List_Model;
import com.trendyol.bubblescrollbarlib.BubbleScrollBar;
import com.trendyol.bubblescrollbarlib.BubbleTextProvider;

import java.util.ArrayList;

public class FabHandler extends AppCompatActivity {

    ArrayList<Fab_List_Model> list = new ArrayList<Fab_List_Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_handler);


        initData();

        RecyclerView fab_recyclerView = findViewById(R.id.fab_rec_view);
        Fab_Adapter adapter = new Fab_Adapter(this,list);
        fab_recyclerView.setAdapter(adapter);
        BubbleScrollBar bubbleScrollBar = findViewById(R.id.fab_scroll);
        bubbleScrollBar.attachToRecyclerView(fab_recyclerView);
        bubbleScrollBar.setBubbleTextProvider(new BubbleTextProvider() {
            @Override
            public String provideBubbleText(int i) {
                return new StringBuilder(list.get(i).getFab_Obj_name().substring(0,1)).toString();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        fab_recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));
        list.add(new Fab_List_Model(R.drawable.model_face,"Rebecca Kurein","Felling Happy"));

    }
}