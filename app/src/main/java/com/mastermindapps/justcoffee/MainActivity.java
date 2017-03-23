package com.mastermindapps.justcoffee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int quantity = 1;
    TextView submitOrder;
    FrameLayout submitOrderRoot;
    int COLOR_BROWN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Order Coffee");

        final TextView quantityView = (TextView) findViewById(R.id.quantity_var_xml);

        submitOrder = (TextView) findViewById(R.id.submit_order_xml);
        submitOrderRoot = (FrameLayout) findViewById(R.id.submit_order_root_xml);

        android.support.design.widget.FloatingActionButton addQuantity = (android.support.design.widget.FloatingActionButton) findViewById(R.id.quantity_add_xml);
        android.support.design.widget.FloatingActionButton minusQuantity = (android.support.design.widget.FloatingActionButton) findViewById(R.id.quantity_remove_xml);

        COLOR_BROWN = ContextCompat.getColor(this, R.color.colorPrimary);

        quantityView.setText(String.valueOf(quantity));

        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityView.setText(String.valueOf(quantity));
                submitStatus();
            }
        });

        minusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity != 0){
                    quantity--;
                    quantityView.setText(String.valueOf(quantity));
                    submitStatus();
                }
            }
        });

    }

    protected void submitStatus() {
        if (quantity == 0) {
            submitOrder.setClickable(false);
            submitOrderRoot.setBackgroundColor(Color.LTGRAY);
        } else {
            submitOrder.setClickable(true);
            submitOrderRoot.setBackgroundColor(COLOR_BROWN);
        }
    }
}
