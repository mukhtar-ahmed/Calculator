package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //  Variable
    TextView totalQuestionCount,question;
    Button ch1,ch2,ch3, ch4,submit;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex  = 0;
    String selectedAns = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        totalQuestionCount = findViewById(R.id.totalQuestionCount);
        question = findViewById(R.id.question);
        ch1 = findViewById(R.id.ch1);
        ch2 = findViewById(R.id.ch2);
        ch3 = findViewById(R.id.ch3);
        ch4 = findViewById(R.id.ch4);
        submit = findViewById(R.id.submit);

        ch1.setOnClickListener(this);
        ch2.setOnClickListener(this);
        ch3.setOnClickListener(this);
        ch4.setOnClickListener(this);
        submit.setOnClickListener(this);
        totalQuestionCount.setText(""+totalQuestion);
        loadNewQuestion();

    }

    private void loadNewQuestion() {
        if(currentQuestionIndex == totalQuestion)
        {
            finishQuiz();
            return;
        }
        question.setText(QuestionAnswer.question[currentQuestionIndex]);
        ch1.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ch2.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ch3.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ch4.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        String passStatus;
        if(score > totalQuestion * 0.60)
            passStatus= "Pass";
        else
            passStatus = "Failed";
        new AlertDialog.Builder(this).setTitle(passStatus)
                .setMessage("Score is "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz()).setCancelable(false).show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ch1.setBackgroundColor(Color.GRAY);
        ch2.setBackgroundColor(Color.GRAY);
        ch3.setBackgroundColor(Color.GRAY);
        ch4.setBackgroundColor(Color.GRAY);
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit)
        {
            if(selectedAns.equals(QuestionAnswer.correctAns[currentQuestionIndex]))
            {
                score =score +1;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else
        {
            selectedAns = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }
}