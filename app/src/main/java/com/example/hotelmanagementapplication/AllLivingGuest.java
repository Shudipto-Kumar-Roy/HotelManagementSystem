package com.example.hotelmanagementapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AllLivingGuest extends AppCompatActivity {

    private DatabaseSQLite databaseSQLite;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_living_guest);

        listView = findViewById(R.id.listViewId);
        databaseSQLite = new DatabaseSQLite(this);

        loadData();
    }
    public  void loadData()
    {
        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = databaseSQLite.showAllGuest();

        if(cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No data available in the database",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                listData.add("Room No : "+cursor.getString(0)+"\nName : "+cursor.getString(1)+"\nFather Name : "+cursor.getString(2)+"\nMother Name : "+cursor.getString(3)+"\nEmail : "+cursor.getString(4)+"\nAddress : "+cursor.getString(5)+"\nPhone : "+cursor.getString(6));

            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.all_guest_list_item,R.id.dataTextId,listData);
        listView.setAdapter(arrayAdapter);
    }

}
