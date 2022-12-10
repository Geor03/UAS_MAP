package umn.ac.id.project_map;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class OrderPage extends AppCompatActivity implements OnMapReadyCallback{
    boolean isPermissionGranted;
    MapView mapView;
    private Button btnConfirmToCheckOut;
    private ArrayList<SelectLaundryItemModel> orderArrayList;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page);
        Intent intent=getIntent();
        Log.d("Isi dari order id", intent.getStringExtra("outlet"));
//        orderId = intent.getStringExtra("id_outlet");
        Log.d("Isi dari order id", String.valueOf(orderId));

        orderArrayList = intent.getParcelableArrayListExtra("jumlah_item");

        mapView = findViewById(R.id.mapView);
        btnConfirmToCheckOut = findViewById(R.id.button_confirmToCheckout);
        btnConfirmToCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        checkMyPermission();
//        if(isPermissionGranted){
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
    }

    public void showMore(View view){

    }


    @Override
    protected void onStart(){
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onStop(){
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState){
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
    }
}
