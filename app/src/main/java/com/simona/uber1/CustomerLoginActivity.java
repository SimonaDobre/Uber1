package com.simona.uber1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerLoginActivity extends AppCompatActivity {


    EditText inputFirstNameED, inputLastNameED, inputPhoneED, inputEmailED, inputPasswordED;
    Button signUpBtn, loginBtn;
    DatabaseReference databaseReference;
    FirebaseAuth customerAuth;
    FirebaseAuth.AuthStateListener customerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        initViews();

    }


    @Override
    protected void onStart() {
        super.onStart();
        customerAuth.addAuthStateListener(customerListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        customerAuth.removeAuthStateListener(customerListener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        customerAuth.signOut();
    }

    void registerNewDriver() {
        String firstName = inputFirstNameED.getText().toString().trim();
        String lastName = inputLastNameED.getText().toString().trim();
        String phone = inputPhoneED.getText().toString().trim();
        String email = inputEmailED.getText().toString().trim();
        String password = inputPasswordED.getText().toString().trim();
        // Client newClient = new Client(id, firstName, lastName, phone, email, password, 0);
        // databaseReference.child(id).setValue(newClient);
        customerAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(CustomerLoginActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (inputEmailED.getText().toString().trim().equals("")) {
                                    Toast.makeText(CustomerLoginActivity.this, "introdu emailul", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (inputPasswordED.getText().toString().trim().equals("")) {
                                    Toast.makeText(CustomerLoginActivity.this, "introdu parola", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(CustomerLoginActivity.this, "NU am inregistrat pt ca: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    String currentDriverID = customerAuth.getCurrentUser().getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference().
                                            child("Users").child("Customers").child(currentDriverID);
                                    databaseReference.setValue(true);
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
        customerAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(CustomerLoginActivity.this, "NU te-ai logat pentru ca " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    void initViews() {
        inputFirstNameED = findViewById(R.id.firstNameCustomerED);
        inputLastNameED = findViewById(R.id.lastNameCustomerED);
        inputEmailED = findViewById(R.id.emailCustomerED);
        inputPhoneED = findViewById(R.id.phoneCustomerED);
        inputPasswordED = findViewById(R.id.passwordCustomerED);


        customerAuth = FirebaseAuth.getInstance();
        customerListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    Intent toDriverLoginActivity = new Intent(CustomerLoginActivity.this, MapActivity.class);
                    startActivity(toDriverLoginActivity);
                    finish();
                }
            }
        };

        signUpBtn = findViewById(R.id.signUpCustomerBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEmailED.getText().toString().trim().equals("")) {
                    Toast.makeText(CustomerLoginActivity.this, "introdu emailul", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (inputPasswordED.getText().toString().trim().equals("")) {
                    Toast.makeText(CustomerLoginActivity.this, "introdu parola", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerNewDriver();
            }
        });

        loginBtn = findViewById(R.id.loginCustomerBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEmailED.getText().toString().trim().equals("")) {
                    Toast.makeText(CustomerLoginActivity.this, "introdu emailul", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (inputPasswordED.getText().toString().trim().equals("")) {
                    Toast.makeText(CustomerLoginActivity.this, "introdu parola", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginDriver();
            }
        });
    }
}