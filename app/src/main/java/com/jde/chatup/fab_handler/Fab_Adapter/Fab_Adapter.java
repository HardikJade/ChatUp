package com.jde.chatup.fab_handler.Fab_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jde.chatup.R;
import com.jde.chatup.fab_handler.Fab_Model.Fab_List_Model;

import java.util.ArrayList;

public class Fab_Adapter extends RecyclerView.Adapter<Fab_Adapter.FabViewHolder> {
    private Context context;
    private ArrayList<Fab_List_Model> list;

    //**********************************************************************

    public Fab_Adapter(Context context, ArrayList<Fab_List_Model> list) {
        this.context = context;
        this.list = list;
    }



    //**********************************************************************

    @NonNull
    @Override
    public FabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fab_child_item,parent,false);
        return new FabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FabViewHolder holder, int position) {
        Fab_List_Model model = list.get(position);
        holder.fab_imageView.setImageResource(model.getFab_profile());
        holder.fab_name.setText(model.getFab_Obj_name());
        holder.fab_status.setText(model.getFab_obj_status());
    }

    @Override
    public int getItemCount() {return list.size();}
    //**********************************************************************

    //******************************************************
    public class FabViewHolder extends RecyclerView.ViewHolder {
        ImageView fab_imageView;
        TextView fab_name;
        TextView fab_status;
        public FabViewHolder(@NonNull View itemView) {
            super(itemView);
            fab_imageView = itemView.findViewById(R.id.fab_profile_image);
            fab_name = itemView.findViewById(R.id.fab_obj_name);
            fab_status = itemView.findViewById(R.id.fab_obj_status);
        }
    }
    //******************************************************
}
