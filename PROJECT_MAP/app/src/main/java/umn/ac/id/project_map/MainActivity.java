package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHorizontal;
    private ArrayList<String> titleDataList;
    private ArrayList<String> messageDataList;
    private RecyclerView.LayoutManager mLayoutManagerHorizontal;
    private CouponAdapter horizontalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //________initialize
        rvHorizontal = (RecyclerView) findViewById(R.id.rvHorizontal);
        titleDataList = new ArrayList<>();
        messageDataList = new ArrayList<>();

        //________add dummy titles and message
        for (int i = 1; i <= 20; i++) {
            titleDataList.add("Title " + i);
            messageDataList.add("message " + i);
        }

        //________initialize adapters
        horizontalAdapter = new CouponAdapter(titleDataList, messageDataList);

        //________initialize layout managers
        mLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //________set layout managers
        rvHorizontal.setLayoutManager(mLayoutManagerHorizontal);

        //________set adapters
        rvHorizontal.setAdapter(horizontalAdapter);
    }
}
