    package com.jde.chatup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.BitmapCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jde.chatup.ItemWithCamera.ResultCameraActivity;
import com.jde.chatup.LoginCredits.Adapters.Main_Recycler_Adapter;
import com.jde.chatup.Models.Main_Recycler_Child_Model;
import com.jde.chatup.Models.Main_Recycler_Parent_Model;
import com.jde.chatup.StatusHere.Status;
import com.jde.chatup.fab_handler.FabHandler;

import java.io.File;
import java.util.ArrayList;


    public class MainActivity extends AppCompatActivity{
        ArrayList<Main_Recycler_Parent_Model> main_recycler_parent_models_list = new ArrayList<Main_Recycler_Parent_Model>();
        private FloatingActionButton main_float_btn;
        private FloatingActionButton child_float_1;
        private FloatingActionButton child_float_2;
        private FloatingActionButton child_float_3 ;
        private boolean isMenuOpen = false;
        Float translationY = 100f;
        OvershootInterpolator interpolator = new OvershootInterpolator();
        private boolean doubleBackToExitPressedOnce = false;
        public static final int CAMERA_PERM_CODE = 101;
        public static final int CAMERA_REQUEST_CODE = 102;
        private String currentPhotoPath;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        RecyclerView main_parent_recycler = findViewById(R.id.main_parent_recycler);
        initData();

        Main_Recycler_Adapter adapter = new Main_Recycler_Adapter(main_recycler_parent_models_list,this);
        main_parent_recycler.setAdapter(adapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        main_parent_recycler.setLayoutManager(linearLayoutManager);

        initFab();




    }
        @Override
        public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    System.exit(0);
                    return;
            }

            this.doubleBackToExitPressedOnce = true;
            Thread th = new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(2000);
                    }
                    catch (Exception e){e.printStackTrace();}
                    finally {
                        doubleBackToExitPressedOnce=false;
                    }
                }
            };
            th.start();
        }
        private void openMenu(){
            isMenuOpen = !isMenuOpen;

            main_float_btn.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();
            child_float_1.animate().translationY(0f).setInterpolator(interpolator).alpha(1f).setDuration(300).start();
            child_float_2.animate().translationY(0f).setInterpolator(interpolator).alpha(1f).setDuration(300).start();
            child_float_3.animate().translationY(0f).setInterpolator(interpolator).alpha(1f).setDuration(300).start();



        }
        private void closeMenu(){
            isMenuOpen = !isMenuOpen;

            main_float_btn.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();
            child_float_1.animate().translationY(translationY).setInterpolator(interpolator).alpha(0f).setDuration(300).start();
            child_float_2.animate().translationY(translationY).setInterpolator(interpolator).alpha(0f).setDuration(300).start();
            child_float_3.animate().translationY(translationY).setInterpolator(interpolator).alpha(0f).setDuration(300).start();

        }
        private void initFab() {
            main_float_btn = findViewById(R.id.main_float_btn);
            child_float_1 = findViewById(R.id.child_float_btn_1);
            child_float_2 = findViewById(R.id.child_float_btn_2);
            child_float_3 = findViewById(R.id.child_float_btn_3);

            child_float_1.setAlpha(0f);
            child_float_2.setAlpha(0f);
            child_float_3.setAlpha(0f);

            child_float_1.setTranslationY(translationY);
            child_float_2.setTranslationY(translationY);
            child_float_3.setTranslationY(translationY);

            //Main Parent Fab Button
            main_float_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isMenuOpen)
                        closeMenu();
                    else
                        openMenu();
                }
            });

            //Child On Click Listener
            child_float_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    askForCamera();

                    closeMenu();
                }
            });

            child_float_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent it = new Intent(MainActivity.this, Status.class);
                    it.putExtra("ActivityId","1234567890");
                    it.putExtra("CookIeUser","56+656+65+66+5");
                    it.putExtra("AuthPin","235+53564564600");
                    it.putExtra("AxisSpin","12364685435434435435");
                    it.putExtra("PostalAddress","41234894531234351");
                    startActivity(it);
                    overridePendingTransition(R.anim.animate_windmill_enter,R.anim.animate_windmill_exit);
                    closeMenu();
                }

            });

            child_float_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(MainActivity.this, FabHandler.class);
                    startActivity(it);
                    overridePendingTransition(R.anim.animate_card_enter,R.anim.animate_card_exit);
                    closeMenu();
                }
            });

        }
        private void askForCamera() {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},CAMERA_PERM_CODE);
            }
            else{
                openCamera();
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if(requestCode == CAMERA_PERM_CODE){
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //openCamera
                    openCamera();
                }
                else{
                    Toast.makeText(this, "Camera Permission is Required For This Action", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    it.setData(uri);
                    startActivity(it);
                }
            }

        }

        private void openCamera() {
            //This Should Be The Unique File Name
            String file_name  = "photo";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File imageFile = File.createTempFile(file_name,".jpg",storageDir);
                currentPhotoPath = imageFile.getAbsolutePath();
                Uri imageUri = FileProvider.getUriForFile(MainActivity.this,"com.jde.chatup.fileprovider",imageFile);
                Intent its = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                its.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(its,CAMERA_REQUEST_CODE);
                overridePendingTransition(R.anim.animate_zoom_enter,R.anim.animate_zoom_exit);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            try{
                if (requestCode == CAMERA_REQUEST_CODE) {
                    Bitmap image = BitmapFactory.decodeFile(currentPhotoPath);
                    int bitmapByteCount= BitmapCompat.getAllocationByteCount(image);
                    if(bitmapByteCount>0){
                        Intent it = new Intent(MainActivity.this, ResultCameraActivity.class);
                        it.putExtra("BitMapImage",currentPhotoPath);
                        startActivity(it);
                    }
                    else{}
                }
                else{
                    Toast.makeText(this, "Somthing Went Wrong With The Camera Action", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){}
            finally {}
        }

        private void initData(){
            ArrayList<Main_Recycler_Child_Model> main_recycler_child_models_list_1 = new ArrayList<Main_Recycler_Child_Model>();
            main_recycler_child_models_list_1.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Dan Well","Hello How Are..",10));
            main_recycler_child_models_list_1.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Will Wade","Hello How Are..",3));
            main_recycler_child_models_list_1.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Beverly Wade","Hello How Are..",1));
            main_recycler_child_models_list_1.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Beverly Wade","Hello How Are..",1));
            main_recycler_child_models_list_1.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Beverly Wade","Hello How Are..",1));
            main_recycler_child_models_list_1.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Beverly Wade","Hello How Are..",1));



            ArrayList<Main_Recycler_Child_Model> main_recycler_child_models_list_2 = new ArrayList<Main_Recycler_Child_Model>();
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Young","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Laurel Hughes","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Emma Watson","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Emma Young","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));
            main_recycler_child_models_list_2.add(new Main_Recycler_Child_Model(R.drawable.model_face,"Rebecca Fox","Sent An Image",1));

            main_recycler_parent_models_list.add(new Main_Recycler_Parent_Model("Pinned Contacts",main_recycler_child_models_list_1));
            main_recycler_parent_models_list.add(new Main_Recycler_Parent_Model("Others",main_recycler_child_models_list_2));

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_toolbar_item,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            return true;
        }



    }