package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;



public class UpdateACar extends AppCompatActivity {

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

    private String id, brand, Model, transmission, ModYear, mileage,fuel,contact, add_car, update_car;
    private boolean editMode = false;
    private  DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_a_car);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        cImageView = findViewById(R.id.image);
        cbrand = findViewById(R.id.brand);
        cModel = findViewById(R.id.Model);
        ctransmission =findViewById(R.id.transmission);
        cModYear = findViewById(R.id.ModYear);
        cmileage = findViewById(R.id.mileage);
        cfuel = findViewById(R.id.fuel);
        ccontact = findViewById(R.id.contact);


        addCarbtn = findViewById(R.id.addFabButton);


        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode", editMode);
        id = intent.getStringExtra("ID");
        brand = intent.getStringExtra("BRAND");
        Model = intent.getStringExtra("MODEL");
        transmission = intent.getStringExtra("TRANSMISSION");
        ModYear = intent.getStringExtra("MODYEAR");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        mileage = intent.getStringExtra("MILEAGE");
        fuel = intent.getStringExtra("FUEL");
        contact = intent.getStringExtra("CONTACT");
        add_car = intent.getStringExtra("ADD_CAR");
        update_car = intent.getStringExtra("UPDATE_CAR");

        if (editMode) {
            actionBar.setTitle("Update Car Details");

            editMode = intent.getBooleanExtra("editMode", editMode);
            id = intent.getStringExtra("ID");
            brand = intent.getStringExtra("BRAND");
            Model = intent.getStringExtra("MODEL");
            transmission = intent.getStringExtra("TRANSMISSION");
            ModYear = intent.getStringExtra("MODYEAR");
            imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
            mileage = intent.getStringExtra("MILEAGE");
            fuel = intent.getStringExtra("FUEL");
            contact = intent.getStringExtra("CONTACT");
            add_car = intent.getStringExtra("ADD_CAR");
            update_car = intent.getStringExtra("UPDATE_CAR");

            cbrand.setText(brand);
            cModel.setText(Model);
            ctransmission.setText(transmission);
            cModYear.setText(ModYear);
            cmileage.setText(mileage);
            cfuel.setText(fuel);
            ccontact.setText(contact);

            if (imageUri.toString().equals("null")) {
                cImageView.setImageResource(R.drawable.ic_action_profile);
            }
            else{
                cImageView.setImageURI(imageUri);
            }
        }
        else{
            actionBar.setTitle("Add Car Details");
        }





        cameraPermissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


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

                getData();

                startActivity(new Intent(UpdateACar.this, ListCarView.class));
                Toast.makeText(UpdateACar.this,"Update Successfully",Toast.LENGTH_SHORT).show();
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

        if (editMode) {
            String newUpdateTime = ""+System.currentTimeMillis();

            dbHelper.updateACar(
                    ""+id,
                    ""+brand,
                    ""+Model,
                    ""+transmission,
                    ""+ModYear,
                    ""+imageUri,
                    ""+mileage,
                    ""+fuel,
                    ""+contact,
                    ""+add_car,
                    ""+update_car
            );
        } else {

            String timeStamp = "" + System.currentTimeMillis();
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

                    if (!checkCameraPermission()){

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

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {

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
}