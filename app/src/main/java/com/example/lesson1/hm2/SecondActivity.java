package com.example.lesson1.hm2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    ImageView  imImageGallery;
 EditText etTransData;
 Button btnSendDataToMainActivity;
 String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        listner();

    }
    private void  initView(){
        imImageGallery = findViewById(R.id.in_image_gallery);
        etTransData = findViewById(R.id.et_title);
        btnSendDataToMainActivity = findViewById(R.id.btn_send_data_to_main_activity);
    }
    private void listner(){
        imImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA_SECURE);
                startActivity(intent);
                resultLauncher.launch("image/*");
            }
        });
        btnSendDataToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           String data = etTransData.getText().toString();
           Intent intent = new Intent(SecondActivity.this,MainActivity.class);
           intent.putExtra("key",data);
           intent.putExtra("image",image);
           startActivity(intent);

            }
        });
    }

    ActivityResultLauncher<String> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri Uri) {
                    image = Uri.toString();
                    imImageGallery.setImageURI(Uri);
                }
            });

}













