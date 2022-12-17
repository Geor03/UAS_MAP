package umn.ac.id.project_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckOut extends AppCompatActivity {

    private Button button_back, button_trackOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        button_back = findViewById(R.id.button_back);
        button_trackOrder = findViewById(R.id.button_trackOrder);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBackToHomeFromeCheckout = new Intent(CheckOut.this, MainActivity.class);
                startActivity(intentBackToHomeFromeCheckout);
                finish();
            }
        });

        button_trackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToTrackOrder = new Intent(CheckOut.this, Track_Order.class);
                startActivity(intentToTrackOrder);
                finish();
            }
        });
    }
}