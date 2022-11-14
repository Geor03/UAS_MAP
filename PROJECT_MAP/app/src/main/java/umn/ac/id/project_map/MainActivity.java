package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOrder = (Button) findViewById(R.id.button_order);

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
}
