package com.example.android.applicationtaskhi2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.android.applicationtaskhi2020.adapters.GoalsAdapter;
import com.example.android.applicationtaskhi2020.adapters.InfoAdapter;
import com.example.android.applicationtaskhi2020.data.Goals;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity implements InfoAdapter.InfoAdapterOnClickHandler {

    RecyclerView recyclerView;
    ArrayList<Goals> infoArrayList;
    InfoAdapter adapter;
    private static final int LOW_POINT = 50;
    private static final int MEDIUM_POINT = 150;
    private static final int HIGH_POINT = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setTitle("Info");

        infoArrayList = new ArrayList<>();
        infoArrayList.add(new Goals(R.drawable.five_minute_shower, R.string.five_minute, LOW_POINT, R.string.five_minute_description));
        infoArrayList.add(new Goals(R.drawable.bring_bag, R.string.bring_bag, LOW_POINT, R.string.bring_bag_description));
        infoArrayList.add(new Goals(R.drawable.drive_less, R.string.drive_less, HIGH_POINT, R.string.drive_less_description));
        infoArrayList.add(new Goals(R.drawable.lights_off, R.string.lights_off, MEDIUM_POINT, R.string.lights_off_description));
        infoArrayList.add(new Goals(R.drawable.local_produce, R.string.local_produce, MEDIUM_POINT, R.string.buy_locally_description));
        infoArrayList.add(new Goals(R.drawable.meat_free_meals, R.string.meat_free, HIGH_POINT, R.string.meatless_meals_description));
        infoArrayList.add(new Goals(R.drawable.recycle, R.string.recycle, HIGH_POINT, R.string.recycle_description));
        infoArrayList.add(new Goals(R.drawable.refill_reuse, R.string.refill_reuse, LOW_POINT, R.string.refill_reuse_description));
        infoArrayList.add(new Goals(R.drawable.unplug, R.string.unplug, MEDIUM_POINT, R.string.unplug_description));
        infoArrayList.add(new Goals(R.drawable.zero_waste, R.string.zero_waste, HIGH_POINT, R.string.zero_waste_description));

        recyclerView = (RecyclerView) findViewById(R.id.rv_infoactivity);

        adapter = new InfoAdapter(this);

        adapter.setGoalsData(infoArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    @Override
    public void onClick(Goals goals) {
        Intent intent = new Intent(InfoActivity.this, ItemInfoActivity.class);
        int image = goals.getImage();
        int title = goals.getGoalString();
        int points = goals.getPoints();
        int description = goals.getDescription();
        intent.putExtra("IMAGE", image);
        intent.putExtra("TITLE", title);
        intent.putExtra("POINTS", points);
        intent.putExtra("DESCRIPTION", description);
        startActivity(intent);
    }
}
