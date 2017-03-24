package com.mastermindapps.justcoffee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
    private TextView submitOrderButton;
    private FrameLayout submitOrderRoot;
    private TextView quantityViewField;
    private CheckBox toppingCheckboxes;
    private LinearLayout linearLayToppings;
    private int COLOR_BROWN;
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

        final ArrayList<Toppings> arrayList = new ArrayList<>();
        arrayList.add(new Toppings("Oreo",150,2.5F));
        arrayList.add(new Toppings("Latte",151,12.2F));

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
                    Toast.makeText(MainActivity.this,"checked" + arrayList.get(j).getPriceOfTopping(),Toast.LENGTH_SHORT).show();

                }
            });
        }
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
