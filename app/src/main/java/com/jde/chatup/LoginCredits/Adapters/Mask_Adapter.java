package com.jde.chatup.LoginCredits.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jde.chatup.Models.Main_Recycler_Parent_Model;
import com.jde.chatup.R;

import java.util.ArrayList;

public class Mask_Adapter extends RecyclerView.Adapter<Mask_Adapter.MaskViewHolder> {
    private Context context;
    private ArrayList<Main_Recycler_Parent_Model> list;

    public Mask_Adapter(Context context, ArrayList<Main_Recycler_Parent_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mask_inner_child,parent,false);
        return new MaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaskViewHolder holder, int position) {
        holder.linearLayout.setBackgroundColor(Color.parseColor("#ADEBBE"));
    }

    @Override
    public int getItemCount() {return list.size();}



    public class MaskViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        public MaskViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.mask_child_item);

        }
    }
}
