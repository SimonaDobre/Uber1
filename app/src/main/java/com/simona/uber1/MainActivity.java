package com.simona.uber1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    EditText inputFirstNameED, inputLastNameED, inputPhoneED, inputEmailED, inputPasswordED;
    Button registerBtn;
    DatabaseReference databaseReference;

    RecyclerView rv;
    ArrayList<Client> clientArray;
    AdapterClient myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clientArray.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    Client c = ds.getValue(Client.class);
                    clientArray.add(c);
                }
                refreshAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    void registerNewClient(){
        String firstName = inputFirstNameED.getText().toString().trim();
        String lastName = inputLastNameED.getText().toString().trim();
        String phone = inputPhoneED.getText().toString().trim();
        String email = inputEmailED.getText().toString().trim();
        String password = inputPasswordED.getText().toString().trim();
        String id = databaseReference.push().getKey();
        Client newClient = new Client(id, firstName, lastName, phone, email, password, 0);
        databaseReference.child(id).setValue(newClient);
    }

    void refreshAdapter(){
        myAdapter = new AdapterClient(this, clientArray);
        rv.setAdapter(myAdapter);
    }

    void initViews(){
        databaseReference = FirebaseDatabase.getInstance().getReference("clientsDB");

        rv = findViewById(R.id.clientsDB);
        rv.setLayoutManager(new LinearLayoutManager(this));
        clientArray = new ArrayList<>();
        myAdapter = new AdapterClient(this, clientArray);
        rv.setAdapter(myAdapter);

        inputFirstNameED = findViewById(R.id.firstNameED);
        inputLastNameED = findViewById(R.id.lastNameED);
        inputEmailED = findViewById(R.id.emailED);
        inputPhoneED = findViewById(R.id.phoneED);
        inputPasswordED = findViewById(R.id.passwordED);

        registerBtn = findViewById(R.id.signUpBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewClient();
            }
        });

    }


}