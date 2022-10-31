package com.example.scrollingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdditionalExerciseActivity extends AppCompatActivity {

    private Button startAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_exercise);

        this.startAgain = findViewById(R.id.startAgain);
        this.startAgain.setOnClickListener(view ->
                this.startActivity(new Intent(this, MainActivity.class))
        );
    }
}