package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Coupon_list extends AppCompatActivity {

    private Button btnBackToMain;
    private RecyclerView rvHorizontal;
    private ArrayList<CouponModel> couponArrayList;
    private RecyclerView.LayoutManager mLayoutManagerHorizontal;
    CouponAdapter couponAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToHomeFromCouponList = new Intent(Coupon_list.this, MainActivity.class);
                startActivity(intentBackToHomeFromCouponList);
                finish();
            }
        });

        rvHorizontal = (RecyclerView) findViewById(R.id.rvHorizontal);
        mLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHorizontal.setLayoutManager(mLayoutManagerHorizontal);
        couponArrayList = new ArrayList<CouponModel>();
        rvHorizontal.setAdapter(couponAdapter);

    }
}