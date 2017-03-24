package com.mastermindapps.justcoffee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int basePrice = 40;
    private TextView submitOrderButton;
    private FrameLayout submitOrderRoot;
    private TextView quantityViewField;
    private CheckBox toppingCheckboxes;
    private ArrayList<ToppingsDataType> toppingCheckboxList;
    private LinearLayout linearLayToppings;
    private int COLOR_BROWN;
    private int quantityValue = 0;

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
        submitSwitchMethod();

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

        submitOrderButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (quantityValue == 0) {
                    return false;
                } else {
                    Toast.makeText(MainActivity.this, String.valueOf(calculateTotal()), Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        });

        addToppingsToList();

        addToppingsToView();
    }


    protected void submitSwitchMethod() {
        if (quantityValue == 0) {
            submitOrderButton.setClickable(false);
            submitOrderRoot.setBackgroundColor(Color.LTGRAY);
            submitOrderButton.setText(R.string.empty_order);
        } else {
            submitOrderButton.setClickable(true);
            submitOrderRoot.setBackgroundColor(COLOR_BROWN);
            submitOrderButton.setText(R.string.submit_long_press);
        }
    }

    protected void displayQuantityMethod() {
        quantityViewField.setText(String.valueOf(quantityValue));
    }

    protected int hexColorToInt(int colorResource) {
        return ContextCompat.getColor(this, colorResource);
    }

    protected int calculateTotal(){
        int sum = quantityValue * basePrice;
        for (int j = 0; j < toppingCheckboxList.size(); j++) {
            CheckBox toppingCheckboxVerify = (CheckBox) findViewById(toppingCheckboxList.get(j).getIdOfTopping());
            if (toppingCheckboxVerify.isChecked()){
                sum = sum + (quantityValue * toppingCheckboxList.get(j).getPriceOfTopping());
            }
        }
        return sum;
    }

    protected void addToppingsToList(){
        toppingCheckboxList = new ArrayList<>();

        toppingCheckboxList.add(new ToppingsDataType("Oreo", 150, 2));
        toppingCheckboxList.add(new ToppingsDataType("Latte", 151, 12));
    }

    protected void addToppingsToView(){
        linearLayToppings = (LinearLayout) findViewById(R.id.topping_linear_xml);
        
        for (int i = 0; i < toppingCheckboxList.size(); i++) {
            toppingCheckboxes = new CheckBox(this);
            toppingCheckboxes.setText(toppingCheckboxList.get(i).getNameOfTopping());
            toppingCheckboxes.setId(toppingCheckboxList.get(i).getIdOfTopping());
            linearLayToppings.addView(toppingCheckboxes);
        }
    }
}
