package com.utt.moonlight;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.utt.config.Config;
import com.utt.paypal.PaymentDetails;

import java.math.BigDecimal;

public class PaymentActivity extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration configuration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    Button btnPayNow;
    EditText edAmount;
    String amount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //start paypal service
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        startService(intent);

        btnPayNow = (Button) findViewById(R.id.btnPayNow);
        edAmount = (EditText) findViewById(R.id.edit_amount);

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });


    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void processPayment(){
        amount = edAmount.getText().toString();
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount))
                ,"USD"
                ,"Donate for MoonLight",PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, com.paypal.android.sdk.payments.PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE){
            if (resultCode== RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null){
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("PaymentDetails",paymentDetails)
                                .putExtra("PaymentAmount", amount));


                    }catch (Exception e){

                    }
                }
            }else if (resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"Cancel", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(getApplicationContext(),"Invalid", Toast.LENGTH_SHORT).show();
        }

    }
}
