package com.simona.uber1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button driverBtn, customerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }


    @Override
    protected void onStart() {
        super.onStart();


    }


    private void initViews() {
        driverBtn = findViewById(R.id.driverBtn);
        customerBtn = findViewById(R.id.customerBtn);
        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDriverLoginActivity = new Intent(MainActivity.this, DriverLoginActivity.class);
                startActivity(toDriverLoginActivity);
                finish();
            }
        });

        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCustomerLoginActivity = new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(toCustomerLoginActivity);
                finish();
            }
        });
    }

}