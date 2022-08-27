package com.example.speakup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextInputLayout email, mobileno, sign_password, pass_again;
    AppCompatButton sign_button;
    TextView login_but;
    float v = 0;

    FirebaseAuth mAuth;

    //create object of database class to access the DB
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speakup-dcbd3-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        mobileno = findViewById(R.id.mobileno);
        sign_password = findViewById(R.id.sign_password);
        pass_again = findViewById(R.id.pass_again);
        sign_button = findViewById(R.id.sign_button);
        login_but = findViewById(R.id.login_but);

        email.setAlpha(v);
        mobileno.setAlpha(v);
        sign_password.setAlpha(v);
        pass_again.setAlpha(v);
        sign_button.setAlpha(v);
        login_but.setAlpha(v);

        email.setTranslationY(800);
        mobileno.setTranslationY(800);
        sign_password.setTranslationY(800);
        pass_again.setTranslationY(800);
        sign_button.setTranslationY(800);
        login_but.setTranslationY(800);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(200).start();
        mobileno.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        sign_password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass_again.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
        sign_button.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login_but.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();

        mAuth = FirebaseAuth.getInstance();

        sign_button.setOnClickListener(view -> {
            createUser();
        });

        login_but.setOnClickListener(view -> {
            startActivity(new Intent(Register.this, Login.class));
        });
    }

    private void createUser() {

//      geting inputs from EditTexts
        final String emailTxt = email.getEditText().getText().toString();
        final String phoneTxt = mobileno.getEditText().getText().toString();
        final String passwordTxt = sign_password.getEditText().getText().toString();
        final String conPasswordTxt = pass_again.getEditText().getText().toString();

        if (emailTxt.isEmpty() || phoneTxt.isEmpty() || passwordTxt.isEmpty() || conPasswordTxt.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                    email.setError("field cannot be empty");
                    email.requestFocus();
                    mobileno.requestFocus();
                    sign_password.requestFocus();
                    pass_again.requestFocus();
        } else if(!(passwordTxt.equals(conPasswordTxt))){
                    Toast.makeText(Register.this,"Passwords aren't matching",Toast.LENGTH_LONG).show();
                    pass_again.requestFocus();
                }
        else {
            mAuth.createUserWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, Login.class));
                    } else {
                        Toast.makeText(Register.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}