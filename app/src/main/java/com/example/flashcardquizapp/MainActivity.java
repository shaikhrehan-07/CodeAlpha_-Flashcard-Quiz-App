package com.example.flashcardquizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Questions
    String[] questions = {
            "What is Java?",
            "What is OOP?",
            "What is Android?",
            "What is an Activity?"
    };

    // Answers
    String[] answers = {
            "Java is a high-level, object-oriented programming language.",
            "OOP stands for Object-Oriented Programming.",
            "Android is an operating system mainly used for mobile devices.",
            "An Activity is a screen or component of an Android application."
    };

    // Current flashcard number
    int currentIndex = 0;

    TextView questionText,answerText;

    Button showAnswerButton,previousButton,nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect Java with XML
        questionText = findViewById(R.id.questionText);
        answerText = findViewById(R.id.answerText);

        showAnswerButton = findViewById(R.id.showAnswerButton);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        // Show first question
        updateFlashcard();

        // Show Answer button
        showAnswerButton.setOnClickListener(v -> {
            answerText.setText(answers[currentIndex]);
        });

        // Next button
        nextButton.setOnClickListener(v -> {

            if (currentIndex < questions.length - 1) {
                currentIndex++;
                updateFlashcard();
            }
        });

        // Previous button
        previousButton.setOnClickListener(v -> {

            if (currentIndex > 0) {
                currentIndex--;
                updateFlashcard();
            }
        });

        // System bar padding
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }

    // Update question and hide answer
    private void updateFlashcard() {

        questionText.setText(questions[currentIndex]);

        // Answer hide/clear when changing card
        answerText.setText("");

        // Enable/disable navigation buttons
        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < questions.length - 1);
    }
}