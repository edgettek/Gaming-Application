package project01.csc296.project01;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by KEdgette1 on 10/6/15.
 */
public class MainActivityLogic {

    public static String LOG = "MainActivityLogic";

    public static String player1Name, player2Name;

    public static int player1Score = 0;
    public static int player2Score = 0;

    public static void setNames(String p1, String p2) {
        player1Name = p1;
        player2Name = p2;
    }


    public static String getPlayerName(int i) {

        if(i == 1) {
            return player1Name;
        }

        if(i == 2) {
            return player2Name;
        }

        return "No Player Found!";

    }


    public static void updateScore(int requestCode) {

        Log.i(LOG, "Updating Score");

        if(requestCode == 1) {
            player1Score++;
        }

        if(requestCode == 2) {
            player2Score++;
        }


    }

    public static int getScoreP1() {

       return player1Score;

    }

    public static int getScoreP2() {

        return player2Score;

    }


}
