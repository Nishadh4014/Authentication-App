package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
     TextView textView;
     EditText editText1;
     EditText editText2;
     Button button10;
    FirebaseAuth gAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.textView4);
        editText1 = findViewById(R.id.editTextTextPersonName5);
        editText2 = findViewById(R.id.editTextTextPersonName6);
        button10 = findViewById(R.id.button2);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText1.getText().toString().trim();
                String password = editText2.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    editText1.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    editText2.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    editText2.setError("Password must have at least 6 characters");
                    return;
                }
                gAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }else{
                                Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }
        });
    }
}