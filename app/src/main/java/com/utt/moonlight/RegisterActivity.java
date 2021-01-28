package com.utt.moonlight;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.utt.utils.BlurBuilder;

public class RegisterActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);


        relativeLayout = (RelativeLayout) findViewById(R.id.mContentView);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_lg2);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        relativeLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));


    }
}
