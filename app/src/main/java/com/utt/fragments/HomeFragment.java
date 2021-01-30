package com.utt.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.utt.config.Config;
import com.utt.model.DataUser;
import com.utt.moonlight.CartsActivity;
import com.utt.moonlight.HomeActivity;
import com.utt.moonlight.R;
import com.utt.utils.SharedPreferencesObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;
    private OrderFragment fragment;
    private TextView txtNameHome;
    private ImageView imgAvatarHome, imgCart;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

//        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
//                .findFragmentById(R.id.map2d);
//        mapFragment.getMapAsync(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        SharedPreferences mPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        String user_token = mPrefs.getString("user_token", "");

        if (acct!=null){
            getProfileGGAccount();
        }
        if (!user_token.equals("")){
            getProfileUserFromApi(user_token);
        }

//        Bundle bundle = this.getArguments();
//        String a = bundle.getString("key");
//        Log.e("eyyy", a+"");

//        mMapView = (MapView) rootView.findViewById(R.id.mapView);
//        mMapView.onCreate(savedInstanceState);
//        mMapView.onResume();
//        try {
//            MapsInitializer.initialize(getActivity().getApplicationContext());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        mMapView.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap mMap) {
//                mMap = mMap;
//                LatLng local_1 = new LatLng(16.0555035, 108.2410071);
//                LatLng local_2 = new LatLng(16.072708, 108.220869);
//                LatLng local_3 = new LatLng(16.0555035, 108.2410071);
//
//                // For showing a move to my location button
//                mMap.setMyLocationEnabled(true);
//
//
//                mMap.addMarker(new MarkerOptions().position(local_1).title("Store 1").snippet("See more"));
//
//                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(local_1).zoom(12).build();
//                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//            }
//        });
//
        
        return rootView;
    }

    private void getProfileUserFromApi(String user_token) {
        Log.e("userToken", user_token+"");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgAvatarHome = (ImageView) view.findViewById(R.id.imgAvatarHome);
        imgCart = (ImageView) view.findViewById(R.id.imgCart);
        txtNameHome    = (TextView) view.findViewById(R.id.txtNameHome);
//        ImageView imgOrderBtn = (ImageView) view.findViewById(R.id.imgOrderBtn);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartsActivity.class);
                startActivity(intent);
            }
        });


//            DataUser dataUser = SharedPreferencesObject.getUserObjectPreferences(getActivity());
//            //String url = Config.API_URL+dataUser.getUrl();
//            txtNameHome.setText(dataUser.getName());
//            //Picasso.with(getContext()).load(url).into(imgAvatarHome);


//        imgOrderBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment = new OrderFragment();
//
//            }
//        });
    }


//    private void checkAccount(){
//        if (CheckGoogleAccountStatus.getcheckDataAccount(this) == true){
//            getProfileGGAccount();
//        }else{
//            btnLogout.setVisibility(View.GONE);
//            btnLogin.setVisibility(View.VISIBLE);
//            //Log.d("Login", "null");
//        }
//    }

    //gte google profile
    private void getProfileGGAccount(){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            String idToken =acct.getIdToken();
            String serverAuthCode = acct.getServerAuthCode();
//            Log.d("get_data_from_gg",
//                    "DisplayName: "+personName+
//                            "GivenName: "+ personGivenName+
//                            "FamilyName: "+personFamilyName+
//                            "Email: "+personEmail+
//                            "Id: "+personId+
//                            "PhotoUrl: "+personPhoto.toString()+
//                            "IdToken: "+idToken+
//                            "ServerAuthCode: "+ serverAuthCode);

            //set layout
            txtNameHome.setText(personName);
            Picasso.with(getContext()).load(String.valueOf(personPhoto)).into(imgAvatarHome);
//            tvNav_Email.setText(personEmail);
//            Glide.with(this).load(String.valueOf(personPhoto)).into(image_Avatar);
//            btnLogout.setVisibility(View.VISIBLE);
//            btnLogin.setVisibility(View.GONE);
            //saveAccount(personEmail);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
