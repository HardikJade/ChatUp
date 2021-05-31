package com.jde.chatup.CropImageHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.jde.chatup.ItemWithCamera.ResultCameraActivity;
import com.jde.chatup.R;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropImageActivity extends AppCompatActivity {

    private CropImageView cropImageView;
    private ImageView getCropFinal;
    private ImageView rotateFinal;
    private Bitmap cachedImage;
    private String bitmapImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        cropImageView = findViewById(R.id.cropImageWindow);
        getCropFinal = findViewById(R.id.cropImageGetFinal);
        rotateFinal = findViewById(R.id.cropImageRotate);

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = 2;

        int rotate = HelperHelper.getCameraPhotoOrientation(bitmapImageURI);
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        bitmapImageURI = getIntent().getStringExtra("cropImageFeeder");
        cachedImage = BitmapFactory.decodeFile(bitmapImageURI);
        cachedImage = Bitmap.createBitmap(cachedImage , 0, 0, cachedImage.getWidth(), cachedImage.getHeight(), matrix, true);
        cropImageView.setImageBitmap(cachedImage);

        getCropFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap cropped = cropImageView.getCroppedImage();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(bitmapImageURI);
                    cropped.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                sendItem();
            }
        });

        rotateFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                cachedImage = Bitmap.createBitmap(cachedImage , 0, 0, cachedImage.getWidth(), cachedImage.getHeight(), matrix, true);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(bitmapImageURI);
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
                cropImageView.setImageBitmap(cachedImage);
            }
        });

    }


    private void sendItem() {
        Intent it = new Intent(CropImageActivity.this,ResultCameraActivity.class);
        it.putExtra("BitMapImage",bitmapImageURI);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
        finish();
    }

}