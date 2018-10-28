package com.example.aminu.devfest18hackday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private TextView loginBtn, registerBtn;
    private EditText email, password;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        firebaseAuth = FirebaseAuth.getInstance();

        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginPage.this, SignupPage.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailText = email.getText().toString();
                final String passText = password.getText().toString();

                if (TextUtils.isEmpty(emailText)) {

                    email.setError("Please Enter Email");
                    email.requestFocus();

                } else {

                    firebaseAuth.signInWithEmailAndPassword(emailText, passText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(LoginPage.this, "Logged In", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(LoginPage.this, MainActivity.class));
                                        finish();

                                    }
                                }
                            });
                }
            }
        });
    }
}
