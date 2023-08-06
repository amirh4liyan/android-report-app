package ir.solid.reports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button today_btn, add_report_btn, show_report_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today_btn = findViewById(R.id.today_btn);
        add_report_btn = findViewById(R.id.add_report_btn);
        show_report_btn = findViewById(R.id.show_report_btn);

        today_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TodayActivity.class);
            startActivity(intent);
        });

        add_report_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        show_report_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
        });

    }
}