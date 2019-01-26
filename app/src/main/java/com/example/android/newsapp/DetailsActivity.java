package com.example.android.newsapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        String title= getIntent().getExtras().getString("id");
        String desc= getIntent().getExtras().getString("id2");
        String image= getIntent().getExtras().getString("id3");


        TextView tv_name = findViewById(R.id.posttitle);
        TextView tv_name1 = findViewById(R.id.aa_desc);
        ImageView img3 = findViewById(R.id.postimage);


        tv_name.setText(title);

        tv_name1.setText(desc);
        Picasso.get().load(image).into(img3);
    }
}
