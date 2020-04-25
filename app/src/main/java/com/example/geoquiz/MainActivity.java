package com.example.geoquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true), //pergunta e resposta certa
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        //pega a id da pergunta selecionada no novo index
        mQuestionTextView.setText(question);
        //novo texto no espaço de pergunta
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion(); //update do texto que ta no textview da pergunta
        /**mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });*/

        //referenciou o botao
        mTrueButton = (Button) findViewById(R.id.true_button);
        //ação pela qual espera
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //o que acontece quando clica
                checkAnswer(true);

            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

            }
        });

        //referenciou o botao
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        //ação pela qual espera
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //o que acontece quando clica
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                //o index sobe. algoritmo da pizza/roda lembra
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        //ação pela qual espera
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //o que acontece quando clica
                if (mCurrentIndex > 0) mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                else mCurrentIndex = mQuestionBank.length - 1;
                //o index sobe. algoritmo da pizza/roda lembra
                updateQuestion();
            }
        });



    }
}
