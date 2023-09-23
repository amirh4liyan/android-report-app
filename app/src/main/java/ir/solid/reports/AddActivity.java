package ir.solid.reports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    EditText date_ed, lecture_ed, section_ed, source_ed,
            duration_ed, pages_ed, function_ed;
    TextView current_date_lbl;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        current_date_lbl = findViewById(R.id.current_date_lbl);
        date_ed = findViewById(R.id.date_ed);
        lecture_ed = findViewById(R.id.lecture_ed);
        section_ed = findViewById(R.id.section_ed);
        source_ed = findViewById(R.id.source_ed);
        duration_ed = findViewById(R.id.duration_editTime);
        pages_ed = findViewById(R.id.pages_ed);
        function_ed = findViewById(R.id.function_ed);
        add_btn = findViewById(R.id.add_btn);

        JCal jcal = new JCal();
        current_date_lbl.setText(jcal.getJDate());
        add_btn.setOnClickListener(view -> {

            String date;
            if (date_ed.getText().toString().equals(""))
                date = jcal.getCurrentJDate();
            else
                date = date_ed.getText().toString();

            MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
            myDB.addLog(
                    date,
                    lecture_ed.getText().toString().trim(),
                    section_ed.getText().toString().trim(),
                    source_ed.getText().toString().trim(),
                    duration_ed.getText().toString().trim(),
                    pages_ed.getText().toString().trim(),
                    function_ed.getText().toString().trim()
            );
        });
    }
}