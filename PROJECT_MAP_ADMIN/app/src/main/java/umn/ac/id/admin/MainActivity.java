package umn.ac.id.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button btnSidebar;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    boolean mSlideState;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        name = (TextView) findViewById(R.id.name);
        Intent intent = getIntent();
        name.setText("Hello, "+intent.getStringExtra("Name"));
//        replaceFragment(new TerimaFragment());

        //    Sidebar Code
        btnSidebar = findViewById(R.id.button_burger);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        mDrawer.setDrawerListener(new ActionBarDrawerToggle(this,
                mDrawer,
                0,
                0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mSlideState = false;
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSlideState = true;
            }
        });

        btnSidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSlideState){
                    mDrawer.closeDrawer(GravityCompat.START);
                }else{
                    mDrawer.openDrawer(GravityCompat.START);
                }
            }
        });

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                    new TerimaFragment()).commit();
            nvDrawer.setCheckedItem(R.id.nav_terimaPesanan);
        }


    }
//    fragment_berlangsung_pesanan
    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                switch (item.getItemId()){
                    case R.id.nav_terimaPesanan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                new TerimaFragment()).commit();
                    break;
                    case R.id.nav_pesananBerlangsung:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                new BerlangsungPesananFragment()).commit();
                        break;
                    case R.id.nav_PesananSelesai:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                new PesananSelesaiFragment()).commit();
                        break;
                    case R.id.nav_profileAdmin:
                        Intent intentProfileToko = new Intent(MainActivity.this, ProfileToko.class);
                        startActivity(intentProfileToko);
                        break;
                }
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.drawer_menuadminsidebar, menu);
//        for (int i = 0; i < menu.size(); i++){
//            MenuItem menuItem = menu.getItem(i);
//        }
//
//    }

    //    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout,fragment);
//        fragmentTransaction.commit();
//    }


}