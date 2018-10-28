package com.example.aminu.devfest18hackday;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText email, password, vPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        email = findViewById(R.id.emailReg);
        password = findViewById(R.id.passwordReg);
        vPassword = findViewById(R.id.VpasswordReg);

        firebaseAuth = FirebaseAuth.getInstance();

        TextView signUp = findViewById(R.id.signupBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailText = email.getText().toString();
                String passText = password.getText().toString();
                String vPassText = vPassword.getText().toString();

                if (!vPassText.equals(passText)) {

                    Toast.makeText(SignupPage.this, "Password did not match", Toast.LENGTH_LONG).show();

                } else {

                    firebaseAuth.createUserWithEmailAndPassword(emailText, vPassText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(SignupPage.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SignupPage.this, MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
}
