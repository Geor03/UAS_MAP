package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Coupon_list extends AppCompatActivity {
    private RecyclerView couponVertcal;
    private ArrayList<String> titleDataList;
    private ArrayList<String> messageDataList;
    private RecyclerView.LayoutManager couponManagerVertical;
    private CouponAdapter couponAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        //________initialize
        couponVertcal = (RecyclerView) findViewById(R.id.couponVertical);
        titleDataList = new ArrayList<>();
        messageDataList = new ArrayList<>();

        //________add dummy titles and message
        for (int i = 1; i <= 20; i++) {
            titleDataList.add("Title " + i);
            messageDataList.add("message " + i);
        }

        //________initialize adapters
        couponAdapter = new CouponAdapter(titleDataList, messageDataList);

        //________initialize layout managers
        couponManagerVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        //________set layout managers
        couponVertcal.setLayoutManager(couponManagerVertical);

        //________set adapters
        couponVertcal.setAdapter(couponAdapter);
    }
}