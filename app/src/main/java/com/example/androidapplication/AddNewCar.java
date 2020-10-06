package com.example.androidapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class AddNewCar extends AppCompatActivity {

    private ImageView cImageView;
    private EditText cbrand,cModel,ctransmission,cModYear,cmileage,cfuel,ccontact;
    Button addCarbtn;
    ActionBar actionBar;

    private static  final int  CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE =101;

    private static final int IMAGE_PICK_CAMERA_CODE=102;
    private static final int IMAGE_PICK_GALLERY_CODE=103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;
    private String brand,Model,transmission,ModYear,mileage,fuel,contact, timeStamp;
    private  DatabaseHelper dbHelper;
    private boolean True;
    private boolean False;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);


        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Car Details");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        cImageView = findViewById(R.id.picture);
        cbrand = findViewById(R.id.brand);
        cModel = findViewById(R.id.Model);
        ctransmission =findViewById(R.id.transmission);
        cModYear = findViewById(R.id.ModYear);
        cmileage = findViewById(R.id.mileage);
        cfuel = findViewById(R.id.fuel);
        ccontact = findViewById(R.id.contact);

        addCarbtn = findViewById(R.id.add);
        cameraPermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //initialize database object in  main function
        dbHelper = new DatabaseHelper(this);

        cImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();

            }
        });
        addCarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when click on save button insert the data to db
                getData();

            }
        });

    }

    private void getData() {
        brand =""+cbrand.getText().toString().trim();
        Model = ""+cModel.getText().toString().trim();
        transmission = ""+ctransmission.getText().toString().trim();
        ModYear = ""+cModYear.getText().toString().trim();
        mileage = ""+cmileage.getText().toString().trim();
        fuel = ""+cfuel.getText().toString().trim();
        contact = ""+ccontact.getText().toString().trim();

        timeStamp = ""+System.currentTimeMillis();

        //my added validation
        if(brand =="" | Model==""| transmission =="" | ModYear =="" | mileage =="" |fuel=="" |contact==""){

            Toast.makeText(AddNewCar.this,"Please fill out all the fields",Toast.LENGTH_SHORT).show();
            return;
        }
        if(this.ModYear.length() != 4 ){

            Toast.makeText(AddNewCar.this,"Please Enter a Valid Model Year",Toast.LENGTH_SHORT).show();
            return;
        }

        if(this.contact.length() != 10 ){

            Toast.makeText(AddNewCar.this,"Please Enter a Valid Phone Number",Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            dbHelper.insertACar(
                    "" + brand,
                    "" + Model,
                    "" + transmission,
                    "" + ModYear,
                    "" + imageUri,
                    "" + mileage,
                    "" + fuel,
                    "" + contact,
                    "" + timeStamp,
                    "" + timeStamp

            );
            startActivity(new Intent(AddNewCar.this, ListCarView.class));
            Toast.makeText(AddNewCar.this,"Added Successfully",Toast.LENGTH_SHORT).show();
        }

    }

    private void imagePickDialog() {

        String[] options ={"Camera", "Gallery"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select for image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    //if 0 then open the camera and also check the permission of camera
                    if (!checkCameraPermission()){
                        //If permission not granted  then request for camera permission
                        requestCameraPermission();
                    }
                    else{
                        pickFromCamera();
                    }

                }
                else if (which==1) {
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else{
                        pickFromStorage();
                    }
                }

            }
        });
        builder.create().show();
    }

    private void pickFromStorage() {
        //so this function get image from gallery
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        //now get image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image description");

        imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==  (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private  void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private  boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);
        boolean resultl = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result && resultl;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted =grantResults[0] ==PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted =grantResults[1] ==PackageManager.PERMISSION_GRANTED;


                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else{
                        Toast.makeText(this,"Camera permission required!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;

            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromStorage();
                    }
                    else {
                        Toast.makeText(this,"Storage Permission Required!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    cImageView.setImageURI(resultUri);
                }
                else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    Toast.makeText(this,""+error, Toast.LENGTH_SHORT).show();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return super.onSupportNavigateUp();

    }
    public boolean isMileageValid(String mileage){

        if (mileage.contains("km") || (mileage.contains("Km"))){
            return True;
        }
        else {
            return False;
        }
    }


}
