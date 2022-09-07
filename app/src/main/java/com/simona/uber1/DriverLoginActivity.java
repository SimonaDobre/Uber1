package com.simona.uber1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DriverLoginActivity extends AppCompatActivity {

    EditText inputFirstNameED, inputLastNameED, inputPhoneED, inputEmailED, inputPasswordED;
    Button signUpBtn, loginBtn;

    //  TextView tv;

    DatabaseReference currentDriverDbReference;
    FirebaseAuth driverAuth;
    FirebaseAuth.AuthStateListener driverListener;

    String currentDriverID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        initViews();


    }

    @Override
    protected void onStart() {
        super.onStart();
        driverAuth.addAuthStateListener(driverListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        driverAuth.removeAuthStateListener(driverListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        driverAuth.signOut();
    }

    void registerNewDriver() {
        String firstName = inputFirstNameED.getText().toString().trim();
        String lastName = inputLastNameED.getText().toString().trim();
        String phone = inputPhoneED.getText().toString().trim();
        String email = inputEmailED.getText().toString().trim();
        String password = inputPasswordED.getText().toString().trim();
        // Client newClient = new Client(id, firstName, lastName, phone, email, password, 0);
        // databaseReference.child(id).setValue(newClient);
        driverAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(DriverLoginActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (inputEmailED.getText().toString().trim().equals("")) {
                                    Toast.makeText(DriverLoginActivity.this, "introdu emailul", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (inputPasswordED.getText().toString().trim().equals("")) {
                                    Toast.makeText(DriverLoginActivity.this, "introdu parola", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(DriverLoginActivity.this, "NU am inregistrat " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                } else {
                                    currentDriverID = driverAuth.getCurrentUser().getUid();
                                    currentDriverDbReference = FirebaseDatabase.getInstance().
                                            getReference("Users").child("Drivers").child(currentDriverID);
                                    currentDriverDbReference.setValue(true);

                                }
                            }
                        });
    }

    void loginDriver() {
        String firstName = inputFirstNameED.getText().toString().trim();
        String lastName = inputLastNameED.getText().toString().trim();
        String phone = inputPhoneED.getText().toString().trim();
        String email = inputEmailED.getText().toString().trim();
        String password = inputPasswordED.getText().toString().trim();
        driverAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this, "NU te-ai logat ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void initViews() {

        inputFirstNameED = findViewById(R.id.firstNameDriverED);
        inputLastNameED = findViewById(R.id.lastNameDriverED);
        inputEmailED = findViewById(R.id.emailDriverED);
        inputPhoneED = findViewById(R.id.phoneDriverED);
        inputPasswordED = findViewById(R.id.passwordDriverED);

        driverAuth = FirebaseAuth.getInstance();
        driverListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    Intent toDriverLoginActivity = new Intent(DriverLoginActivity.this, DriverMapsActivity.class);
                    startActivity(toDriverLoginActivity);
                    finish();
                }
            }
        };

        signUpBtn = findViewById(R.id.signUpDriverBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEmailED.getText().toString().trim().equals("")) {
                    Toast.makeText(DriverLoginActivity.this, "introdu emailul", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (inputPasswordED.getText().toString().trim().equals("")) {
                    Toast.makeText(DriverLoginActivity.this, "introdu parola", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerNewDriver();
            }
        });

        loginBtn = findViewById(R.id.loginDriverBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEmailED.getText().toString().trim().equals("")) {
                    Toast.makeText(DriverLoginActivity.this, "introdu emailul", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (inputPasswordED.getText().toString().trim().equals("")) {
                    Toast.makeText(DriverLoginActivity.this, "introdu parola", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginDriver();
            }
        });
    }


}