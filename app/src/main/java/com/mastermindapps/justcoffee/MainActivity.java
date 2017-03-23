package com.mastermindapps.justcoffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Order Coffee");

        final TextView quantityView = (TextView) findViewById(R.id.quantity_var_xml);
        TextView submitOrder = (TextView) findViewById(R.id.submit_order_xml);
        android.support.design.widget.FloatingActionButton addQuantity = (android.support.design.widget.FloatingActionButton) findViewById(R.id.quantity_add_xml);
        android.support.design.widget.FloatingActionButton minusQuantity = (android.support.design.widget.FloatingActionButton) findViewById(R.id.quantity_remove_xml);

        quantityView.setText(String.valueOf(quantity));

        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityView.setText(String.valueOf(quantity));
            }
        });
        minusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity != 0){
                    quantity--;
                    quantityView.setText(String.valueOf(quantity));
                }
            }
        });


    }
}
