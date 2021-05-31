package com.jde.chatup.LoginCredits.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.jde.chatup.ChatActivity;
import com.jde.chatup.FinalEnlarger;
import com.jde.chatup.Helper.MySwipeHelper;
import com.jde.chatup.Models.Main_Recycler_Child_Model;
import com.jde.chatup.R;
import com.jde.chatup.TempTestActibity;


import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main_Recycler_Child_Adapter extends RecyclerView.Adapter<Main_Recycler_Child_Adapter.MainRecyclerChildViewHolder> {
    private ArrayList<Main_Recycler_Child_Model> child_models_list;
    private Context context;


    public Main_Recycler_Child_Adapter(ArrayList<Main_Recycler_Child_Model> child_models_list, Context context) {
        this.child_models_list = child_models_list;
        this.context = context;
    }
    @NonNull
    @Override
    public MainRecyclerChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rec_inner_cont,parent,false);
        return new MainRecyclerChildViewHolder(view);}

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerChildViewHolder holder, int position) {
        Main_Recycler_Child_Model model = child_models_list.get(position);
        holder.circleImageView.setImageResource(model.getModel_Object_Profile());
        holder.HolderObjectName.setText(model.getModel_Object_Name());
        holder.HolderObjectLastMsg.setText(model.getModel_Object_Last_msg());
        holder.HolderUnreadCount.setText(String.valueOf(model.getModel_Object_unread_count()));
        holder.HolderCallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, ChatActivity.class);
                it.putExtra("itemThumbnail","#####");
                it.putExtra("itemChatName",model.getModel_Object_Name());
                context.startActivity(it);
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.AlertDialogTheme)
                .create();
        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(context).inflate(R.layout.iamge_enlarge_modal,null);
//                This is Outer Constrain Layout
                View vg = ((ViewGroup) view).getChildAt(0);
//                This is Inner Constrain Layout
                View ViewImage = ((ViewGroup) vg).getChildAt(0);
                View viewCard = ( ((ViewGroup) vg).getChildAt(1) );
                View viewLinear = ((ViewGroup) viewCard).getChildAt(0);

                View viewChat = ((ViewGroup) viewLinear).getChildAt(0);
                View viewCall = ((ViewGroup) viewLinear).getChildAt(1);
                View viewVDOCall = ((ViewGroup) viewLinear).getChildAt(2);
                View viewInfo = ((ViewGroup) viewLinear).getChildAt(3);


                ImageView imageVie = ((ImageView)ViewImage);
                LottieAnimationView modalChat = (LottieAnimationView)viewChat;
                LottieAnimationView modalCall = (LottieAnimationView)viewCall;
                LottieAnimationView modalVDOCall = (LottieAnimationView)viewVDOCall;
                LottieAnimationView modalInfo = (LottieAnimationView)viewInfo;

                imageVie.setImageResource(model.getModel_Object_Profile());
                alertDialog.setView(view);
                alertDialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alertDialog.getWindow().getAttributes());
                lp.width = 524;
                lp.height = 610;
                alertDialog.getWindow().setAttributes(lp);
//                Convert The Thumbnail to Drawable Resource
                imageVie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String URL = "https://source.unsplash.com/random";
                        Intent it = new Intent(context, FinalEnlarger.class);
                        it.putExtra("ImageURL",URL);
                        it.putExtra("ItemName",model.getModel_Object_Name());
                        it.putExtra("ImageThumbNail",model.getModel_Object_Profile());
                        it.putExtra("ActivityId","1234567890");
                        context.startActivity(it);
                        alertDialog.dismiss();
                    }
                });

                modalChat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(context, ChatActivity.class);
//                        This is The Call From Modal
                        it.putExtra("ActivityId","111111");
                        it.putExtra("itemThumbnail","#####");
                        it.putExtra("itemChatName",model.getModel_Object_Name());
                        context.startActivity(it);
                        alertDialog.dismiss();

                    }
                });


                modalCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Audio Call Here

                        alertDialog.dismiss();

                    }
                });

                modalVDOCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Video Call Here
                        URL serverURL = null;
                        try {
                            serverURL = new URL("https://meet.jit.si");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Sorry This Facility is Currnetly Not Available", Toast.LENGTH_SHORT).show();
                        }
                        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(serverURL)
                                .setRoom("CU1125sdg1d321gh5fd4h3df1df21hg23dfg35df4g")
                                .setAudioMuted(false)
                                .setVideoMuted(false)
                                .setAudioOnly(false)
                                .setWelcomePageEnabled(false)
                                .setFeatureFlag("add-people.enabled",false)
                                .setFeatureFlag("calendar.enabled",false)
                                .setFeatureFlag("call-integration.enabled",false)
                                .setFeatureFlag("audio-only.enabled",false)
                                .setFeatureFlag("video-share.enabled",false)
                                .setFeatureFlag("meeting-password.enabled",false)
                                .setFeatureFlag("meeting-name.enabled",false)
                                .setFeatureFlag("live-streaming.enabled",false)
                                .setFeatureFlag("recording.enabled",false)
                                .setFeatureFlag("invite.enabled",false)
                                .setFeatureFlag("add-people.enabled",false)
                                .setFeatureFlag("video-share.enabled",false)
                                .setFeatureFlag("close-captions.enabled",false)
                                .setFeatureFlag("filmstrip.enabled",false)
                                .setFeatureFlag("ios.recording.enabled",false)
                                .setFeatureFlag("overflow-menu.enabled",false)
                                .setFeatureFlag("kick-out.enabled",false)
                                .setFeatureFlag("help.enabled",false)
                                .setFeatureFlag("chat.enabled",false)
                                .setFeatureFlag("welcomepage.enabled",false)
                                .setFeatureFlag("",false)
                                .build();
                            JitsiMeetActivity.launch(context,options);
                        alertDialog.dismiss();
                    }
                });
                modalInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(context,TempTestActibity.class);
                        context.startActivity(it);
                        alertDialog.dismiss();
                    }
                });


            }
        });






    }

    @Override
    public int getItemCount() {return child_models_list.size();}

    public class MainRecyclerChildViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView HolderObjectName;
        TextView HolderObjectLastMsg;
        TextView HolderUnreadCount;
        LinearLayout HolderCallLayout;


        public MainRecyclerChildViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.profile_image);
            HolderObjectName = itemView.findViewById(R.id.obj_name);
            HolderObjectLastMsg = itemView.findViewById(R.id.obj_last_msg);
            HolderUnreadCount = itemView.findViewById(R.id.obj_unread_count);
            HolderCallLayout = itemView.findViewById(R.id.main_listener_layout);
        }
    }
}
