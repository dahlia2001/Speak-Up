package com.example.speakup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextInputLayout email, pass;
    AppCompatButton button;
    TextView forgot_pass,register_button;
    float v = 0;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.login_button);
        forgot_pass = findViewById(R.id.forget_pass);
        register_button = findViewById(R.id.register_button);

        email.setAlpha(v);
        pass.setAlpha(v);
        button.setAlpha(v);
        forgot_pass.setAlpha(v);
        register_button.setAlpha(v);

        email.setTranslationY(800);
        pass.setTranslationY(800);
        button.setTranslationY(800);
        forgot_pass.setTranslationY(800);
        register_button.setTranslationY(800);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(200).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
        button.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
        forgot_pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        register_button.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(view -> {
            loginUser();
        });
        register_button.setOnClickListener(view ->{
            startActivity(new Intent(Login.this, Register.class));
        });
    }

    private void loginUser() {
        final String emailTxt = email.getEditText().getText().toString();
        final String passTxt = pass.getEditText().getText().toString();
        if (emailTxt.isEmpty() || passTxt.isEmpty()) {
            Toast.makeText(Login.this, "Please enter your email or password", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(emailTxt, passTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Homescreen.class));
                    } else {
                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}