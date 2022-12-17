package umn.ac.id.project_map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class OrderPage extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    boolean isPermissionGranted;
    MapView mapView;
    private GoogleMap mGooglemap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;
    private LocationRequest mLocationRequest;
    private LatLng latLng;
    private Geocoder geocoder;
    private Button btnConfirmToCheckOut, backButton;

    private ArrayList<SelectLaundryItemModel> itemArrayList;
    private List<Address> addressList;
    private TextView address_name;
    private String address;
    private String orderId;
    private String orderType;
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String Uid = fAuth.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderId = String.valueOf(extras.getString("Outlet"));
            itemArrayList = (ArrayList<SelectLaundryItemModel>) extras.getSerializable("items");
            orderType = String.valueOf(extras.getString("Type"));

            Log.d("Isi dari order", String.valueOf(itemArrayList));
            Log.d("Isi dari order id", String.valueOf(orderId));
        } else {
            Log.d("Isi dari order id", "KOSONGGGG");

        }

        mapView = findViewById(R.id.mapView);
        address_name = findViewById(R.id.address);
        btnConfirmToCheckOut = findViewById(R.id.button_confirmToCheckout);
        btnConfirmToCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docref = fStore.collection("orders").document(Uid).collection("orders").document();
                Map<String, Object> user = new HashMap<>();
                user.put("address_longitude", address);
                user.put("date", FieldValue.serverTimestamp());
                user.put("laundry_type", orderType);
                user.put("outlet_id",orderId);
                user.put("status", "Ongoing");
                user.put("total_pants", itemArrayList.get(0).qty);
                user.put("total_price", 100000);
                user.put("total_shirts", itemArrayList.get(1).qty);

                Intent intent = new Intent(OrderPage.this, CheckOut.class);
                startActivity(intent);
                finish();
            }
        });
        backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        if (requestSinglePermission()){
            mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
            mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            checkLocation();
        }
    }

    @Override
    protected void onStart(){
        mapView.onStart();
        super.onStart();
        if(mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume(){
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause(){
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onStop(){
        mapView.onStop();
        super.onStop();

        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }
    @Override
    protected void onDestroy(){
        mapView.onDestroy();
        super.onDestroy();

    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState){
//        super.onSaveInstanceState(outState, outPersistentState);
//        mapView.onSaveInstanceState(outState);
//    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }
    private boolean checkLocation(){
        if(!isLocationEnabled()){
            showAlert();
        }
        return isLocationEnabled();
    }

    private void showAlert(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location").setMessage("Turn on location service for this app").
                setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled(){
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGooglemap = googleMap;
        mGooglemap.setMyLocationEnabled(true);
        if(latLng!=null){
            mGooglemap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mGooglemap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f));
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        String msg = "Update Location: " + Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(this, "Location Updated", Toast.LENGTH_SHORT).show();
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        try{
            geocoder = new Geocoder(this, Locale.getDefault());
            addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            address = addressList.get(0).getAddressLine(0);
            address_name.setText(address);
        }catch (Exception e){
            e.printStackTrace();
        }

        mapView = findViewById(R.id.mapView);
        mapView.getMapAsync(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startLocationUpdates();
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(mLocation == null){
            startLocationUpdates();
        }
        else{
            Toast.makeText(this, "Location is not detected", Toast.LENGTH_SHORT).show();
        }
    }

    private void startLocationUpdates() {
        mLocationRequest = com.google.android.gms.location.LocationRequest.create().setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private boolean requestSinglePermission(){
        Dexter.withActivity(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener(){
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response){
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                if(permissionDeniedResponse.isPermanentlyDenied()){
                    isPermissionGranted = false;
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

            }

        }).check();
        return isPermissionGranted;
    }
}
