package com.example.assignment5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {
    private TextView textTitle, textPrice;
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        textTitle = findViewById(R.id.textTitle);
        textPrice = findViewById(R.id.textPrice);
        btnBuy = findViewById(R.id.btnBuy);

        // Retrieve the title and price from the intent
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");

        // Display the title and price in the TextViews
        textTitle.setText("Title: " + title);
        textPrice.setText("Price: " + price);

        // Set a click listener for the "Buy" button
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message with the purchase details
                String toastMessage = "You have bought " + title + " for " + price;
                Toast.makeText(PaymentActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
