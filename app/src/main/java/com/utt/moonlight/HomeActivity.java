package com.utt.moonlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.accounts.AccountManager;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.utt.API_Interface.Product_interface;
import com.utt.API_clients.API_clients;
import com.utt.fragments.AccountFragment;
import com.utt.fragments.HomeFragment;
import com.utt.fragments.OrderFragment;
import com.utt.fragments.StoreFragment;
import com.utt.fragments.fragment_tab.CommonFragment;
import com.utt.fragments.fragment_tab.DrinkFragment;
import com.utt.fragments.fragment_tab.FoodFragment;
import com.utt.model.DataProducts;
import com.utt.model.ProductResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, OrderFragment.OnFragmentInteractionListener, StoreFragment.OnFragmentInteractionListener, AccountFragment.OnFragmentInteractionListener,
        CommonFragment.OnFragmentInteractionListener, DrinkFragment.OnFragmentInteractionListener, FoodFragment.OnFragmentInteractionListener {

    Fragment fragment = null;
    public static ImageView imgNewsLayout, imgOrderlayout, imgStoreLayout, imgAccountLayout;
    private String TAG = "HomeActivity";
    FragmentTransaction ft;
    List<DataProducts> productsList = new ArrayList<>();
    ProductResponse productResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initLayout();
        // Begin the transaction
        Intent intent = getIntent();
        int key = intent.getIntExtra("key",0);
        Log.e("keyy", ""+key);
        ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle=new Bundle();
        bundle.putInt("key",key);

        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, new HomeFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
        fragment = new HomeFragment();
        fragment.setArguments(bundle);

//        final Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            public void run() {
//
//                new readData().execute();
//            }
//        };
//        handler.postDelayed(r, 3000);

    }

    private void initLayout() {
        imgNewsLayout = (ImageView) findViewById(R.id.imgNewsLayout);
        imgOrderlayout = (ImageView) findViewById(R.id.imgOrderLayout);
        imgStoreLayout = (ImageView) findViewById(R.id.imgStoreLayout);
        imgAccountLayout = (ImageView) findViewById(R.id.imgAccountLayout);

        imgNewsLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_1),
                PorterDuff.Mode.MULTIPLY);
        imgOrderlayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                PorterDuff.Mode.MULTIPLY);
        imgStoreLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                PorterDuff.Mode.MULTIPLY);
        imgAccountLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                PorterDuff.Mode.MULTIPLY);

        imgNewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNewsLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_1),
                        PorterDuff.Mode.MULTIPLY);
                imgOrderlayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgStoreLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgAccountLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                loadFragment(new HomeFragment());
                Log.d(TAG, "news fragment");


            }
        });
        imgOrderlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNewsLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgOrderlayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_1),
                        PorterDuff.Mode.MULTIPLY);
                imgStoreLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgAccountLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                loadFragment(new OrderFragment());
                Log.d(TAG, "order fragment");

            }
        });
        imgStoreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNewsLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgOrderlayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgStoreLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_1),
                        PorterDuff.Mode.MULTIPLY);
                imgAccountLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);

                loadFragment( new StoreFragment());
                Log.d(TAG, "store fragment");

            }
        });
        imgAccountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgNewsLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgOrderlayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgStoreLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_4),
                        PorterDuff.Mode.MULTIPLY);
                imgAccountLayout.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.color_basic_1),
                        PorterDuff.Mode.MULTIPLY);
                loadFragment(new AccountFragment());
                Log.d(TAG, "account fragment");

            }
        });

    }

    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        ft = getSupportFragmentManager().beginTransaction();
        // replace the FrameLayout with new Fragment
        ft.replace(R.id.fragment_container, fragment);
        ft.commit(); // save the changes
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
