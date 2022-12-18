package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Receipt extends AppCompatActivity {
    private Button btnBackHome;
    private Button btnBackOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        btnBackHome= findViewById(R.id.button_backHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Receipt.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnBackOrderList = findViewById(R.id.button_back);
        btnBackOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToOrderList = new Intent(Receipt.this, Order_list.class);
                startActivity(intentBackToOrderList);
                finish();
            }
        });
    }
}