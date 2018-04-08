package com.example.android.medikart;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static java.security.AccessController.getContext;


public class ImageActivity extends AppCompatActivity {
    String image;
    Uri img_uri;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent i=getIntent();
        image=i.getStringExtra("Image");
        img_uri=Uri.parse(image);
        img=(ImageView)findViewById(R.id.imgView);
        Picasso.with(getApplicationContext()).load(img_uri).into(img);
    }
}
