package com.example.foodbuy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodbuy.Activity.CartActivity;
import com.example.foodbuy.Activity.SettingActivity;
import com.example.foodbuy.Activity.SupportActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<CartItem> cartItemList;
    private VideoView videoView;
    private BottomNavigationView bottomNavigationView;

    ImageView item1,item2,item3,item4,item5,item6,item7,item8,item9,item10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartItemList = new ArrayList<>();
        // Initialize views
        videoView = findViewById(R.id.videoView);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        item1= findViewById(R.id.ivAddToCart1);
        item2= findViewById(R.id.ivAddToCart2);
        item3= findViewById(R.id.ivAddToCart3);
        item4= findViewById(R.id.ivAddToCart4);
        item5= findViewById(R.id.ivAddToCart5);
        item6= findViewById(R.id.ivAddToCart6);
        item7= findViewById(R.id.ivAddToCart7);
        item8= findViewById(R.id.ivAddToCart8);
        item9= findViewById(R.id.ivAddToCart9);
        item10= findViewById(R.id.ivAddToCart10);
        // Set up video playback
        setupVideoView();

        // Handle bottom navigation item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.navigation_profile) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                return true;
            }
            else if (item.getItemId() == R.id.navigation_cart) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                return true;
            }
            else if (item.getItemId() == R.id.navigation_support) {
                startActivity(new Intent(MainActivity.this, SupportActivity.class));
                return true;
            }
            else if (item.getItemId() == R.id.navigation_settings) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                return true;
            }
            else return false;
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.pizza1,"Pepperoni Pizza","₹ 319"));
                Toast.makeText(MainActivity.this, "Pepperoni Pizza added to cart..", Toast.LENGTH_SHORT).show();
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.burger_large,"Chese Burger","₹ 96"));
                Toast.makeText(MainActivity.this, "Chese Burger added to cart..", Toast.LENGTH_SHORT).show();
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.vegetable_pizza,"Vegetable Pizza","₹ 249"));

                Toast.makeText(MainActivity.this, "Vegetable Pizza added to cart..", Toast.LENGTH_SHORT).show();}
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.fourchese,"4 Chese Pizza","₹ 150"));
                Toast.makeText(MainActivity.this, "4 Chese Pizza added to cart..", Toast.LENGTH_SHORT).show();}
        });
        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.paneer_makhani,"Paneer Makhani","₹ 290"));
                Toast.makeText(MainActivity.this, "Paneer Makhani added to cart..", Toast.LENGTH_SHORT).show();}
        });
        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.peppy_paneer,"Peppy Paneer","₹ 239"));
                Toast.makeText(MainActivity.this, "Peppy Paneer added to cart..", Toast.LENGTH_SHORT).show();}
        });
        item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.vegie_paradise," Vegie Paradise","₹ 248"));
                Toast.makeText(MainActivity.this, "Vegie Paradise added to cart..", Toast.LENGTH_SHORT).show();}
        });
        item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.dosa,"Dosa","₹ 90"));
                Toast.makeText(MainActivity.this, "Dosa added to cart..", Toast.LENGTH_SHORT).show();}
        });
        item9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.sambar_vada,"Sambar Wada","₹ 100"));
                Toast.makeText(MainActivity.this, "added to cart..", Toast.LENGTH_SHORT).show();}
        });
        item10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemList.add(new CartItem(R.drawable.idli,"Idli","₹ 80"));
                Toast.makeText(MainActivity.this, "Idli added to cart..", Toast.LENGTH_SHORT).show();}
        });

    }

    private void setupVideoView() {
        videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videosample);
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mp -> {
            mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            videoView.start();
        });
    }
}
