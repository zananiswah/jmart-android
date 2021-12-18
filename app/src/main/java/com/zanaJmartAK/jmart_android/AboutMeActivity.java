package com.zanaJmartAK.jmart_android;

import static com.zanaJmartAK.jmart_android.LoginActivity.loggedAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zanaJmartAK.jmart_android.model.Store;
import com.zanaJmartAK.jmart_android.request.RegisterStoreRequest;
import com.zanaJmartAK.jmart_android.request.TopUpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * This is used for serving information about our account that has been registered, to register new store and store the store information
 * and also invoice of the store
 * @author Zana Niswah Awahita
 */

public class AboutMeActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    Store store = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.about_me_activity);

        TextView name = findViewById(R.id.outputNameAbout);
        name.setText("" + LoginActivity.getLoggedAccount().name);
        TextView email = findViewById(R.id.outputEmailAbout);
        email.setText("" + LoginActivity.getLoggedAccount().email);
        TextView balance = findViewById(R.id.outputBalanceAbout);
        balance.setText("Rp. " + LoginActivity.getLoggedAccount().balance);

        EditText topUpInput =  findViewById(R.id.topupInputAbout);
        EditText storeName =  findViewById(R.id.inputNameRegisterStore);
        EditText storeAddress =  findViewById(R.id.inputAddressRegisterStore);
        EditText storePhone =  findViewById(R.id.inputPhoneRegisterStore);

        Button buttonTopUp = findViewById(R.id.topupButtonAbout);
        Button registerButton = findViewById(R.id.registerStoreButtonAbout);
        Button registerStore = findViewById(R.id.buttonRegisterStore);
        Button cancelRegister = findViewById(R.id.cancelRegisterStore);
        Button buttonStoreHistory = findViewById(R.id.buttonStoreHistory);

        CardView cardRegister = findViewById(R.id.cardRegisterAbout);
        CardView cardStore = findViewById(R.id.cardStoreAbout);

        registerButton.setVisibility(View.GONE);
        cardRegister.setVisibility(View.GONE);
        cardStore.setVisibility(View.GONE);

        if (LoginActivity.getLoggedAccount().store == null){
            registerButton.setVisibility(View.VISIBLE);
        }
        else if (LoginActivity.getLoggedAccount().store != null) {
            TextView dataName = (TextView) findViewById(R.id.dataNameTextAbout);
            dataName.setText("" + LoginActivity.getLoggedAccount().store.name);
            TextView dataAddress = (TextView) findViewById(R.id.dataAddressTextAbout);
            dataAddress.setText("" + LoginActivity.getLoggedAccount().store.address);
            TextView dataPhone = (TextView) findViewById(R.id.dataPhoneTextAbout);
            dataPhone.setText("" + LoginActivity.getLoggedAccount().store.phoneNumber);
            cardStore.setVisibility(View.VISIBLE);
        } else {
            registerButton.setVisibility(View.VISIBLE);
        }

        topUpInput.setText("0");

        buttonTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Boolean.parseBoolean(response)){
                            Toast.makeText(AboutMeActivity.this, "Topup berhasil!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AboutMeActivity.this, "Topup error!", Toast.LENGTH_SHORT).show();
                        }
                        LoginActivity.loggedAccount.balance += Double.parseDouble(topUpInput.getText().toString());
                        finish();
                        startActivity(getIntent());
                    }
                };
                TopUpRequest TopUpRequest = new TopUpRequest(LoginActivity.getLoggedAccount().id, Double.parseDouble(topUpInput.getText().toString()), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(TopUpRequest);
            }
        });

        registerButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton.setVisibility(View.GONE);
                cardRegister.setVisibility(View.VISIBLE);
                cancelRegister.setOnClickListener (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardRegister.setVisibility(View.GONE);
                        registerButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        registerStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            LoginActivity.loggedAccount.store = gson.fromJson(object.toString(),Store.class);
                            System.out.println(LoginActivity.loggedAccount.store);
                            Toast.makeText(AboutMeActivity.this, "Create Store Success!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }catch (JSONException e){
                            Toast.makeText(AboutMeActivity.this, "Create Store Failed!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };
                RegisterStoreRequest request = new RegisterStoreRequest(LoginActivity.getLoggedAccount().id,storeName.getText().toString(),storeAddress.getText().toString(),storePhone.getText().toString(),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(request);
            }
        });

        buttonStoreHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutMeActivity.this, StoreInvoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
