package com.gym.leon.maptest.view;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.gym.leon.maptest.MainActivity;
import com.gym.leon.maptest.QuestionList;
import com.gym.leon.maptest.R;

public class MarkerDetailView extends AppCompatActivity {
    private QuestionList mQuestionLibrary = new QuestionList();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonGoBack;
    private String mAnswer;
    public int mQuestionNumber = 1 ;
    public Bundle b;
    private Intent i;
    private int Score = 0;

    public MarkerDetailView() {

    }


   /* public void getQuestionID(int i){
        this.mQuestionNumber = i;
        Log.d("Nummer_In_Funktion : ", Integer.toString(mQuestionNumber));
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Nummer", Integer.toString(mQuestionNumber));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        b = getIntent().getExtras();
        mQuestionNumber = b.getInt("key");
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonGoBack = (Button)findViewById(R.id.quit);
        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice1.getText() == mAnswer){

                    //This line of code is optiona
                    Toast.makeText(MarkerDetailView.this, "correct", Toast.LENGTH_SHORT).show();
                    i = new Intent(view.getContext(), MainActivity.class);
                    startActivity(i);

                }else {
                    Toast.makeText(MarkerDetailView.this, "wrong", Toast.LENGTH_SHORT).show();
                    i = new Intent(view.getContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice2.getText() == mAnswer){
                    //This line of code is optiona
                    Toast.makeText(MarkerDetailView.this, "correct", Toast.LENGTH_SHORT).show();
                    i = new Intent(view.getContext(), MainActivity.class);

                    startActivity(i);

                }else {
                    Toast.makeText(MarkerDetailView.this, "wrong", Toast.LENGTH_SHORT).show();
                    i = new Intent(view.getContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice3.getText() == mAnswer){
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(MarkerDetailView.this, "correct", Toast.LENGTH_SHORT).show();
                    i = new Intent(view.getContext(), MainActivity.class);
                    startActivity(i);

                }else {
                    Toast.makeText(MarkerDetailView.this, "wrong", Toast.LENGTH_SHORT).show();
                    i = new Intent(view.getContext(), MainActivity.class);
                    startActivity(i);
                }

            }
        });

        //End of Button Listener for Button3
        mButtonGoBack.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });





    }


    public void updateQuestion(){
        Log.d("Nummer___ : ", Integer.toString(this.mQuestionNumber));
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
        Log.d("Nummer:" , Integer.toString(mQuestionNumber));
        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
    }




}