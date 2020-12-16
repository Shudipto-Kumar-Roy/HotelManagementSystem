package com.example.hotelmanagementapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteGuest extends AppCompatActivity implements View.OnClickListener {
    private Typeface typeface;
    private TextView roomIDText,deleteTitleText,deletedTextView;
    private EditText roomId;
    private Button deleteButton,searchButton;

    DatabaseSQLite databaseSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_guest);

        typeface = Typeface.createFromAsset(getAssets(),"Font/colus_regular.ttf");
        databaseSQLite = new DatabaseSQLite(this);
        SQLiteDatabase sqLiteDatabase = databaseSQLite.getWritableDatabase();

        roomIDText = findViewById(R.id.RoomNoTextId);
        deleteTitleText = findViewById(R.id.DeleteTitleID);
        deletedTextView = findViewById(R.id.deleteTextViewId);

        roomId = findViewById(R.id.UserRoomId);
        searchButton = findViewById(R.id.searchButtonId);
        deleteButton  =findViewById(R.id.deleteButtonId);

        roomIDText.setTypeface(typeface);
        deleteButton.setTypeface(typeface);
        deleteTitleText.setTypeface(typeface);


        searchButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.searchButtonId)
        {
//            ArrayList<String> listData = new ArrayList<>();
            StringBuffer stringBuffer = new StringBuffer();
            Cursor searchValue = databaseSQLite.searchData();
            if(searchValue.getCount()==0)
            {

                Toast.makeText(getApplicationContext(),"No Guest booked in this room no",Toast.LENGTH_SHORT).show();


            }
            else {
                String roomId1 = roomId.getText().toString();
                while(searchValue.moveToNext())
                {
                    if(searchValue.getString(0).equals(roomId1))
                    {
                        stringBuffer.append("Room No : "+searchValue.getString(0)+"\nName : "+searchValue.getString(1)+"\nFather Name : "+searchValue.getString(2)+"\nMother Name : "+searchValue.getString(3)+"\nEmail : "+searchValue.getString(4)+"\nAddress : "+searchValue.getString(5)+"\nPhone : "+searchValue.getString(6));

                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"No match found",Toast.LENGTH_SHORT).show();

                    }

                }
            }
            deletedTextView.setText(stringBuffer);

        }

        else if(v.getId()==R.id.deleteButtonId)
        {

            String room_id1 = roomId.getText().toString();

            int value = databaseSQLite.deleteGuest(room_id1);
            if(value>0)
            {
                Toast.makeText(getApplicationContext(),"Guest in " +room_id1+ "is deleted successfully",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Guest in "+room_id1+ "is not deleted",Toast.LENGTH_LONG).show();

            }
        }
    }

}
