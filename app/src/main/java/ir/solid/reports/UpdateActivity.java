package ir.solid.reports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    String id, b, c, d, e, f, g, h;

    EditText date_ed, lecture_ed, section_ed, source_ed,
            duration_ed, pages_ed, function_ed;
    TextView current_date_lbl;
    Button edit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        current_date_lbl = findViewById(R.id.current_date_lbl2);
        date_ed = findViewById(R.id.date_ed2);
        lecture_ed = findViewById(R.id.lecture_ed2);
        section_ed = findViewById(R.id.section_ed2);
        source_ed = findViewById(R.id.source_ed2);
        duration_ed = findViewById(R.id.duration_editTime2);
        pages_ed = findViewById(R.id.pages_ed2);
        function_ed = findViewById(R.id.function_ed2);
        edit_btn = findViewById(R.id.edit_button);

        JCal jcal = new JCal();
        current_date_lbl.setText(jcal.getJDate());

        getAndSetIntentData();

        edit_btn.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            myDB.updateLog(
                    id,
                    date_ed.getText().toString().trim(),
                    lecture_ed.getText().toString().trim(),
                    section_ed.getText().toString().trim(),
                    source_ed.getText().toString().trim(),
                    duration_ed.getText().toString().trim(),
                    pages_ed.getText().toString().trim(),
                    function_ed.getText().toString().trim()
            );
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
            b = getIntent().getStringExtra("date");
            c = getIntent().getStringExtra("lecture");
            d = getIntent().getStringExtra("section");
            e = getIntent().getStringExtra("source");
            f = getIntent().getStringExtra("duration");
            g = getIntent().getStringExtra("pages");
            h = getIntent().getStringExtra("function");

            date_ed.setText(b);
            lecture_ed.setText(c);
            section_ed.setText(d);
            source_ed.setText(e);
            duration_ed.setText(f);
            pages_ed.setText(g);
            function_ed.setText(h);

        } else {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }
    }
}