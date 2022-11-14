package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHorizontal;
    private RecyclerView orderHorizontal;
    private ArrayList<String> titleDataList;
    private ArrayList<String> messageDataList;
    private RecyclerView.LayoutManager mLayoutManagerHorizontal;
    private RecyclerView.LayoutManager orderManagerHorizontal;
    private CouponAdapter couponAdapter;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //________initialize
        rvHorizontal = (RecyclerView) findViewById(R.id.rvHorizontal);
        orderHorizontal = (RecyclerView) findViewById(R.id.orderHorizontal);
        titleDataList = new ArrayList<>();
        messageDataList = new ArrayList<>();

        //________add dummy titles and message
        for (int i = 1; i <= 20; i++) {
            titleDataList.add("Title " + i);
            messageDataList.add("message " + i);
        }

        //________initialize adapters
        couponAdapter = new CouponAdapter(titleDataList, messageDataList);
        orderAdapter = new OrderAdapter(titleDataList, messageDataList);

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
}
