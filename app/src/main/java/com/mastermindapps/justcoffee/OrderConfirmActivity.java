package com.mastermindapps.justcoffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        OrderConfirmActivity.this.setTitle("Order summary");
        Bundle bundle = getIntent().getExtras();

        TextView name = (TextView) findViewById(R.id.name_summary_xml);
        name.setText(bundle.getString("name") + "\'s Order");
        int arraysize = bundle.getInt("arraysize");
        for (int i = 0; i < arraysize; i++) {
//            String ch
        }
    }
}
