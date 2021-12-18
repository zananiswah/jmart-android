package com.zanaJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zanaJmartAK.jmart_android.model.Product;
import com.zanaJmartAK.jmart_android.request.CreateProductRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is used to provide user when they want to add product(s) that haven't been on the list
 * @author Zana Niswah Awahita
 */

public class CreateProductActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static  Product product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_product);
        EditText nameInput = findViewById(R.id.InputNameCreate);
        EditText weightInput = findViewById(R.id.InputWeightCreate);
        EditText priceInput = findViewById(R.id.InputPriceCreate);
        EditText discountInput = findViewById(R.id.InputDiscountCreate);
        CheckBox newCheck = findViewById(R.id.NewRadiobutton);
        CheckBox usedCheck = findViewById(R.id.UsedRadiobutton);
        Spinner category = findViewById(R.id.SpinnerCategory);
        Spinner shipmentPlans = findViewById(R.id.SpinnerShipment);
        Button create = findViewById(R.id.ButtonCreate);
        Button clear = findViewById(R.id.ButtonCancel);

        newCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    usedCheck.setChecked(false);
                }
            }
        });

        usedCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    newCheck.setChecked(false);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput.setText("");
                weightInput.setText("");
                priceInput.setText("");
                discountInput.setText("");
                usedCheck.setChecked(false);
                newCheck.setChecked(false);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            product = gson.fromJson(object.toString(), Product.class);
                            Toast.makeText(CreateProductActivity.this,"Product Terdaftar",Toast.LENGTH_SHORT).show();
                            finish();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        System.out.println(product);
                    }
                };
                CreateProductRequest requestProduct = new CreateProductRequest(nameInput.getText().toString(),weightInput.getText().toString(),String.valueOf(newCheck.isChecked()),priceInput.getText().toString(),discountInput.getText().toString(),category.getSelectedItem().toString(),convertShipmentPlans(shipmentPlans.getSelectedItem().toString()),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(CreateProductActivity.this);
                requestQueue.add(requestProduct);
            }
        });
    }

    protected String convertShipmentPlans(String shipment){
        switch (shipment) {
            case "INSTANT":
                return "0";
            case "SAME DAY":
                return "1";
            case "NEXT DAY":
                return "2";
            case "REGULER":
                return "3";
            default:
                return "4";
        }
    }
}