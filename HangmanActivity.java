package project01.csc296.project01;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HangmanActivity extends AppCompatActivity {

    public Button mButtonSubmit, mButtonCancel;

    public TextView mDisplayedWord, mTextViewGuessesRemaining;

    public EditText mEditTextGuess;

    public Hangman hangman;

    public String LOG = "IN HANGMAN";

    public String KEY_WINNER = "WinningPlayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        hangman = new Hangman();

        //INSTANTIATION

        mDisplayedWord = (TextView) findViewById(R.id.TextView_DisplayWord);

        mButtonSubmit = (Button) findViewById(R.id.Button_HMSubmit);

        mEditTextGuess = (EditText) findViewById(R.id.EditText_LetterGuess);

        mTextViewGuessesRemaining = (TextView) findViewById(R.id.TextView_GuessesRemaining);



        //Get the hidden word
        mDisplayedWord.setText(hangman.getDisplayedWord());

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guess = mEditTextGuess.getText().toString();

                //if the user only submitted one character
                if (guess.length() == 1) {
                    //if the char is in the word
                    if (hangman.inWord(guess)) {

                        Log.i(LOG, "Correct Guess was Made!");

                        //modify the displayed word and set the Text View
                        mDisplayedWord.setText(hangman.modifyDisplayedWord(guess));

                        //check win conditions met
                        if (hangman.win() != 0) {

                            String name = MainActivityLogic.getPlayerName(hangman.win());

                            Toast.makeText(HangmanActivity.this, getString(R.string.Toast_Win) + " " + name + " won!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent();

                            intent.putExtra(KEY_WINNER, hangman.win());

                            setResult(RESULT_OK, intent);

                            Log.i(LOG, "About to Finish()!");

                            finish();

                        } else {

                            if (hangman.noMoreGuesses()) {

                                Toast.makeText(HangmanActivity.this, R.string.Toast_Tie, Toast.LENGTH_LONG).show();

                                setResult(RESULT_CANCELED);

                                finish();

                            }

                            hangman.nextTurn();
                        }

                    } else {

                        Toast.makeText(HangmanActivity.this, R.string.Warning_HMNotValidLetter, Toast.LENGTH_SHORT).show();
                        hangman.wrongLetter();

                        mTextViewGuessesRemaining.setText(Integer.toString(hangman.getGuessesRemaining()));

                        if (hangman.noMoreGuesses()) {

                            Toast.makeText(HangmanActivity.this, R.string.Toast_Tie, Toast.LENGTH_LONG).show();

                            setResult(RESULT_CANCELED);

                            finish();

                        }

                    }
                } else {
                    Toast.makeText(HangmanActivity.this, R.string.title_HMTooManyLetters, Toast.LENGTH_SHORT).show();
                }


            }
        });

        // CANCEL BUTTON
        mButtonCancel = (Button) findViewById(R.id.Button_HMCancel);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);

                finish();

            }
        });


        // ***** SETTING UP FRAGMENT *****

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        //Setting up fragment
        FragmentManager fm = getFragmentManager();
        Fragment Frag_ScoreBoard = fm.findFragmentById(R.id.FrameLayout_Frag);


        if(Frag_ScoreBoard == null) {

            Log.i(LOG, "Making new Fragment");

            Frag_ScoreBoard = new ScoreBoardFragment();
            Frag_ScoreBoard.setArguments(extras);

            fm.beginTransaction().add(R.id.FrameLayout_ForFragHM, Frag_ScoreBoard).commit();
        }


    }
}
