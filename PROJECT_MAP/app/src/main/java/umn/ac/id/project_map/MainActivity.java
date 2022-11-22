package umn.ac.id.project_map;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHorizontal;
    private RecyclerView orderHorizontal;
    private ArrayList<String> titleDataList;
    private ArrayList<String> messageDataList;
    private ArrayList<String> statusDataList;
    private ArrayList<String> orderidDataList;
    private ArrayList<String> dateOrderDataList;
    private ArrayList<String> priceOrderDataList;
    private RecyclerView.LayoutManager mLayoutManagerHorizontal;
    private RecyclerView.LayoutManager orderManagerHorizontal;
    private CouponAdapter couponAdapter;
    private OrderAdapter orderAdapter;
    private Button btnOrder;

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Button sidebar;
    boolean mSlideState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        //________initialize
        rvHorizontal = (RecyclerView) findViewById(R.id.rvHorizontal);
        orderHorizontal = (RecyclerView) findViewById(R.id.orderHorizontal);
        titleDataList = new ArrayList<>();
        messageDataList = new ArrayList<>();
        statusDataList = new ArrayList<>();
        orderidDataList = new ArrayList<>();
        dateOrderDataList = new ArrayList<>();
        priceOrderDataList = new ArrayList<>();

        //________add dummy titles and message
        for (int i = 1; i <= 20; i++) {
            titleDataList.add("Title " + i);
            messageDataList.add("message " + i);
        }

        //_______add dummy status,order,date,price
        for(int k = 1; k <= 20; k++){
            statusDataList.add("Status " + k);
            orderidDataList.add("Order ID: " + k);
            dateOrderDataList.add("oct : " + k);
            priceOrderDataList.add("Rp " + k + ".000");
        }

        //________initialize adapters
        couponAdapter = new CouponAdapter(titleDataList, messageDataList);
        orderAdapter = new OrderAdapter(statusDataList, orderidDataList, dateOrderDataList, priceOrderDataList);

        //________initialize layout managers
        orderManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


        //________set layout managers
        orderHorizontal.setLayoutManager(orderManagerHorizontal);
        rvHorizontal.setLayoutManager(mLayoutManagerHorizontal);

        //________set adapters
        orderHorizontal.setAdapter(orderAdapter);
        rvHorizontal.setAdapter(couponAdapter);
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
                break;
            case R.id.profile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
            case R.id.logout:
                logout();
                break;
            default:
//                fragmentClass = MainActivity.class;
        }

    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}
