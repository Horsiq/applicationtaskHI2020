package com.example.android.applicationtaskhi2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemInfoActivity extends AppCompatActivity {

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        ImageView imageView = (ImageView) findViewById(R.id.info_item_iv);
        TextView textView = (TextView) findViewById(R.id.info_item_title_tv);
        TextView textView1 = (TextView) findViewById(R.id.info_item_points_tv);
        TextView textView2 = (TextView) findViewById(R.id.info_item_description_tv);
        imageButton = (ImageButton) findViewById(R.id.register_actions_info_item_bt);

        Bundle extras = getIntent().getExtras();
        int image = extras.getInt("IMAGE");
        imageView.setImageResource(image);
        int title = extras.getInt("TITLE");
        textView.setText(title);
        setTitle(title);
        int points = extras.getInt("POINTS");
        String pointsString = Integer.toString(points);
        textView1.setText(pointsString + " Points");
        int description = extras.getInt("DESCRIPTION");
        textView2.setText(description);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemInfoActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
