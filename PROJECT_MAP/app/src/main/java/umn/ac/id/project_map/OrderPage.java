package umn.ac.id.project_map;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;


public class OrderPage extends AppCompatActivity implements OnMapReadyCallback{
    boolean isPermissionGranted;
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page);

        mapView = findViewById(R.id.mapView);
//        checkMyPermission();
        if(isPermissionGranted){
            mapView.getMapAsync(this);
            mapView.onCreate(savedInstanceState);
        }
    }

//    private void checkMyPermission(){
//        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener(){
//            @Override
//            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse){
//                Toast.makeText(OrderPage.this, "Permission Granted", Toast.LENGTH_SHORT).show();
//                isPermissionGranted = true;
//            }
//            @Override
//            public void onPermissionDenied(PermissionDeniedResponse premissionDeniedResponse){
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package", getPackageName(), "");
//            }
//            @Override
//            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken){
//                permissionToken.continuePermissionRequest();
//            }
//        }).check();
//    }
    @Override
    public void onMapReady(GoogleMap googleMap){

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
}
