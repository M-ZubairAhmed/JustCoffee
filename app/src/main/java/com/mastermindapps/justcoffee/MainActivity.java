package com.mastermindapps.justcoffee;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
    private int arraySize;

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
        final EditText custName = (EditText) findViewById(R.id.customer_name_xml);
        final EditText custEmail = (EditText) findViewById(R.id.customer_email_xml);

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
                    if (!TextUtils.isEmpty(custName.getText().toString()) || !TextUtils.isEmpty(custEmail.getText().toString())){
                        Intent confirmOrderIntent = new Intent(MainActivity.this,OrderConfirmActivity.class);
                        confirmOrderIntent.putExtra("name", custName.getText().toString());
                        confirmOrderIntent.putExtra("email", custEmail.getText().toString());
                        confirmOrderIntent.putExtra("quantity", quantityValue);
                        confirmOrderIntent.putExtra("baseprice", basePrice);
                        confirmOrderIntent.putExtra("totalprice", calculateTotal());
                        confirmOrderIntent.putExtra("arraysize",arraySize);
                        for (int i = 0; i < arraySize; i++) {
                            CheckBox toppingCheckboxVerify = (CheckBox) findViewById(toppingCheckboxList.get(i).getIdOfTopping());
                            if (toppingCheckboxVerify.isChecked()) {
                                confirmOrderIntent.putExtra("topping"+i,toppingCheckboxVerify.getText().toString());
                            }
                        }

                        startActivityForResult(confirmOrderIntent,1001);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Please add name and email", Toast.LENGTH_SHORT).show();
                    }
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
            submitOrderButton.setBackgroundColor(Color.LTGRAY);
            submitOrderButton.setText(R.string.empty_order);
        } else {
            submitOrderButton.setClickable(true);
            TypedValue outValue = new TypedValue();
            getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            submitOrderButton.setBackgroundResource(outValue.resourceId);
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
        for (int j = 0; j < arraySize; j++) {
            CheckBox toppingCheckboxVerify = (CheckBox) findViewById(toppingCheckboxList.get(j).getIdOfTopping());
            if (toppingCheckboxVerify.isChecked()){
                sum = sum + (quantityValue * toppingCheckboxList.get(j).getPriceOfTopping());
            }
        }
        return sum;
    }

    protected void addToppingsToList(){
        toppingCheckboxList = new ArrayList<>();
        toppingCheckboxList.add(new ToppingsDataType("Whipped Cream", 150, 2));
        toppingCheckboxList.add(new ToppingsDataType("Chocolate", 151, 12));
        arraySize = toppingCheckboxList.size();
    }

    protected void addToppingsToView(){
        linearLayToppings = (LinearLayout) findViewById(R.id.topping_linear_xml);

        for (int i = 0; i < arraySize; i++) {
            toppingCheckboxes = new CheckBox(this);
            toppingCheckboxes.setText(toppingCheckboxList.get(i).getNameOfTopping());
            if (Build.VERSION.SDK_INT < 23){
                toppingCheckboxes.setTextAppearance(this,android.R.style.TextAppearance_Medium);
            }
            else {
                toppingCheckboxes.setTextAppearance(android.R.style.TextAppearance_Medium);
            }
            toppingCheckboxes.setId(toppingCheckboxList.get(i).getIdOfTopping());
            linearLayToppings.addView(toppingCheckboxes);
        }
    }

    protected int dpSizeToPx(int dp){
        return (int)(getResources().getDisplayMetrics().density * dp + 0.5F);
    }

    protected boolean textChecker(String text, byte type){
        boolean containsOnlySpace = (text.trim().length() == 0);
        boolean twoLetterWord = text.trim().length() <= 2;
        switch (type){
            case 1:
                //for name
            case 2:
                //for email
        }
        return containsOnlySpace;
    }
}
