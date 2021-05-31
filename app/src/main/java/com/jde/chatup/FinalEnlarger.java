package com.jde.chatup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class FinalEnlarger extends AppCompatActivity {
    private String ItemName = "####";
    private int ItemThumbnail;
    private String ItemLoadURL = "#";
    private String ActivityId;
    private TextView ItemNameView;
    private ZoomageView itemZoomView;
    private ImageButton BackBtn;
    private OvershootInterpolator interpolator = new OvershootInterpolator();
    private LottieAnimationView spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_enlarger);
        ItemNameView = findViewById(R.id.profileViewtext);
        itemZoomView = findViewById(R.id.profileViewerZoomage);
        BackBtn = findViewById(R.id.profileViewerBack);
        spinner = findViewById(R.id.loadSpinnerHere);

        if(getIntent().hasExtra("ActivityId")){
            ActivityId = getIntent().getStringExtra("ActivityId");
            ItemName = getIntent().getStringExtra("ItemName");
            ItemThumbnail = getIntent().getIntExtra("ImageThumbNail",R.drawable.model_face);
            ItemLoadURL = getIntent().getStringExtra("ImageURL");
            ItemNameView.setText(ItemName);
            if(ActivityId.equals("1234567890")){
                ItemName = getIntent().getStringExtra("ItemName");
                ItemThumbnail = getIntent().getIntExtra("ImageThumbNail",R.drawable.model_face);
                ItemLoadURL = getIntent().getStringExtra("ImageURL");
                ItemNameView.setText(ItemName);
                Picasso.get()
                        .load(ItemLoadURL)
                        .placeholder(ItemThumbnail)
                        .into(itemZoomView, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                spinner.animate().setInterpolator(interpolator).alpha(0).setDuration(200).start();
                                spinner.setVisibility(View.INVISIBLE);
                            }
                            @Override
                            public void onError(Exception e) {
                                spinner.animate().setInterpolator(interpolator).alpha(0).setDuration(200).start();
                                spinner.setVisibility(View.INVISIBLE);
                                Toast.makeText(FinalEnlarger.this, "Failed To Load The Profile Pic", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
//                When Application Id Does Not Match
            }
        }else{
            //This has Came From Out Of App
        }

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}