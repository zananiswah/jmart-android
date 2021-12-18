package com.zanaJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.zanaJmartAK.jmart_android.model.Product;
import com.zanaJmartAK.jmart_android.model.ProductCategory;

/**
 * This is used to provide information about the product that clicked by the user
 * @author Zana Niswah Awahita
 */

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_activity);

        TextView name = findViewById(R.id.productName);
        TextView weight = findViewById(R.id.weightProduct);
        TextView conditionProduct = findViewById(R.id.conditionProduct);
        TextView price = findViewById(R.id.priceProduct);
        TextView discount = findViewById(R.id.discountProduct);
        TextView category =  findViewById(R.id.categoryProduct);
        TextView shipmentPlans = findViewById(R.id.shipmentPlanProduct);
        Button checkOut = findViewById(R.id.checkout_button);

        name.setText(fragment_product.productClicked.name);
        weight.setText(String.valueOf(fragment_product.productClicked.weight));
        if(fragment_product.productClicked.conditionUsed = false){
            conditionProduct.setText("Used");
        }
        else{
            conditionProduct.setText("New");
        }
        price.setText(String.valueOf(fragment_product.productClicked.price));
        discount.setText(String.valueOf(fragment_product.productClicked.discount));
        category.setText(String.valueOf(fragment_product.productClicked.category));
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

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}