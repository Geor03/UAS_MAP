package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Order_list extends AppCompatActivity {
    private RecyclerView rvOrderList;
    private Button btnBackToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        btnBackToMain = findViewById(R.id.btnBackToMain);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToMainFromOderList = new Intent(Order_list.this, MainActivity.class);
                startActivity(intentBackToMainFromOderList);
                finish();
            }
        });

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