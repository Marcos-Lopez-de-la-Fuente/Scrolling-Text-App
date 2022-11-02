package com.example.scrollingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ScrollCommentsActivity extends AppCompatActivity {

    private Button goToAdditionalExercise;

    private EditText article;

    private Button addComment;
    private EditText inputComment;
    private boolean initComment;
    private LinearLayout comments;
    private ArrayList<String> arrComments = new ArrayList<>();

    private Button editArticle;
    private boolean statusEditArticle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_comments);

        this.goToAdditionalExercise = findViewById(R.id.goToAdditionalExercise);
        this.article = findViewById(R.id.article);
        this.comments = findViewById(R.id.comments);
        this.addComment = findViewById(R.id.addComment);
        this.inputComment = findViewById(R.id.inputComment);
        this.editArticle = findViewById(R.id.editArticle);


        if (savedInstanceState != null) {
            this.article.setText(savedInstanceState.getString("textArticle"));
            this.arrComments = savedInstanceState.getStringArrayList("arrComments");

            this.showComments();
        }


        this.goToAdditionalExercise.setOnClickListener(view ->
                this.startActivity(new Intent(this, AdditionalExerciseActivity.class))
        );


        this.statusEditArticle = false;
        this.editArticle.setOnClickListener(view -> {

            if (!this.statusEditArticle) {
                this.statusEditArticle = true;

                this.article.setFocusable(true);
                this.article.setFocusableInTouchMode(true);
                this.editArticle.setText("Save changes");

            } else {
                this.statusEditArticle = false;

                this.article.setFocusable(false);
                this.article.setFocusableInTouchMode(false);
                this.editArticle.setText("Edit Article");
            }

        });


        this.initComment = false;
        this.addComment.setOnClickListener(view -> {

            if (!this.initComment) {
                this.initComment = true;

                this.inputComment.setVisibility(View.VISIBLE);
                this.addComment.setText("Save Comment");

            } else {
                this.initComment = false;
                this.addComment.setText("Add Comment");
                this.inputComment.setVisibility(View.INVISIBLE);

                if (!String.valueOf(this.inputComment.getText()).equals("")) {

                    this.arrComments.add(String.valueOf(this.inputComment.getText()));
                    this.inputComment.setText("");

                    this.showComments();
                }
            }
        });
    }


    public void showComments() {
        this.comments.removeAllViews();

        for (int i = 0; i < this.arrComments.size(); i++) {
            LinearLayout newComment = new LinearLayout(this);
            newComment.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            newComment.setOrientation(LinearLayout.VERTICAL);

            TextView commentTitle = new TextView(this);
            commentTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            commentTitle.setText("Comment " + (i + 1));
            commentTitle.setTypeface(null, Typeface.BOLD);

            newComment.addView(commentTitle);


            TextView commentText = new TextView(this);
            commentText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            commentText.setText(this.arrComments.get(i));

            newComment.addView(commentText);
            this.comments.addView(newComment);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("textArticle", String.valueOf(this.article.getText()));
        savedInstanceState.putStringArrayList("arrComments", this.arrComments);
    }
}