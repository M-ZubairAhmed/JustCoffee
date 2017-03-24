package com.mastermindapps.justcoffee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int basePrice = 40;
    private int totalCost;
    private TextView submitOrderButton;
    private FrameLayout submitOrderRoot;
    private TextView quantityViewField;
    private CheckBox toppingCheckboxes;
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
                totalCost = cupsPrice(quantityValue);
            }
        });

        minusQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityValue != 0) {
                    quantityValue--;
                    displayQuantityMethod();
                    submitSwitchMethod();
                    totalCost = cupsPrice(quantityValue);
                }
            }
        });

        submitOrderButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (quantityValue == 0){
                    return false;
                }
                else {
                    Toast.makeText(MainActivity.this,String.valueOf(totalCost),Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        });

        final ArrayList<Toppings> arrayList = new ArrayList<>();
        arrayList.add(new Toppings("Oreo", 150, 2));
        arrayList.add(new Toppings("Latte", 151, 12));

        linearLayToppings = (LinearLayout) findViewById(R.id.topping_linear_xml);
        for (int i = 0; i < arrayList.size(); i++) {
            toppingCheckboxes = new CheckBox(this);
            toppingCheckboxes.setText(arrayList.get(i).getNameOfTopping());
            toppingCheckboxes.setId(arrayList.get(i).getIdOfTopping());
            linearLayToppings.addView(toppingCheckboxes);
            final int j = i;
            toppingCheckboxes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int sum;
                    if (isChecked) {
                        sum = priceCalculator(quantityValue, arrayList.get(j).getPriceOfTopping(), true);
                        Toast.makeText(MainActivity.this, "checked" + String.valueOf(sum), Toast.LENGTH_SHORT).show();
                    } else {
                        sum = priceCalculator(quantityValue, arrayList.get(j).getPriceOfTopping(), false);
                        Toast.makeText(MainActivity.this, "checked" + String.valueOf(sum), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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

    protected int hexColorToInt(int colorResource){
        return ContextCompat.getColor(this,colorResource);
    }

    protected int priceCalculator(int quantityValue, int toppingPrice, boolean toppingInclude) {
        int totalPrice = basePrice * quantityValue;
        if (toppingInclude) {
            totalPrice = (basePrice * quantityValue) + toppingPrice;
        }
        return totalPrice;
    }

    private int cupsPrice(int quantityValue){
        return quantityValue * basePrice;
    }
}
