package com.utt.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.utt.moonlight.HomeActivity;
import com.utt.moonlight.InforStoreActivity;
import com.utt.moonlight.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GoogleMap mMap;
    private MapView mMapView;
    TextView txtAddressStore;

    public StoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);

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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = view.findViewById(R.id.mapView);
        if (mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }
        txtAddressStore = (TextView)view.findViewById(R.id.txtAddressStore);
        txtAddressStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InforStoreActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        LatLng loca1 = new LatLng(16.0555035, 108.2410071);
        LatLng loca2 = new LatLng(16.072708, 108.220869);
        LatLng loca3 = new LatLng(16.0555035, 108.2410071);
        addMarker(loca1);
//        mMap.setMyLocationEnabled(true);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(loca1, 15.f);
        mMap.animateCamera(cameraUpdate);
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            //Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
        }
    }

    private void addMarker(LatLng latLng){
        Marker mar = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Store 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location))
                .snippet("See more")
        );
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
