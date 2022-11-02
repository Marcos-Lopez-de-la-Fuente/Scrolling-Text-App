package com.example.scrollingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button goToWebLinksAndScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.goToWebLinksAndScrollView = findViewById(R.id.goToWebLinksAndScrollView);
        this.goToWebLinksAndScrollView.setOnClickListener(view ->
            this.startActivity(new Intent(this, WebLinksAndScrollViewActivity.class))
        );
    }
}