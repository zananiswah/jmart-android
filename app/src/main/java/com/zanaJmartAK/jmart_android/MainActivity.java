package com.zanaJmartAK.jmart_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

/**
 * This is used to handle the 3 main activity shows in the menu bar, that is search, create new product, and about me
 * @author Zana Niswah Awahita
 */

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragment_product(), "PRODUCT");
        vpAdapter.addFragment(new fragment_filter(), "FILTER");
        viewPager.setAdapter(vpAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Toast.makeText(this, "Search Selected", Toast.LENGTH_SHORT);
        }
        if (item.getItemId() == R.id.CreateProduct) {
            Toast.makeText(this, "Create Selected", Toast.LENGTH_SHORT);
            Intent intent = new Intent(MainActivity.this, CreateProductActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.AboutMe) {
            Toast.makeText(this, "About Me Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.PersonalHistory) {
            Toast.makeText(this, "Personal History Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, InvoiceActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}