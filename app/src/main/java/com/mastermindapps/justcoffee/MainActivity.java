package com.mastermindapps.justcoffee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView submitOrderButton;
    FrameLayout submitOrderRoot;
    TextView quantityViewField;
    int COLOR_BROWN;
    private int quantityValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Order Coffee");

        quantityViewField = (TextView) findViewById(R.id.quantity_var_xml);

        submitOrderButton = (TextView) findViewById(R.id.submit_order_xml);
        submitOrderRoot = (FrameLayout) findViewById(R.id.submit_order_root_xml);

        android.support.design.widget.FloatingActionButton addQuantityButton = (android.support.design.widget.FloatingActionButton) findViewById(R.id.quantity_add_xml);
        android.support.design.widget.FloatingActionButton minusQuantityButton = (android.support.design.widget.FloatingActionButton) findViewById(R.id.quantity_remove_xml);

        COLOR_BROWN = hexColorToInt(R.color.colorPrimary);

        displayQuantityMethod();

        addQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityValue++;
                displayQuantityMethod();
                submitSwitchMethod();
            }
        });

        minusQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityValue != 0) {
                    quantityValue--;
                    displayQuantityMethod();
                    submitSwitchMethod();
                }
            }
        });



    }

    protected void submitSwitchMethod() {
        if (quantityValue == 0) {
            submitOrderButton.setClickable(false);
            submitOrderRoot.setBackgroundColor(Color.LTGRAY);
        } else {
            submitOrderButton.setClickable(true);
            submitOrderRoot.setBackgroundColor(COLOR_BROWN);
        }
    }

    protected void displayQuantityMethod() {
        quantityViewField.setText(String.valueOf(quantityValue));
    }

    protected int hexColorToInt(int colorResource){
        return ContextCompat.getColor(this,colorResource);
    }
}
