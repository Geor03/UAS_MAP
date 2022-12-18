package umn.ac.id.project_map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHorizontal;
    private RecyclerView orderHorizontal;
    private ArrayList<OrderModel> orderArrayList;
    private ArrayList<CouponModel> couponArrayList;
    private RecyclerView.LayoutManager mLayoutManagerHorizontal;
    private RecyclerView.LayoutManager orderManagerHorizontal;
    CouponAdapter couponAdapter;
    OrderAdapter orderAdapter;

    private Button btnOrder;
    private Button sidebar;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    boolean mSlideState;

    private TextView fName;
    private Button btnViewAllCoupon;
    private Button btnViewAllOrder;

    String userID, orderID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fName = findViewById(R.id.name);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Button btnNotification = findViewById(R.id.Notification);

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, Notification.class));
                Intent intentToNotif = new Intent(MainActivity.this, Notification.class);
                startActivity(intentToNotif);
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    fName.setText("Hello, "+documentSnapshot.getString("fName"));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        btnOrder = (Button) findViewById(R.id.button_order);
        sidebar = (Button) findViewById(R.id.button_burger);
        mDrawer= (DrawerLayout)findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        mDrawer.setDrawerListener(new ActionBarDrawerToggle(this,
                mDrawer,
                0,
                0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mSlideState=false;//is Closed
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSlideState=true;//is Opened
            }});

        sidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSlideState){
                    mDrawer.closeDrawer(GravityCompat.START);
                }else{
                    mDrawer.openDrawer(GravityCompat.START);
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOrderButton = new Intent(MainActivity.this, SelectLaundry.class);
                startActivity(intentOrderButton);
            }
        });

        btnViewAllCoupon = findViewById(R.id.btnViewAlloupon);
        btnViewAllOrder = findViewById(R.id.btnViewAllorder);

        btnViewAllCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHomeToCouponList = new Intent(MainActivity.this, Coupon_list.class);
                startActivity(intentHomeToCouponList);
                finish();
            }
        });

        btnViewAllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHomeToOrderList = new Intent(MainActivity.this, Order_list.class);
                startActivity(intentHomeToOrderList);
                finish();
            }
        });


        //________initialize
        rvHorizontal = (RecyclerView) findViewById(R.id.rvHorizontal);
        orderHorizontal = (RecyclerView) findViewById(R.id.orderHorizontal);

        orderManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //________set layout managers
        orderHorizontal.setLayoutManager(orderManagerHorizontal);
        rvHorizontal.setLayoutManager(mLayoutManagerHorizontal);


        orderArrayList = new ArrayList<OrderModel>();
        couponArrayList = new ArrayList<CouponModel>();

        //________initialize adapters
        couponAdapter = new CouponAdapter(this, couponArrayList);
        orderAdapter = new OrderAdapter(orderArrayList, this);

        //________set adapters
        orderHorizontal.setAdapter(orderAdapter);
        rvHorizontal.setAdapter(couponAdapter);


        fetchData();
        EventChangeListener();

    }
    //kelar onCreate
    private void EventChangeListener(){
        fStore.collection("vouchers").orderBy("date", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                if(error != null){
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        couponArrayList.add(dc.getDocument().toObject(CouponModel.class));
                    }

                    couponAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void fetchData(){
        //_______add dummy status,order,date,price
        fStore.collection("users").document(userID).collection("orders").orderBy("address", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                if(error != null){
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        orderArrayList.add(dc.getDocument().toObject(OrderModel.class));
                    }
                    else{
                        Log.e("Empty Data", "Order is empty");
                        return;
                    }
                }
                orderAdapter.notifyDataSetChanged();

            }
        });

    }
    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    selectDrawerItem(menuItem);
                    return true;
                }
            });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Intent intent;
        switch(menuItem.getItemId()) {
            case R.id.home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.profile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
            case R.id.logout:
                logout();
                break;
            default:
        }

    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
