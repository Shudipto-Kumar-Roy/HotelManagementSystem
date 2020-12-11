package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    private Typeface typeface;
    private TextView loginTitle,userText,passwordText;
    private EditText username,password;
   private Button loginButton, signinButton;
   private AlertDialog.Builder alertdialogbuilder;

   DatabaseSQLite databaseSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTitle = findViewById(R.id.LoginTitleID);
        userText = findViewById(R.id.UserTextId);
        passwordText = findViewById(R.id.PasswordTextId);
        username = findViewById(R.id.UserNameId);
        password = findViewById(R.id.UserPasswordId);
        loginButton = findViewById(R.id.LoginButtonId);
        signinButton = findViewById(R.id.SignInButtonId);


        typeface = Typeface.createFromAsset(getAssets(),"Font/colus_regular.ttf");
        loginTitle.setTypeface(typeface);
        userText.setTypeface(typeface);
        passwordText.setTypeface(typeface);
        loginButton.setTypeface(typeface);
        signinButton.setTypeface(typeface);

        signinButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);


        databaseSQLite = new DatabaseSQLite(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.LoginButtonId)
        {

             Cursor cursor = databaseSQLite.showData();
             if(cursor.getColumnCount()==0)
             {
                 Toast.makeText(getApplicationContext(),"No registered user found",Toast.LENGTH_SHORT).show();
             }
             else
             {
                 String username1 = username.getText().toString();
                 String password1 = password.getText().toString();
                while (cursor.moveToNext())
                {

                    if((cursor.getString(1).equals(username1)) && (cursor.getString(2).equals(password1)))
                    {
                        Toast.makeText(getApplicationContext(),"Login successfull!!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Login failed!!",Toast.LENGTH_SHORT).show();

                    }
                }
             }

        }
        if(v.getId()==R.id.SignInButtonId)
        {
            Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        alertdialogbuilder = new AlertDialog.Builder(LoginActivity.this);
        alertdialogbuilder.setTitle("Select");
        alertdialogbuilder.setMessage("Do you really want to exit?");
        alertdialogbuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertdialogbuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }

        });

        AlertDialog alertdialog = alertdialogbuilder.create();
        alertdialog.show();
    }
}

