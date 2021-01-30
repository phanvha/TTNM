package com.utt.moonlight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.utt.API_Interface.Product_interface;
import com.utt.API_Interface.User_interface;
import com.utt.API_clients.API_clients;
import com.utt.Adapter.GridCommonAdapter;
import com.utt.fragments.HomeFragment;
import com.utt.fragments.fragment_tab.CommonFragment;
import com.utt.model.DataUser;
import com.utt.model.ProductResponse;
import com.utt.model.UserResponse;
import com.utt.utils.BlurBuilder;
import com.utt.utils.SharedPreferencesObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;

    Button btn_sign_in, btn_sign_up;
    private BottomSheetDialog bottomSheetDialog1, bottomSheetDialog2;
    private View bottomSheetViewLogin, bottomSheetViewRegis;
    private String mail, pass;
//    private int  ;
    private String mail_reg, name_reg,pass_reg , imagePath_reg="", phonenumber_reg;
    public GoogleSignInClient googleSignInClient;
    GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN_GOOGLE = 101;
    private String TAG = "WaitActivity";
    private int REQUEST_CODE_CAMERA = 211;
    private int REQUEST_CODE_FOLDER = 221;
    private ImageView imgImageSource;
    private static final int MY_CAMERA_REQUEST_CODE = 10;
    private AlertDialog alertDialog;
    private int WRITE_REQUEST_CODE = 35, READ_REQUEST_CODE = 53;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        initLayout();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(WaitActivity.this, gso);


    }

    private void initLayout(){
        relativeLayout = (RelativeLayout) findViewById(R.id.mContent);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_lg2);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        relativeLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomDialogLogin();
            }
        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomDialogRegister();
            }
        });
    }

    private void openBottomDialogLogin(){
        bottomSheetDialog1  = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        bottomSheetViewLogin = LayoutInflater.from(WaitActivity.this)
                .inflate(
                        R.layout.bottom_sheet_view_login,
                        (LinearLayout) findViewById(R.id.bottom_sheets_view_login)
                );
        final EditText edEmail = (EditText) bottomSheetViewLogin.findViewById(R.id.edEmail);
        final EditText edPass = (EditText) bottomSheetViewLogin.findViewById(R.id.edPassword);
        bottomSheetViewLogin.findViewById(R.id.btn_sign_in_push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = edEmail.getText().toString().trim();
                pass = edPass.getText().toString().trim();
                Log.e("results", "e= "+mail+", p= "+pass);
                if (mail.equals("") && pass.equals("")){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email hoặc password !", Toast.LENGTH_SHORT).show();
                }else{
                    postLogin(mail, pass);
                }
            }
        });
        bottomSheetViewLogin.findViewById(R.id.imgGoogleLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        bottomSheetDialog1.setContentView(bottomSheetViewLogin);
        bottomSheetDialog1.show();
    }

    private void openBottomDialogRegister(){
        bottomSheetDialog2  = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        bottomSheetViewRegis = LayoutInflater.from(WaitActivity.this)
                .inflate(
                        R.layout.bottom_sheet_view_reg,
                        (LinearLayout) findViewById(R.id.bottom_sheets_view_register)
                );

        final EditText edEmail = (EditText) bottomSheetViewRegis.findViewById(R.id.edEmail_reg);
        final EditText edName = (EditText) bottomSheetViewRegis.findViewById(R.id.edName_reg);
        final EditText edPhoneNumber = (EditText) bottomSheetViewRegis.findViewById(R.id.edPhoneNumber_reg);
        final EditText edPass = (EditText) bottomSheetViewRegis.findViewById(R.id.edPassword_reg);
        imgImageSource = (ImageView)bottomSheetViewRegis.findViewById(R.id.imgAvatarSource);

        bottomSheetViewRegis.findViewById(R.id.txtOpenImageSource).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCheckImage();
            }
        });

        bottomSheetViewRegis.findViewById(R.id.btn_sign_up_push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail_reg = edEmail.getText().toString().trim();
                pass_reg = edPass.getText().toString().trim();
                phonenumber_reg = edPhoneNumber.getText().toString().trim();
                name_reg = edName.getText().toString().trim();
                Log.e("ress", ""+name_reg+", "+ mail_reg+", " +phonenumber_reg+", "+ pass_reg+", ");
                new pushRegister().execute();
            }
        });

        bottomSheetDialog2.setContentView(bottomSheetViewRegis);
        bottomSheetDialog2.show();
    }

    private void openCheckImage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(WaitActivity.this);
        builder.setMessage("Choose type")
                .setIcon(R.drawable.ic_help)
                .setCancelable(false)
                .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CODE_CAMERA);
                    }
                })
                .setNegativeButton("Open folder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        Intent result = Intent.createChooser(intent,"Choose Image");
                        startActivityForResult(result,REQUEST_CODE_FOLDER);
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();

    }

    private void postLogin(String email, String password){
        User_interface service = API_clients.getClient().create(User_interface.class);
        Call<UserResponse> userCall = service.postLogin(email, password);
        userCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                //onSignupSuccess();
                if (response.isSuccessful()) {
                    if (response.body()!=null){
                        Log.e("commonfragment", ""+response.body().getCode());
                        DataUser dataUser = response.body().getDataUser();
                        //SharedPreferencesObject.storeUserObject(WaitActivity.this, dataUser);
                        SharedPreferences mPrefs = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//                        Gson gson = new Gson();
//                        String json = gson.toJson(dataUser);
                        prefsEditor.putString("user_token", dataUser.getApi_token()+"");
                        prefsEditor.apply();
                        Intent intent = new Intent(WaitActivity.this, HomeActivity.class);
                        intent.putExtra("key","1");
                        startActivity(intent);
                        View view = findViewById(R.id.btn_sign_in);
                        Snackbar.make(view, "Successfully! ", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                    }
                    //Toast.makeText(getApplicationContext(), "Tải dữ liệu thành công !", Toast.LENGTH_SHORT).show();

                }else {
                    //Toast.makeText(getApplicationContext(), "không thành công !", Toast.LENGTH_SHORT).show();
                    Log.e("get data: ", "không thành công !");
                }

            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("Failed: ", t.toString());
            }
        });
    }

    private void postRegister(String name,String email, int phoneNumber,String password, String imagePath){

        File file;
        String imgname = "";
        if (imagePath!=null) {
            Log.e(TAG, "imagepath");
            file = new File(imagePath);
        }else{
            return;
        }
        RequestBody photoContent = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("avatar",file.getName(),photoContent);
        imgname = file.getName();

        User_interface service = API_clients.getClient().create(User_interface.class);
        Log.e("mms", name+", "+email+", " + phoneNumber+", "+ password+", "+imagePath);
        Call<UserResponse> userCall = service.postRegister(name,email, phoneNumber, password, photo);
        userCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                //onSignupSuccess();
                if (response.isSuccessful()) {
                    if (response.body()!=null){
                        Log.e("commonfragment", ""+response.body().getCode());


                        View view = findViewById(R.id.btn_sign_up);
                        Snackbar.make(view, "Successfully! ", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                    }
                    //Toast.makeText(getApplicationContext(), "Tải dữ liệu thành công !", Toast.LENGTH_SHORT).show();

                }else {
                    //Toast.makeText(getApplicationContext(), "không thành công !", Toast.LENGTH_SHORT).show();
                    Log.e("get data: ", "không thành công !");
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("Failed: ", t.toString());
            }
        });
    }

    class pushRegister extends AsyncTask<Void, Void, String> {
        // private ProgressDialog dialog;
        Dialog dialog;
        View view;
        ProgressDialog progressDialog ;

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(WaitActivity.this, R.style.SpinKitView_Large_Circle);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        }

        @Override
        protected String doInBackground(Void... params) {
            //setMyLocation();
            //getAllUser();
            int phone = Integer.valueOf(phonenumber_reg);
            postRegister(name_reg, mail_reg, phone, pass_reg, imagePath_reg);
//            checkAccount();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "camera permission granted");
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            verifyStoragePermissions(this);

        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
    }




    private void signOutAccountGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                        //Toast.makeText(getApplicationContext(), "Đăng xuất thành công !", Toast.LENGTH_SHORT).show();
                        recreate();
//                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                        startActivityForResult(intent, RC_MAIN);
//                        finish();
                        //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    }
                });

    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == RC_SIGN_IN_GOOGLE) {
                Log.e("this", "Login with google account!");
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }else if (requestCode == REQUEST_CODE_CAMERA) {

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imagePath_reg = getRealPathFromURI(getImageUri(this, bitmap));
                imgImageSource.setImageBitmap(bitmap);

            }
            else if (requestCode == REQUEST_CODE_FOLDER){
                Uri uri = data.getData();
                imagePath_reg = getRealPathFromURI(uri);
                imgImageSource.setImageURI(uri);

            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Uri contentUri){
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),contentUri,proj,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    private void bubbleToNextLayout(){

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Log.e("request","Đăng nhập thành công!");
            updateUI_AfterLoginGG(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    //update ui after login to GG
    private void updateUI_AfterLoginGG(GoogleSignInAccount account) {
        try {
            Intent intent = new Intent(WaitActivity.this, HomeActivity.class);
            startActivity(intent);
            String strData = "" + account.getDisplayName();
            //Toast.makeText(getApplicationContext(),strData,Toast.LENGTH_LONG).show();
            Log.e("Name",strData);
            //recreate();
            //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        } catch (Exception ex) { }
    }


    class pushLogin extends AsyncTask<Void, Void, String> {
        // private ProgressDialog dialog;
        Dialog dialog;
        View view;

        @Override
        protected void onPreExecute() {

            dialog = new Dialog(WaitActivity.this);
            view  = getLayoutInflater().inflate(R.layout.layout_dialog_loading, null);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(view);
            dialog.setCancelable(true);
            dialog.show();

        }


        @Override
        protected String doInBackground(Void... params) {
            //postLogin();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
        }
    }

}
