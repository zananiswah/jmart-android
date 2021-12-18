package com.zanaJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.zanaJmartAK.jmart_android.model.Payment;
import com.zanaJmartAK.jmart_android.model.Payment;
import com.zanaJmartAK.jmart_android.request.PaymentRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Payment paid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);
        TextView name = (TextView) findViewById(R.id.nameProductPayment);
        TextView shipmentPlans = (TextView) findViewById(R.id.shipmentPlansPayment);
        TextView price = (TextView) findViewById(R.id.priceProductPayment);
        EditText productCount = (EditText) findViewById(R.id.payment_product_count);
        EditText address = (EditText) findViewById(R.id.address_payment);
        Button buy = (Button) findViewById(R.id.buy_payment);
        name.setText(fragment_product.productClicked.name);
        switch (fragment_product.productClicked.shipmentPlans) {
            case 0:
                shipmentPlans.setText("INSTANT");
                break;
            case 1:
                shipmentPlans.setText("SAME DAY");
                break;
            case 2:
                shipmentPlans.setText("NEXT DAY");
                break;
            case 3:
                shipmentPlans.setText("REGULER");
                break;
            default:
                shipmentPlans.setText("KARGO");
                break;
        }
        price.setText(String.valueOf(fragment_product.productClicked.price));
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object != null){
                                paid = gson.fromJson(object.toString(), Payment.class);
                                System.out.println(paid);
                                Intent intent = new Intent(PaymentActivity.this,MainActivity.class);
                                LoginActivity.loggedAccount.balance -= fragment_product.productClicked.price*Double.parseDouble(productCount.getText().toString())*(1 - fragment_product.productClicked.discount/100);
                                startActivity(intent);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                PaymentRequest paymentRequest = new PaymentRequest(productCount.getText().toString(),address.getText().toString(),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                requestQueue.add(paymentRequest);
            }
        });
    }
}
