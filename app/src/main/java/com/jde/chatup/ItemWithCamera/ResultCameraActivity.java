package com.jde.chatup.ItemWithCamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.BitmapCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jde.chatup.CropImageHelper.CropImageActivity;
import com.jde.chatup.CropImageHelper.HelperHelper;
import com.jde.chatup.MainActivity;
import com.jde.chatup.R;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultCameraActivity extends AppCompatActivity {
    private ZoomageView getParcel;
    private String cropSendURL = null;
    private Bitmap cachedImage;
    private FloatingActionButton check_btn;
    private File file;
    private File CU_compressedFinal;
    private ImageView preview_image_crop;
    private ImageView delete_btn;
    private ImageView back_btn;
    private ImageView qualityManager;
    private SeekBar qualitySeekBar;
    private float TRANSLATIONY = -1000f;
    private OvershootInterpolator interpolator;
    private boolean isSeekBarOpen = false;
    private TextView seekProgressCount;
    private TextView imageSize;
    private Bitmap compressedImage;
    private String parentDir;
    private int SEEK_BAR_PROGRESS = 100;
    private Matrix scaleSetter = new Matrix();
    private float scaleFactor = 1f;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_camera);

        getParcel = findViewById(R.id.return_capture_preview);
        String currentPhotoPath = getIntent().getStringExtra("BitMapImage");
        cropSendURL = currentPhotoPath;
        compressedImage = BitmapFactory.decodeFile(currentPhotoPath);

        check_btn = findViewById(R.id.live_capture_here);
        preview_image_crop = findViewById(R.id.preview_crop_image_item);
        delete_btn = findViewById(R.id.preview_delete);
        back_btn = findViewById(R.id.preview_back);
        qualityManager = findViewById(R.id.preview_qulity);
        qualitySeekBar = findViewById(R.id.quality_seekBar);
        interpolator = new OvershootInterpolator();
        seekProgressCount = findViewById(R.id.seekBarValue);
        imageSize = findViewById(R.id.imageSize);


        qualitySeekBar.setTranslationY(TRANSLATIONY);
        qualitySeekBar.setAlpha(0);
        qualitySeekBar.setVisibility(View.INVISIBLE);
        qualitySeekBar.setProgress(SEEK_BAR_PROGRESS);
        qualitySeekBar.incrementProgressBy(10);
        ConstraintLayout constraintLayout =  findViewById(R.id.result_mainParent);

        qualitySeekBar.setTranslationX((float)getWindowManager().getDefaultDisplay().getWidth()/2 - 50);
        seekProgressCount.setTranslationX(qualitySeekBar.getTranslationX()- 70);
        seekProgressCount.setAlpha(0);

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = 2;
        cachedImage=BitmapFactory.decodeFile(currentPhotoPath);
        int rotate = HelperHelper.getCameraPhotoOrientation(currentPhotoPath);
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        try {
            cachedImage = Bitmap.createBitmap(cachedImage , 0, 0, cachedImage.getWidth(), cachedImage.getHeight(), matrix, true);
        }
        catch (Exception e){
            e.printStackTrace();
            Intent it = new Intent(ResultCameraActivity.this, MainActivity.class);
            startActivity(it);
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentPhotoPath);
            cachedImage.compress(Bitmap.CompressFormat.JPEG,100,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        getParcel.setImageBitmap(cachedImage);
//        imageSize.setText(String.valueOf(sizeImage) + "B");

        file = new File(currentPhotoPath).getParentFile();


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        qualityManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSeekBarOpen){
                    isSeekBarOpen = true;
                    openSeekBar();
                }
                else{
                    isSeekBarOpen = false;
                    closeSeekBar();
                }
            }

        });
        qualitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10;
                progress = progress * 10;

                int OldRange,OldMax,OldMin,NewRange,NewMax,NewMin,NewValue,OldValue;
                OldMax = 100;
                OldMin = 0;
                NewMax = 100;
                NewMin = 50;
                OldValue = progress;

                OldRange = (OldMax - OldMin);
                NewRange = (NewMax - NewMin);
                NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin;

                seekProgressCount.setText(String.valueOf(NewValue) + "%");
                if(progress<=1){
                    SEEK_BAR_PROGRESS = 10;
                }else{
                    SEEK_BAR_PROGRESS = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekProgressCount.animate().setInterpolator(interpolator).alpha(1).setDuration(300).start();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                File file = new File(currentPhotoPath);
                parentDir = file.getParent()  + "/CU_compressedImage.jpg";
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(parentDir);
                    compressedImage = BitmapFactory.decodeFile(currentPhotoPath);
                    compressedImage.compress(Bitmap.CompressFormat.JPEG,SEEK_BAR_PROGRESS,fos);
                    seekProgressCount.animate().setInterpolator(interpolator).alpha(0).setDuration(300).start();
                    CU_compressedFinal = new File(parentDir);
                    Bitmap myBitmap = BitmapFactory.decodeFile(CU_compressedFinal.getAbsolutePath());
                    getParcel.setImageBitmap(myBitmap); } catch(FileNotFoundException e) {e.printStackTrace();}finally {try {fos.close();} catch (IOException e) {e.printStackTrace();}}}

        });

        preview_image_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ResultCameraActivity.this, CropImageActivity.class);
                Bitmap FinalItemBitmap = BitmapFactory.decodeFile(currentPhotoPath);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(currentPhotoPath);
                    FinalItemBitmap.compress(Bitmap.CompressFormat.JPEG,SEEK_BAR_PROGRESS,fos);} catch (FileNotFoundException e) {
                    e.printStackTrace();}finally{try {fos.close();}catch (IOException e) {e.printStackTrace();}
                }
                it.putExtra("cropImageFeeder", currentPhotoPath);
                startActivity(it);}
            });

    }
    private void openSeekBar() {
        qualitySeekBar.setVisibility(View.VISIBLE);
        qualitySeekBar.animate().setInterpolator(interpolator).setDuration(300).translationY(0).start();
        qualitySeekBar.animate().setInterpolator(interpolator).setDuration(300).alpha(1).start();
        qualitySeekBar.setProgress(SEEK_BAR_PROGRESS);
    }
    private void closeSeekBar(){
        qualitySeekBar.animate().setInterpolator(interpolator).setDuration(300).translationY(TRANSLATIONY).start();
        qualitySeekBar.animate().setInterpolator(interpolator).setDuration(300).alpha(0).start();
        qualitySeekBar.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isSeekBarOpen){
            isSeekBarOpen = false;
            closeSeekBar();
            return;
        }
        HelperHelper.traverse_delete(file);
    }

}