package com.example.android.applicationtaskhi2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.applicationtaskhi2020.adapters.GoalsAdapter;
import com.example.android.applicationtaskhi2020.data.Goals;
import com.example.android.applicationtaskhi2020.data.SharedPref;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements GoalsAdapter.GoalsAdapterOnClickHandler{

    private static final String LOG = RegisterActivity.class.getSimpleName();

    ArrayList<Goals> goalsArrayList;
    RecyclerView recyclerView;
    GoalsAdapter adapter;
    int numberOfColumns;
    int score;
    int actions;
    int defValue = 0;

    ImageButton registerButton;
    public static final String SCORE_KEY = "score";
    public static final String ACTIONS_KEY = "actions";
    private static final int LOW_POINT = 50;
    private static final int MEDIUM_POINT = 150;
    private static final int HIGH_POINT = 300;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Register your Acts");

        String scoreString = SharedPref.getInt(getApplicationContext(), SCORE_KEY, defValue);
        score = Integer.parseInt(scoreString);
        String actionsString = SharedPref.getInt(getApplicationContext(), ACTIONS_KEY, defValue);
        actions = Integer.parseInt(actionsString);

        goalsArrayList = new ArrayList<>();
        goalsArrayList.add(new Goals(R.drawable.five_minute_shower, R.string.five_minute, LOW_POINT));
        goalsArrayList.add(new Goals(R.drawable.bring_bag, R.string.bring_bag, LOW_POINT));
        goalsArrayList.add(new Goals(R.drawable.drive_less, R.string.drive_less, HIGH_POINT));
        goalsArrayList.add(new Goals(R.drawable.lights_off, R.string.lights_off, MEDIUM_POINT));
        goalsArrayList.add(new Goals(R.drawable.local_produce, R.string.local_produce, MEDIUM_POINT));
        goalsArrayList.add(new Goals(R.drawable.meat_free_meals, R.string.meat_free, HIGH_POINT));
        goalsArrayList.add(new Goals(R.drawable.recycle, R.string.recycle, HIGH_POINT));
        goalsArrayList.add(new Goals(R.drawable.refill_reuse, R.string.refill_reuse, LOW_POINT));
        goalsArrayList.add(new Goals(R.drawable.unplug, R.string.unplug, MEDIUM_POINT));
        goalsArrayList.add(new Goals(R.drawable.zero_waste, R.string.zero_waste, HIGH_POINT));

        recyclerView = (RecyclerView) findViewById(R.id.rv_mainactivty);
        registerButton = (ImageButton) findViewById(R.id.register_actions_bt);

        numberOfColumns = 2;

        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        adapter = new GoalsAdapter(this);

        adapter.setGoalsData(goalsArrayList);

        recyclerView.setAdapter(adapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StartActivity.class);
                startActivity(intent);
                SharedPref.saveIntPoints(getApplicationContext(), SCORE_KEY, score);
                SharedPref.saveIntActions(getApplicationContext(), ACTIONS_KEY, actions);
                Log.v(LOG, "Score is: " + score);

            }
        });





    }

    @Override
    public void onClick(Goals goals) {
        int points = goals.getPoints();
        score = score + points;
        actions = actions + 1;
    }

}
