package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Order_list extends AppCompatActivity {
    private RecyclerView rvOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);


        rvOrderList = findViewById(R.id.rvOrderList);

        ArrayList<Order_listMOdel> order_ListArrayList = new ArrayList<>();
        order_ListArrayList.add(new Order_listMOdel("Washing", "#43101", "03/12/22", "Rp 20.000"));
        order_ListArrayList.add(new Order_listMOdel("Washing", "#25501", "13/12/22", "Rp 37.000"));
        order_ListArrayList.add(new Order_listMOdel("Done", "#71961", "23/12/22", "Rp 43.000"));

        Order_listAdapter order_listAdapter = new Order_listAdapter(this, order_ListArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvOrderList.setLayoutManager(linearLayoutManager);
        rvOrderList.setAdapter(order_listAdapter);
    }
}