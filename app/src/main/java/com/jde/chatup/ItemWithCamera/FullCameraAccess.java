package com.jde.chatup.ItemWithCamera;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jde.chatup.R;
import com.jde.chatup.startUP.StartUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FullCameraAccess extends AppCompatActivity {
    private TextureView textureView;
    private FloatingActionButton click_picture_btn;


    //Checking The Orientation of Capture Image
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0,90);
        ORIENTATIONS.append(Surface.ROTATION_90,0);
        ORIENTATIONS.append(Surface.ROTATION_180,270);
        ORIENTATIONS.append(Surface.ROTATION_270,180);
    }

    private String cameraId;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSessions;
    private CaptureRequest.Builder captureRequestBuilder;
    private Size imageDimension;
    private ImageReader imageReader;

    //Save To Files
    File folder;
    private File file;

    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private boolean mFlashSupport;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundHandlerThread;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    CameraDevice.StateCallback stateCallBack = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;
            createCameraPreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            cameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice = null;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_camera_access);
        textureView = (TextureView) findViewById(R.id.live_camera_preview);
        click_picture_btn = findViewById(R.id.live_capture);

        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);

        click_picture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

    }

    private void takePicture() {
        if(cameraDevice == null)
            return;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
                Size[] jpegSize = null;
                if(characteristics != null){
                    jpegSize = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                            .getOutputSizes(ImageFormat.JPEG);

                    // Capture Image With Required Size
                    int width = 640;
                    int height = 480;
                    if(jpegSize != null && jpegSize.length>0){
                        width = jpegSize[0].getWidth();
                        height = jpegSize[0].getHeight();
                    }
                    ImageReader reader = ImageReader.newInstance(width,height,ImageFormat.JPEG,1);
                    List<Surface> outputSurface = new ArrayList<>(2);
                    outputSurface.add(reader.getSurface());
                    outputSurface.add(new Surface(textureView.getSurfaceTexture()));


                    CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                    captureBuilder.addTarget(reader.getSurface());
                    captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

                    //Check The Orientation Base on Device
                    int rotation = getWindowManager().getDefaultDisplay().getRotation();
                    captureBuilder.set(CaptureRequest.JPEG_ORIENTATION,ORIENTATIONS.get(rotation));

//                    file = new File(Environment.getExternalStorageDirectory() + "/ChatUp" + UUID.randomUUID().toString() + ".jpg");
//
                     folder = new File(Environment.getExternalStorageDirectory() + "/ChatUp");
                    if (!folder.exists()) {
                        folder = new File(Environment.getExternalStorageDirectory() + "/ChatUp");
                        boolean success = folder.mkdir();

                        if (success){
                            file = new File(folder.getPath() + "/ChatUp" + UUID.randomUUID(), toString()+ ".jpg");

                        }else {
                            //Some message
                            folder = new File(Environment.getExternalStorageDirectory() + "/ChatUp");
                            Log.d("TakenImageHere", "takePicture: " + folder.getPath());
                        }
                    }else {
                        file = new File(folder.getPath() + "/ChatUp" + UUID.randomUUID(), toString()+ ".jpg");
                    }


                    ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                        @Override
                        public void onImageAvailable(ImageReader reader) {
                            Image image = null;
                            Log.d("ListenerHere", "onImageAvailable: " + "The Image Listener is Called Here");

                            try {
                                image = reader.acquireLatestImage();
                                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                                byte[] bytes = new byte[buffer.capacity()];
                                buffer.get(bytes);
                                save(bytes);
                                Log.d("ListenerHere", "onImageAvailable: " + "The Image Listener Save Here");
                                cameraDevice.close();
                            }catch (FileNotFoundException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            finally {
                                if(image != null)
                                    image.close();
                            }
                        }

                        private void save(byte[] bytes) throws IOException {
                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(file);
                                outputStream.write(bytes);
                            }
                            finally {
                                if (outputStream != null)
                                    outputStream.close();
                            }
                        }

                    };
                    reader.setOnImageAvailableListener(readerListener,mBackgroundHandler);
                    CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                            super.onCaptureCompleted(session, request, result);
                            Toast.makeText(FullCameraAccess.this, "Saved" + file, Toast.LENGTH_SHORT).show();
                            createCameraPreview();
                        }
                    };



                    cameraDevice.createCaptureSession(outputSurface, new CameraCaptureSession.StateCallback() {

                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession session) {
                            try {
                                cameraCaptureSessions.capture(captureBuilder.build(),captureListener,mBackgroundHandler);
                            }catch (Exception e){e.printStackTrace();}

                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                        }
                    },mBackgroundHandler);

                }

            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this, "Sorry This Service is Not Available for Your Android Version", Toast.LENGTH_SHORT).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(),imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    if(cameraDevice == null)
                        return;
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    Toast.makeText(FullCameraAccess.this, "Changed", Toast.LENGTH_SHORT).show();
                }
            },null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updatePreview(){
        if(cameraDevice == null)
            Toast.makeText(this, "Error In Camera", Toast.LENGTH_SHORT).show();
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE,CaptureRequest.CONTROL_MODE_AUTO);
        try {
            cameraCaptureSessions.setRepeatingBurst(Collections.singletonList(captureRequestBuilder.build()),null,mBackgroundHandler);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera(){
        CameraManager manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = manager.getCameraIdList()[0];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];

            //Check For The Realtime Permission
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId,stateCallBack,null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CAMERA_PERMISSION){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Please Allow The Camera Permission First", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPostResume() {
        super.onPostResume();
        startBackgroundThread();
        if(textureView.isAvailable()){
            openCamera();
        }
        else{
            textureView.setSurfaceTextureListener(textureListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopBackgroundThread();
        try {cameraDevice.close();}
        catch (Exception e){e.printStackTrace();}
        finally {}
    }

    private void stopBackgroundThread() {
        mBackgroundHandlerThread.quitSafely();
        try {
            mBackgroundHandlerThread.join();
            mBackgroundHandlerThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void startBackgroundThread() {
        mBackgroundHandlerThread = new HandlerThread("Camera Background");
        mBackgroundHandlerThread.start();
        mBackgroundHandler = new Handler(mBackgroundHandlerThread.getLooper());

    }


}