package com.example.foodbuy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodbuy.CartItem;
import com.example.foodbuy.MainActivity;
import com.example.foodbuy.R;
import com.razorpay.Checkout;
import com.razorpay.CheckoutActivity;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {
    private Button payment;

    private Checkout checkout; // Razorpay Checkout instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        payment = findViewById(R.id.proceed);


        payment.setOnClickListener(v -> {
            // Logic to save the address

            startPayment(500);
        });


        // Access the static cart item list from MainActivity
        ArrayList<CartItem> cartItems = MainActivity.cartItemList;

        // Find the LinearLayout where you want to display the cart items
        LinearLayout cartLayout = findViewById(R.id.cart);

        // Loop through the cart items and display them
        for (CartItem item : cartItems) {
            // Create a LinearLayout for each cart item
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Convert 10dp to pixels
            int marginInPixels = dpToPx(10);

            // Set layout params for item layout with top and bottom margins
            LinearLayout.LayoutParams itemLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            itemLayoutParams.setMargins(0, marginInPixels, 0, marginInPixels);
            itemLayout.setLayoutParams(itemLayoutParams);

            // Create an ImageView for the item image
            ImageView itemImage = new ImageView(this);
            itemImage.setImageResource(item.getImageResourceId());
            itemImage.setLayoutParams(new LinearLayout.LayoutParams(200, 200));

            // Create a vertical LinearLayout for the item name and price
            LinearLayout textLayout = new LinearLayout(this);
            textLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textLayoutParams.gravity = android.view.Gravity.CENTER_VERTICAL; // Set layout_gravity to center_vertical
            textLayout.setLayoutParams(textLayoutParams);

            // Create LayoutParams for the TextViews with left margin
            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textViewParams.setMargins(35, 0, 0, 0); // Set left margin to 20dp

            // Create a TextView for the item name
            TextView itemName = new TextView(this);
            itemName.setText(item.getItemName());
            itemName.setLayoutParams(textViewParams); // Apply margin
            itemName.setTextSize(22);
            itemName.setTypeface(null, android.graphics.Typeface.BOLD);

            // Create a TextView for the item price
            TextView itemPrice = new TextView(this);
            itemPrice.setText(item.getItemPrice());
            itemPrice.setLayoutParams(textViewParams); // Apply margin
            itemPrice.setTextSize(20);
            itemPrice.setTypeface(null, android.graphics.Typeface.BOLD);

            // Add the text views to the text layout
            textLayout.addView(itemName);
            textLayout.addView(itemPrice);

            // Add the image view and text layout to the item layout
            itemLayout.addView(itemImage);
            itemLayout.addView(textLayout);

            // Add the item layout to the cart layout
            cartLayout.addView(itemLayout);
        }
    }

    // Method to convert dp to pixels
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
    private void startPayment(int amountInRupees) {
        checkout = new Checkout();
        checkout.setKeyID("rzp_test_1DP5mmOlF5G5ag");

        try {
            double amountInRupeesDouble = amountInRupees;
            int amountInPaisa = (int) (amountInRupeesDouble * 100); // Convert rupees to paisa

            JSONObject options = new JSONObject();
            options.put("currency", "INR");
            options.put("amount", amountInPaisa); // Amount in paisa
            options.put("name", "Food Mood");
            options.put("description", "Order Payment");


            checkout.open(CartActivity.this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Error in starting payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();

        MainActivity.cartItemList.clear();

        // Show success message in a dialog
        new AlertDialog.Builder(this)
                .setTitle("Payment Successful")
                .setMessage("Your payment was successful!")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Optionally, you can add any further actions here
                    Intent intent =new Intent(CartActivity.this, MainActivity.class);
                    startActivity(intent);
                })
                .show();

    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_SHORT).show();

    }


}
