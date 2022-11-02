package com.example.scrollingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ScrollMultipleActivity extends AppCompatActivity {

    private Button goToScrollComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_multiple);

        this.goToScrollComments = findViewById(R.id.goToScrollComments);
        this.goToScrollComments.setOnClickListener(view ->
                this.startActivity(new Intent(this, ScrollCommentsActivity.class))
        );
    }
}