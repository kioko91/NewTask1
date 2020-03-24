package com.example.newtask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;
EditText username, email, password, passwordVer;
Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        passwordVer=findViewById(R.id.passwordVer);
        create=findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userString=username.getText().toString();
                String emailString=email.getText().toString();
                String passwordString=password.getText().toString();
                String passwordVerString=passwordVer.getText().toString();

                if (passwordString.equals(passwordVerString)) {
                    registerUser(emailString,passwordString);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match. Please try again",Toast.LENGTH_SHORT).show();
                }
                }

        });


    }

    private void registerUser(String emailString, String passwordString) {
        auth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registration failed. Please try again",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }
}
