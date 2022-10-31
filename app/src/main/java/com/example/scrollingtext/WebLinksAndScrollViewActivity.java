package com.example.scrollingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WebLinksAndScrollViewActivity extends AppCompatActivity {

    private Button goToScrollMultiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_links_and_scroll_view);

        this.goToScrollMultiple = findViewById(R.id.goToScrollMultiple);
        this.goToScrollMultiple.setOnClickListener(view ->
                this.startActivity(new Intent(this, ScrollMultipleActivity.class))
        );
    }
}