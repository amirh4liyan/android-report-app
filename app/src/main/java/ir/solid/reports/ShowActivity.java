package ir.solid.reports;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Details> detailsList;

    MyDatabaseHelper myDB;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView = findViewById(R.id.recyclerView_show);
        myDB = new MyDatabaseHelper(ShowActivity.this);

        storeDataInArray();

        customAdapter = new CustomAdapter(ShowActivity.this, this, detailsList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray() {
        detailsList = new ArrayList<>();
/*
        String app_id, app_date, app_lecture, app_section, app_source,
                app_duration, app_pages, app_function;

 */

        String a, b, c, d, e, f, g, h;

        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                a = cursor.getString(0);
                b = cursor.getString(1);
                c = cursor.getString(2);
                d = cursor.getString(3);
                e = cursor.getString(4);
                f = cursor.getString(5);
                g = cursor.getString(6);
                h = cursor.getString(7);

                detailsList.add(new Details(a, b, c, d, e, f, g, h));
            }
        }
    }
}