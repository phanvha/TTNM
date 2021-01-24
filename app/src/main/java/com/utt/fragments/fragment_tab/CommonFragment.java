package com.utt.fragments.fragment_tab;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.utt.API_Interface.Product_interface;
import com.utt.API_clients.API_clients;
import com.utt.Adapter.GridCommonAdapter;
import com.utt.model.DataProducts;
import com.utt.model.ProductResponse;
import com.utt.moonlight.HomeActivity;
import com.utt.moonlight.MainActivity;
import com.utt.moonlight.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CommonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CommonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommonFragment extends Fragment implements GridCommonAdapter.OnShareClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GridView gridView;
    String[] nameProduct = {"Trà sữa trân châu đường đen","Trà sữa trân châu đường trắng","Trà sữa trân châu ít đường","Trà sữa trân châu ít đường","Trà sữa trân châu ít đường","Trà sữa trân châu ít đường","Trà sữa trân châu ít đường"};
    int[] imgproduct = {R.drawable.viewhouse,R.drawable.viewhouse,R.drawable.viewhouse,R.drawable.viewhouse,R.drawable.viewhouse,R.drawable.viewhouse,R.drawable.viewhouse};

    ImageView imgProduct, imgAddToCart;
    TextView txtNameProduct, txtPrice;
    GridCommonAdapter gridCommonAdapter;
    RecyclerView recyclerView;
    List<DataProducts> dataProductsList = new ArrayList<>();
    Dialog dialog;

    private OnFragmentInteractionListener mListener;
    private ProgressDialog progressDialog;

    public CommonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommonFragment newInstance(String param1, String param2) {
        CommonFragment fragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        gridView = view.findViewById(R.id.gridView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCommonDrink);
        getDataProducts();
        //new readData().execute();
        statusData();
//        CommonAdapter commonAdapter = new CommonAdapter();
//        gridView.setAdapter(gridCommonAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }

    private void showDialog(){
//        if (list == null){
//            return;
//        }
        final Dialog dialog;
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    @Override
    public void ShareDesClicked(boolean position) {
        if (position){
            dialog.dismiss();
        }

    }
    private void statusData(){

        View view;
        dialog = new Dialog(getActivity());
        view  = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_loading, null);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.show();

    }

    class readData extends AsyncTask<Void, Void, String> {
        // private ProgressDialog dialog;
        Dialog dialog;
        View view;
        ProgressDialog progressDialog ;

        @Override
        protected void onPreExecute() {
            // This progressbar will load util tast in doInBackground method loads
//            dialog = new Dialog(getActivity());
//            view  = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_loading, null);
//            dialog.setContentView(view);
//
////            Window window = dialog.getWindow();
////            window.setGravity(Gravity.CENTER);
////            window.getAttributes().windowAnimations = R.style.DialogAnimation;
//            dialog.setCancelable(true);
//            //window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
//            dialog.show();

            progressDialog = new ProgressDialog(getActivity(), R.style.SpinKitView_Large_Circle);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setMessage("loading...");

        }

        @Override
        protected String doInBackground(Void... params) {
            //setMyLocation();
            //getAllUser();
            getDataProducts();
//            checkAccount();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }
    }

    private void getDataProducts(){
        Product_interface service = API_clients.getClient().create(Product_interface.class);
        Call<ProductResponse> userCall = service.getAllProducts();
        userCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                //onSignupSuccess();
                if (response.isSuccessful()) {
                    if (response.body()!=null){
                        Log.e("commonfragment", ""+response.body().getCode());
                        ProductResponse productResponse = response.body();


                        dataProductsList = response.body().getDataProductsArrayList();
                        if(dataProductsList!=null){
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                            gridCommonAdapter = new GridCommonAdapter(dataProductsList,getContext(), CommonFragment.this);
                            recyclerView.setAdapter(gridCommonAdapter);
                            gridCommonAdapter.notifyDataSetChanged();
                        }else{
                            Log.e("get data: ", "null!");
                        }



//                        View view = findViewById(R.id.layer1);
//                        Snackbar.make(view, "Data updated! "+db.getTotalPotholeTB(), Snackbar.LENGTH_SHORT)
//                                .setAction("Action", null).show();

                    }
                    //Toast.makeText(getApplicationContext(), "Tải dữ liệu thành công !", Toast.LENGTH_SHORT).show();

                }else {
                    //Toast.makeText(getApplicationContext(), "không thành công !", Toast.LENGTH_SHORT).show();
                    Log.e("get data: ", "không thành công !");
                }

            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("Failed: ", t.toString());
            }
        });
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
