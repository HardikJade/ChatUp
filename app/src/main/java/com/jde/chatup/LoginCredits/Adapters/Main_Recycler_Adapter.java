package com.jde.chatup.LoginCredits.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jde.chatup.ChatActivity;
import com.jde.chatup.Helper.MySwipeHelper;
import com.jde.chatup.Models.Main_Recycler_Parent_Model;
import com.jde.chatup.R;

import java.util.ArrayList;
import java.util.List;

public class Main_Recycler_Adapter extends RecyclerView.Adapter<Main_Recycler_Adapter.MainRecyclerViewHolder> {
    private ArrayList<Main_Recycler_Parent_Model> parent_models_list;
    private Context context;

    public Main_Recycler_Adapter(ArrayList<Main_Recycler_Parent_Model> parent_models_list, Context context) {
        this.parent_models_list = parent_models_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MainRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_parent_recycler_lay,parent,false);
        return new MainRecyclerViewHolder(view);}

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewHolder holder, int position) {
        Main_Recycler_Parent_Model model_ = parent_models_list.get(position);
        holder.HolderTextView.setText(model_.getModel_head_text());

        Main_Recycler_Child_Adapter adapter = new Main_Recycler_Child_Adapter(model_.getMain_recycler_child_model(),context);
        holder.HolderRecyclerView.setAdapter(adapter);
        LinearLayoutManager child_LinearLayoutManager = new LinearLayoutManager(context);
        holder.HolderRecyclerView.setLayoutManager(child_LinearLayoutManager);




        MySwipeHelper swipeHelper = new MySwipeHelper(context,holder.HolderRecyclerView,65) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new MySwipeHelper.UnderlayButton(
                        context,
                        "",
                        0,

                        R.drawable.ic_outline_delete_24,
                        Color.parseColor("#ADEBBE"),
                        true,
                        new MySwipeHelper.ButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();

                            }
                        }
                ));
                underlayButtons.add(new MySwipeHelper.UnderlayButton(
                                        context,
                                        "",
                                        0,
                                        R.drawable.ic_outline_volume_mute_24,
                                        Color.parseColor("#ADEBBE"),
                                        false,
                                        new MySwipeHelper.ButtonClickListener() {
                                            @Override
                                            public void onClick(int pos) {
                                                // TODO: onDelete
                                                Toast.makeText(context, "Mute", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                ));
                underlayButtons.add(new MySwipeHelper.UnderlayButton(
                                        context,
                                        "",
                                        0,
                                        R.drawable.ic_outline_push_pin_24,
                                        Color.parseColor("#ADEBBE"),
                                        false,
                                        new MySwipeHelper.ButtonClickListener() {
                                            @Override
                                            public void onClick(int pos) {
                                                // TODO: onDelete
                                                Toast.makeText(context, "Pin", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                ));
            }
        };
    }




    @Override
    public int getItemCount() {return parent_models_list.size();}

    public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView HolderTextView;
        private RecyclerView HolderRecyclerView;

        public MainRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            HolderTextView = itemView.findViewById(R.id.main_rec_head_text);
            HolderRecyclerView = itemView.findViewById(R.id.main_rec_inner_rec);
        }

    }

}
