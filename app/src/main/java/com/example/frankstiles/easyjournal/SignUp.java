package com.example.frankstiles.easyjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {
     private Button mSignInButton;
     private Button mSignUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mSignInButton = (Button) findViewById(R.id.sign_in);
        mSignUpButton = (Button) findViewById(R.id.sign_up);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,ViewDetails.class);
                startActivity(i);
            }
        });
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,JournalEntries.class);
                startActivity(i);
            }
        });
    }

}
