# Scrolling-Text-App

Project in Android Studio to practice the operation of "Scrolls".

Instead of making 5 different Releases, I have created 5 Activities with buttons to navigate between them.

<details>

**<summary>Application Images</summary>**

<img src="resForReadme/mobile.gif">

</details>

## Functioning general (User view)

-   A main title will be displayed and will always be visible.

-   A subheading will display and sometimes scroll along with the article.

-   An "article" will be displayed that can be scrolled vertically.

-   1 button will be displayed to edit the content of the article.

-   1 button to add a comment, it will be displayed below the article.

## Functioning technical

The program will be divided into the following sections:

-   ### **Java files** (Application Backend):

    *Most Activities do not have any additional Java code (just what is needed to move between activities with the buttons).*

    -   **`ScrollCommentsActivity.java`**

        -   Single Activity with a considerable change in its code. In the code we will have to add Listeners in the edit and add comments buttons, we will also have to show the comments through a list of these. If the screen is rotated horizontally, it will be necessary to save the changes.

-   ### **XML files** (Application Frontend):

    -   **`activity_main.xml`**

        -   `TextView` title

        -   `TextView` subtitle

        -   `TextView` article

    -   **`activity_web_links_and_scroll_view.xml`**

        -   `TextView` title

        -   `TextView` subtitle

        -   `ScrollView` for the article

            -   `TextView` article (Attribute `autolink="web"`)

    -   **`activity_scroll_multiple.xml`**

        -   `TextView` title

        -   `ScrollView` for the subtitle and article

            -   `LinearLayout` to be able to enter 2 elements inside a `ScrollView`

                -   `TextView` subtitle

                -   `TextView` article

    -   **`activity_scroll_comments.xml`**

        -   `TextView` title

        -   `ScrollView` for the subtitle, article, buttons and comments

            -   `LinearLayout` to be able to enter several elements within a `ScrollView`.

                -   `TextView` subtitle

                -   `EditText` article (Attribute `focusable="false"`)

                -   `Button` to change the value of `focusable=true` in Java.

                -   `LinearLayout` to horizontalize `Button` and `EditText` (Attribute `orientation="horizontal"`)

                    -   `Button` to add a comment

                    -   `EditText` to enter a comment (Attribute `visibility="invisible"`)

                -   `LinearLayout` to verticalize the comment list (Attribute `orientation="vertical"`)

    -   **`activity_additional_exercise.xml`**

        -   `TextView` title

        -   `ScrollView` for the subtitle and article

            -   `LinearLayout` to be able to enter several elements within a `ScrollView`. (Attribute `orientation="horizontal"`)

                -   `TextView` subtitle (Attribute `width=100dp`)

                -   `TextView` article

## **Code**

<ul>

### <li>**Java files**

<ul>

<li>

<details>

**<summary>`MainActivity.java`</summary>**

```java
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
```

</details>

</li>

<li>

<details>

**<summary>`WebLinksAndScrollViewActivity.java`</summary>**

```java
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
```

</details>

</li>

<li>

<details>

**<summary>`ScrollMultipleActivity.java`</summary>**

```java
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
```

</details>

</li>

<li>

<details>

**<summary>`ScrollCommentsActivity.java`</summary>**

```java
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
```

</details>

</li>

<li>

<details>

**<summary>`AdditionalExerciseActivity.java`</summary>**

```java
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
```

</details>

</li>

</ul>

</li>

### <li>**XML files**

<ul>

<li>

<details>

**<summary>`activity_main.xml`</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/padding_regular">

    <Button
        android:id="@+id/goToWebLinksAndScrollView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Web Links And Scroll View" />

    <TextView
        android:id="@+id/article_heading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/goToWebLinksAndScrollView"
        android:background="@color/colorPrimary"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_title"
        android:textAppearance="@android:style/TextAppearance.Large"
        android:textColor="@android:color/white"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/article_subheading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_heading"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_subtitle"
        android:textAppearance="@android:style/TextAppearance" />

    <TextView
        android:id="@+id/article"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_subheading"
        android:lineSpacingExtra="@dimen/line_spacing"
        android:text="@string/article_text" />

</RelativeLayout>
```

</details>

</li>

<li>

<details>

**<summary>`activity_web_links_and_scroll_view.xml`</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/padding_regular">

    <Button
        android:id="@+id/goToScrollMultiple"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Scroll Multiple" />

    <TextView
        android:id="@+id/article_heading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/goToScrollMultiple"
        android:background="@color/colorPrimary"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_title"
        android:textAppearance="@android:style/TextAppearance.Large"
        android:textColor="@android:color/white"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/article_subheading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_heading"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_subtitle"
        android:textAppearance="@android:style/TextAppearance" />

    <ScrollView
        android:id="@+id/scroll_article"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_subheading">

        <TextView
            android:id="@+id/article"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:autoLink="web"
            android:lineSpacingExtra="@dimen/line_spacing"
            android:text="@string/article_text" />

    </ScrollView>

</RelativeLayout>
```

</details>

</li>

<li>

<details>

**<summary>`activity_scroll_multiple.xml`</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/padding_regular">

    <Button
        android:id="@+id/goToScrollComments"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Scroll Comments" />

    <TextView
        android:id="@+id/article_heading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/goToScrollComments"
        android:background="@color/colorPrimary"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_title"
        android:textAppearance="@android:style/TextAppearance.Large"
        android:textColor="@android:color/white"
        android:textStyle="bold" />

    <ScrollView
        android:id="@+id/scroll_main"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_heading">

        <LinearLayout
            android:id="@+id/linear_layout_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:id="@+id/article_subheading"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:padding="@dimen/padding_regular"
                android:text="@string/article_subtitle"
                android:textAppearance="@android:style/TextAppearance" />

            <TextView
                android:id="@+id/article"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:autoLink="web"
                android:lineSpacingExtra="@dimen/line_spacing"
                android:text="@string/article_text" />

        </LinearLayout>
    </ScrollView>

</RelativeLayout>
```

</details>

</li>

#### <li>**`activity_scroll_comments.xml`**

<ul>

<li>

<details>

**<summary>(vertical)</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/padding_regular">

    <Button
        android:id="@+id/goToAdditionalExercise"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Additional Exercise" />

    <TextView
        android:id="@+id/article_heading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/goToAdditionalExercise"
        android:background="@color/colorPrimary"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_title"
        android:textAppearance="@android:style/TextAppearance.Large"
        android:textColor="@android:color/white"
        android:textStyle="bold" />

    <ScrollView
        android:id="@+id/scroll_main"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_heading">

        <LinearLayout
            android:id="@+id/linear_layout_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:id="@+id/article_subheading"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:padding="@dimen/padding_regular"
                android:text="@string/article_subtitle"
                android:textAppearance="@android:style/TextAppearance" />

            <EditText
                android:id="@+id/article"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:autoLink="web"
                android:focusable="false"
                android:lineSpacingExtra="@dimen/line_spacing"
                android:text="@string/article_text" />

            <Button
                android:id="@+id/editArticle"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Edit Article" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <Button
                    android:id="@+id/addComment"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Add Comment" />

                <EditText
                    android:id="@+id/inputComment"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:visibility="invisible" />

            </LinearLayout>

            <LinearLayout
                android:id="@+id/comments"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">

            </LinearLayout>

        </LinearLayout>
    </ScrollView>

</RelativeLayout>
```

</details>

</li>

<li>

<details>

**<summary>(land)</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/padding_regular">

    <Button
        android:id="@+id/goToAdditionalExercise"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Additional Exercise" />

    <TextView
        android:id="@+id/article_heading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/goToAdditionalExercise"
        android:background="@color/colorPrimary"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_title"
        android:textAppearance="@android:style/TextAppearance.Large"
        android:textColor="@android:color/white"
        android:textStyle="bold" />

    <ScrollView
        android:id="@+id/scroll_main"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_heading">

        <LinearLayout
            android:id="@+id/linear_layout_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:id="@+id/article_subheading"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:padding="@dimen/padding_regular"
                android:text="@string/article_subtitle"
                android:textAppearance="@android:style/TextAppearance" />

            <EditText
                android:id="@+id/article"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:autoLink="web"
                android:focusable="false"
                android:lineSpacingExtra="@dimen/line_spacing"
                android:text="@string/article_text" />

            <Button
                android:id="@+id/editArticle"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Edit Article" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <Button
                    android:id="@+id/addComment"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Add Comment" />

                <EditText
                    android:id="@+id/inputComment"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:visibility="invisible" />

            </LinearLayout>

            <LinearLayout
                android:id="@+id/comments"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">

            </LinearLayout>

        </LinearLayout>
    </ScrollView>

</RelativeLayout>
```

</details>

</li>

</ul>

</li>

<li>

<details>

**<summary>`activity_additional_exercise.xml`</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/padding_regular">

    <Button
        android:id="@+id/startAgain"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Start Again" />

    <TextView
        android:id="@+id/article_heading"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/startAgain"
        android:background="@color/colorPrimary"
        android:padding="@dimen/padding_regular"
        android:text="@string/article_title"
        android:textAppearance="@android:style/TextAppearance.Large"
        android:textColor="@android:color/white"
        android:textStyle="bold" />

    <ScrollView
        android:id="@+id/scroll_main"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/article_heading">

        <LinearLayout
            android:id="@+id/linear_layout_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:id="@+id/article_subheading"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:width="100dp"
                android:padding="@dimen/padding_regular"
                android:text="@string/article_subtitle"
                android:textAppearance="@android:style/TextAppearance" />

            <TextView
                android:id="@+id/article"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:autoLink="web"
                android:lineSpacingExtra="@dimen/line_spacing"
                android:text="@string/article_text" />

        </LinearLayout>
    </ScrollView>

</RelativeLayout>
```

</details>

</li>

</ul>

</li>

</ul>
