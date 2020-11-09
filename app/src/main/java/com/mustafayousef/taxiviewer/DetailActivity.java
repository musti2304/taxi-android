package com.mustafayousef.taxiviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.mustafayousef.taxiviewer.MainActivity.EXTRA_AUFTRAEGE;
import static com.mustafayousef.taxiviewer.MainActivity.EXTRA_EINSTIEGE;
import static com.mustafayousef.taxiviewer.MainActivity.EXTRA_NAME;
import static com.mustafayousef.taxiviewer.MainActivity.EXTRA_WARTEZEIT;

public class DetailActivity extends AppCompatActivity {

    TextView name;
    TextView auftraege;
    TextView einstiege;
    TextView wartezeit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Details");

        this.name = findViewById(R.id.tv_hp_name_detail);
        this.auftraege = findViewById(R.id.tv_auftraege_detail);
        this.einstiege = findViewById(R.id.tv_einstiege_detail);
        this.wartezeit = findViewById(R.id.tv_wartezeit_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        int auftraege = intent.getIntExtra(EXTRA_AUFTRAEGE, 0);
        int einstiege = intent.getIntExtra(EXTRA_EINSTIEGE, 0);
        String wartezeit = intent.getStringExtra(EXTRA_WARTEZEIT);

        this.name.setText(name);
        this.auftraege.setText(Integer.toString(auftraege));
        this.einstiege.setText(Integer.toString(einstiege));
        this.wartezeit.setText(wartezeit);

    }
}