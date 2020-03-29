package com.example.android.applicationtaskhi2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.applicationtaskhi2020.data.SharedPref;
import com.example.android.applicationtaskhi2020.sync.ReminderUtilities;

public class StartActivity extends AppCompatActivity {

    public static final String SCORE_KEY = "score";
    public static final String ACTIONS_KEY = "actions";
    ImageButton registerButton;
    TextView currentScoreTextView;
    TextView completedActionsTextView;
    int defValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setTitle("");



        registerButton = (ImageButton) findViewById(R.id.register_bt);
        currentScoreTextView = (TextView) findViewById(R.id.current_score_tv);
        completedActionsTextView = (TextView) findViewById(R.id.completed_actions_tv);


        String scoreString = SharedPref.getInt(getApplicationContext(), "score", defValue);
        currentScoreTextView.setText(scoreString);

        String actionsString = SharedPref.getInt(getApplicationContext(), "actions", defValue);
        completedActionsTextView.setText(actionsString);

        ReminderUtilities.scheduleReminder(this);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.information_menu){
            Intent intent = new Intent(StartActivity.this, InfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
