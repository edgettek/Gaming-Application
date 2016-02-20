package project01.csc296.project01;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity {

    public GridLayout mGridLayoutTTT;

    ImageButton[][] TTT = new ImageButton[3][3];

    public Button mButtonCancel;

    Board board;

    public String LOG = "IN TTT";

    public String KEY_WINNER = "WinningPlayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        mGridLayoutTTT = (GridLayout) findViewById(R.id.GridLayout_TicTacToe);

        //Adding Image Buttons to Grid Layout
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {

                TTT[i][j] = new ImageButton(this);
                TTT[i][j].setBackgroundColor(Color.GRAY);
                TTT[i][j].setOnClickListener(TTTListener);
                TTT[i][j].setMinimumHeight(150);
                TTT[i][j].setMinimumWidth(150);
                TTT[i][j].setImageResource(R.mipmap.ic_launcher);
                TTT[i][j].setTag(new Integer[] {i, j});

                mGridLayoutTTT.addView(TTT[i][j]);
            }
        }

        board = new Board(3,3);

        //CANCEL BUTTON
        mButtonCancel = (Button) findViewById(R.id.Button_TTTCancel);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);

                finish();
            }
        });

        // ***** SETTING UP CANCEL BUTTON *****

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        //Setting up fragment
        FragmentManager fm = getFragmentManager();
        Fragment Frag_ScoreBoard = fm.findFragmentById(R.id.FrameLayout_Frag);


        if(Frag_ScoreBoard == null) {

            Log.i(LOG, "Making new Fragment");

            Frag_ScoreBoard = new ScoreBoardFragment();
            Frag_ScoreBoard.setArguments(extras);

            fm.beginTransaction().add(R.id.FrameLayout_FragForTTT, Frag_ScoreBoard).commit();
        }


    }

    private View.OnClickListener TTTListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int rowNum, colNum;

            //Find which button was pressed
            rowNum = ((Integer []) v.getTag())[0];
            colNum = ((Integer []) v.getTag())[1];

            //if move is valid
            if(board.validMove(rowNum, colNum)) {

                //if it is p1's turn
                if(board.p1Turn()) {

                    ((ImageButton) v).setImageResource(R.mipmap.ttt_x);

                    board.makeMove(rowNum, colNum, 1);

                }
                //otherwise it is p2's turn
                else {
                    ((ImageButton) v).setImageResource(R.mipmap.ttt_o);

                    board.makeMove(rowNum, colNum, 2);
                }

                board.nextTurn();

            }
            else {

                Toast.makeText(TicTacToeActivity.this, R.string.Warning_NotValidMove, Toast.LENGTH_SHORT).show();

            }

            //check if the game is over
            if(board.gameOver() != 0) {

                int winningPlayer = board.gameOver();
                Log.i(LOG, "Someone won! Player that won: " + winningPlayer);

                if(winningPlayer != -1) {
                    String name = MainActivityLogic.getPlayerName(winningPlayer);

                    Toast.makeText(TicTacToeActivity.this, getString(R.string.Toast_Win) + " " + name + " won!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();

                    intent.putExtra(KEY_WINNER, winningPlayer);

                    setResult(RESULT_OK, intent);

                    Log.i(LOG, "About to Finish()!");

                    finish();
                }

                if(winningPlayer == -1 && board.noMoreMoves() == true) {

                    Toast.makeText(TicTacToeActivity.this, R.string.Toast_Tie , Toast.LENGTH_LONG).show();

                    setResult(0);

                    finish();

                }

            }


        }
    };

}
