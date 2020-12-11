package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private Typeface typeface;
    private TextView titleText,userText,passwordText,emailText;
    private EditText UserName, UserPassword ,UserEmail;
    private Button signUpButton;

    DatabaseSQLite databaseSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseSQLite = new DatabaseSQLite(this);

        titleText = findViewById(R.id.SigninTitleID);
        userText = findViewById(R.id.UserTextId);
        passwordText = findViewById(R.id.PasswordTextId);
        emailText = findViewById(R.id.EmailTextId);

        UserName = findViewById(R.id.UserNameId);
        UserPassword = findViewById(R.id.UserPasswordId);
        UserEmail = findViewById(R.id.UserEmailId);

        signUpButton = findViewById(R.id.SignUpButtonId);

        typeface = Typeface.createFromAsset(getAssets(),"Font/colus_regular.ttf");
        titleText.setTypeface(typeface);
        userText.setTypeface(typeface);
        passwordText.setTypeface(typeface);
        emailText.setTypeface(typeface);
        signUpButton.setTypeface(typeface);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = UserName.getText().toString();
                String password = UserPassword.getText().toString();
                String email = UserEmail.getText().toString();

               if(username.equals("") || password.equals("") || email.equals(""))
               {
                   Toast.makeText(getApplicationContext(),"Please fill up the text first..",Toast.LENGTH_SHORT).show();
               }
               else {
                   long rowID = databaseSQLite.insertData(username,password,email);
                   if(rowID==-1)
                   {

                       Toast.makeText(getApplicationContext(),"Failed..",Toast.LENGTH_LONG).show();
                   }
                   else
                   {

                       Toast.makeText(getApplicationContext(),"SignUp Successfully..",Toast.LENGTH_LONG).show();

                   }
               }


            }
        });
    }
}
