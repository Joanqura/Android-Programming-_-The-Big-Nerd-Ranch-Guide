package com.joanqura.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity
{
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionsBank = new Question[]
            {
                    new Question(R.string.question_oceans,true),
                    new Question(R.string.question_mideast,false),
                    new Question(R.string.question_africa,false),
                    new Question(R.string.question_americas,true),
                    new Question(R.string.question_asia,true),
            };
    private int mCurrentIndex =0;
    private void updateQuestion()
    {
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer (Boolean userPressdTrue)
    {
        Boolean answerTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if (userPressdTrue == answerTrue)
        {
            messageResId = R.string.correct_toast;
        }
        else
        {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    checkAnswer(true);
                }
            });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    checkAnswer(false);
                }
            });
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                    updateQuestion();
                }
            });
        updateQuestion();
    }

}