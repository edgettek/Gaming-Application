package project01.csc296.project01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.microedition.khronos.egl.EGLDisplay;

public class MainActivity extends AppCompatActivity {

    public String LOG = "MainActivity";

    public static String player1Name, player2Name;

    //KEY VALUES
    public String KEY_WINNER = "WinningPlayer";
    public static final String KEY_P1Name = "project01.csc296.P1Name";
    public static final String KEY_P2Name = "project01.csc296.P2Name";
    public String KEY_P1Score = "Player1Score";
    public String KEY_P2Score = "Player2Score";

    //BUTTONS TO START ACTIVITIES
    public Button mButtonOkName, mButtonStartTTT, mButtonStartHM, mButtonStartC4;

    public EditText mP1Slot, mP2Slot;

    public TextView mP1Name, mP2Name;

    public TextView mP1ScoreBoard, mP2ScoreBoard;

    //REQUEST CODES
    public final int RC_TTT = 1;
    public final int RC_C4 = 2;
    public final int RC_HM = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG, "calling onCreate()");

        mP1ScoreBoard = (TextView) findViewById(R.id.TextView_P1Score);
        mP2ScoreBoard = (TextView) findViewById(R.id.TextView_P2Score);


        // ***** SETTING PLAYER NAMES *****
        mButtonOkName = (Button) findViewById(R.id.Button_OKPlayerNames);

        mButtonOkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mP1Slot = (EditText) findViewById(R.id.EditText_P1);
                mP2Slot = (EditText) findViewById(R.id.EditText_P2);

                mP1Name = (TextView) findViewById(R.id.TextView_P1Name);
                mP2Name = (TextView) findViewById(R.id.TextView_P2Name);


                player1Name = mP1Slot.getText().toString();
                player2Name = mP2Slot.getText().toString();

                mP1Name.setText(player1Name);
                mP2Name.setText(player2Name);

                MainActivityLogic.setNames(player1Name, player2Name);

                mP1Slot.setEnabled(false);
                mP2Slot.setEnabled(false);

            }
        });


        // ***** SETTING UP TIC TAC TOE *****

        mButtonStartTTT = (Button) findViewById(R.id.button_StartTicTac);

        mButtonStartTTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TicTacToeActivity.class);

                intent.putExtra(KEY_P1Name, MainActivityLogic.getPlayerName(1));
                intent.putExtra(KEY_P2Name, MainActivityLogic.getPlayerName(2));
                intent.putExtra(KEY_P1Score, MainActivityLogic.getScoreP1());
                intent.putExtra(KEY_P2Score, MainActivityLogic.getScoreP2());

                startActivityForResult(intent, RC_TTT);


            }
        });

        // ***** SETTING UP CONNECT 4 *****

        mButtonStartC4 = (Button) findViewById(R.id.button_StartConnect4);

        mButtonStartC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Connect4.class);

                intent.putExtra(KEY_P1Name, MainActivityLogic.getPlayerName(1));
                intent.putExtra(KEY_P2Name, MainActivityLogic.getPlayerName(2));

                Log.i(LOG, "P1 score: " + MainActivityLogic.getScoreP1());
                intent.putExtra(KEY_P1Score, MainActivityLogic.getScoreP1());
                intent.putExtra(KEY_P2Score, MainActivityLogic.getScoreP2());


                startActivityForResult(intent, RC_C4);



            }
        });

        // ***** SETTING UP HANGMAN *****

        mButtonStartHM = (Button) findViewById(R.id.button_StartHangMan);

        mButtonStartHM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, HangmanActivity.class);

                intent.putExtra(KEY_P1Name, MainActivityLogic.getPlayerName(1));
                intent.putExtra(KEY_P2Name, MainActivityLogic.getPlayerName(2));
                intent.putExtra(KEY_P1Score, MainActivityLogic.getScoreP1());
                intent.putExtra(KEY_P2Score, MainActivityLogic.getScoreP2());


                startActivityForResult(intent, RC_HM);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int winner;

        Log.i(LOG, "Made it back to MainActivity");

        //RETURNING FROM TIC TAC TOE
        if(requestCode==RC_TTT && resultCode==RESULT_OK && data!=null){

            Log.i(LOG, "We have returned from TTT");

            Bundle bundle = data.getExtras();

            winner = bundle.getInt(KEY_WINNER, -1);

            MainActivityLogic.updateScore(winner);

            updateScoreBoard();

        }


        //RETURNING FROM CONNECT 4
        if(requestCode==RC_C4 && resultCode==RESULT_OK && data!=null){

            Log.i(LOG, "We have returned from C4");

            Bundle bundle = data.getExtras();

            winner = bundle.getInt(KEY_WINNER, -1);

            MainActivityLogic.updateScore(winner);

            updateScoreBoard();

        }

        //RETURNING FROM HANGMAN
        if(requestCode==RC_HM && resultCode==RESULT_OK && data!=null){

            Log.i(LOG, "We have returned from HANGMAN");

            Bundle bundle = data.getExtras();

            winner = bundle.getInt(KEY_WINNER, -1);

            MainActivityLogic.updateScore(winner);

            updateScoreBoard();

        }

    }


    //UPDATE SCOREBOARD ON MAIN SCREEN
    public void updateScoreBoard(){


        mP1ScoreBoard.setText(Integer.toString(MainActivityLogic.getScoreP1()));
        mP2ScoreBoard.setText(Integer.toString(MainActivityLogic.getScoreP2()));


    }




}
