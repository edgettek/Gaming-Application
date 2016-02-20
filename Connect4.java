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
import android.widget.ImageView;
import android.widget.Toast;

public class Connect4 extends AppCompatActivity {

    public GridLayout mGridLayout;

    public String LOG = "IN C4";

    public String KEY_WINNER = "WinningPlayer";

    public Button mButtonCancel;

    public ImageButton[] ColButtons;

    public ImageView[][] spaces;

    public C4Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4);


        mGridLayout = (GridLayout) findViewById(R.id.GridLayout_Connect4);

        //***** BUTTONS ABOVE COLUMNS *****
        ColButtons = new ImageButton[5];

        for(int i = 0; i < 5; i++) {

            ColButtons[i] = new ImageButton(this);
            ColButtons[i].setBackgroundColor(Color.GRAY);
            ColButtons[i].setOnClickListener(C4Listener);
            ColButtons[i].setMinimumHeight(150);
            ColButtons[i].setMinimumWidth(150);
            ColButtons[i].setImageResource(R.mipmap.down_arrow);
            ColButtons[i].setTag(new Integer(i));

            mGridLayout.addView(ColButtons[i]);

        }

        // ***** IMAGEVIEWS *****

        spaces = new ImageView[5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {

                spaces[i][j] = new ImageView(this);
                spaces[i][j].setImageResource(R.mipmap.connect_blank);

                mGridLayout.addView(spaces[i][j]);
            }

        }

        board = new C4Board(5, 5);


        //CANCEL BUTTON
        mButtonCancel = (Button) findViewById(R.id.Button_C4Cancel);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);

                finish();

            }
        });


        // ***** SETTING UP SCOREBOARD FRAGMENT *****

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        //Setting up fragment
        FragmentManager fm = getFragmentManager();
        Fragment Frag_ScoreBoard = fm.findFragmentById(R.id.FrameLayout_Frag);


        if(Frag_ScoreBoard == null) {

            Log.i(LOG, "Making new Fragment");

            Frag_ScoreBoard = new ScoreBoardFragment();
            Frag_ScoreBoard.setArguments(extras);

            fm.beginTransaction().add(R.id.FrameLayout_ForFragC4, Frag_ScoreBoard).commit();
        }

    }

    private View.OnClickListener C4Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int column = (Integer) v.getTag();

            // if column has space available
            if(board.colValid(column)) {

                //find lowest row to fill
                int row = board.rowToFill(column);

                //p1 makes the move if it is their turn
                if(board.p1Turn()) {

                    spaces[row][column].setImageResource(R.mipmap.connect_red);

                    board.nextTurn();

                    board.makeMove(row, column, 1);

                }
                //otherwise p2 makes the move
                else {

                    spaces[row][column].setImageResource(R.mipmap.connect_blue);

                    board.nextTurn();

                    board.makeMove(row, column, 2);

                }

                int winner = board.winner();

                //check if the winner is an actual player
                if(winner != 0){

                        Log.i(LOG, "Player " + winner + " won!");

                        String name = MainActivityLogic.getPlayerName(winner);

                        Toast.makeText(Connect4.this, getString(R.string.Toast_Win) + " " + name  + " won!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent();

                        intent.putExtra(KEY_WINNER, winner);

                        setResult(RESULT_OK, intent);

                        Log.i(LOG, "About to Finish()!");

                        finish();
                    
                }


            }
            else {

                Toast.makeText(Connect4.this, R.string.Warning_NotValidMove, Toast.LENGTH_SHORT).show();

            }

        }
    };



}
