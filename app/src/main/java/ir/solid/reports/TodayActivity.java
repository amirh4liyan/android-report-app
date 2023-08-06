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


public class TodayActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetailsRepetition> detailsList;

    MyDatabaseHelper myDB;
    CustomAdapterRepetition customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        recyclerView = findViewById(R.id.recyclerView_today);
        myDB = new MyDatabaseHelper(TodayActivity.this);

        detailsList = new ArrayList<>();

        JCal jcal = new JCal();
        getData(jcal._1day_ago(), "مرور اول");
        getData(jcal._1week_ago(), "مرور دوم");
        getData(jcal._2week_ago(), "مرور سوم");
        getData(jcal._4week_ago(), "مرور چهارم");
        getData(jcal._12week_ago(), "مرور پنجم");

        customAdapter = new CustomAdapterRepetition(TodayActivity.this, this, detailsList);
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

    void getData(String date, String repetition) {
        String a, b, c, d, e, f, g, h;

        Cursor cursor = myDB.search(date);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                a = cursor.getString(0);
                b = cursor.getString(1);
                c = cursor.getString(2);
                d = cursor.getString(3);
                e = cursor.getString(4);
                f = cursor.getString(5);
                g = cursor.getString(6);
                h = cursor.getString(7);

                // in this activity Nth repetition added to Details
                detailsList.add(new DetailsRepetition(repetition, a, b, c, d, e, f, g, h));
            }
        }
    }
}