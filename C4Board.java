package project01.csc296.project01;

import android.util.Log;

/**
 * Created by KEdgette1 on 10/8/15.
 */
public class C4Board  {

    public int [][] board;

    public int rows, columns;

    public int turnCounter;

    public String LOG = "IN C4 LOGIC";

    //constructor
    public C4Board(int rows, int columns) {

        board = new int [rows][columns];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {

                board[i][j] = 0;
            }
        }

        this.rows = rows;
        this.columns = columns;

        turnCounter = 1;


    }

    public boolean colValid(int column) {

        for(int i = 0; i < rows; i++) {

            if(board[i][column] == 0) {
                return true;
            }


        }

        return false;

    }

    public int rowToFill(int column) {

        for(int i = rows-1; i >= 0; i--) {

            if(board[i][column] == 0) {
                return i;
            }
        }

        return -1;

    }

    public boolean p1Turn() {

        if(turnCounter % 2 == 1) {

            return true;
        }
        else {

            return false;
        }

    }

    public void nextTurn() {
        turnCounter++;
    }

    public void makeMove(int row, int col, int player) {

        board[row][col] = player;

    }

    //WIN CONDITIONS
    public int winner() {

        int k = 0;

        //HORIZONTAL WIN CONDITIONS

        for(int i = 0; i < rows; i++) {

            k=0;

            if(board[i][k] == board[i][k+1] && board[i][k+1] == board[i][k+2] && board[i][k+2] == board[i][k+3]) {
                if(board[i][k] != 0) return board[i][k];
            }

            k=1;

            if(board[i][k] == board[i][k+1] && board[i][k+1] == board[i][k+2] && board[i][k+2] == board[i][k+3]) {
                if(board[i][k] != 0) return board[i][k];
            }


        }

        //VERTICAL WIN CONDITIONS

        for(int i = 0; i < columns; i++) {

            k=0;

            if(board[k][i] == board[k+1][i] && board[k+1][i] == board[k+2][i] && board[k+2][i] == board[k+3][i]) {
                if(board[k][i] != 0) return board[k][i];
            }

            k=1;

            if(board[k][i] == board[k+1][i] && board[k+1][i] == board[k+2][i] && board[k+2][i] == board[k+3][i]) {
                if(board[k][i] != 0) return board[k][i];

            }

        }

        //DIAGONAL WIN CONDITIONS

        //From left to right

        k = 0;
        int l = 3;

        if(board[l][k] == board[l-1][k+1] && board[l-1][k+1] == board[l-2][k+2] && board[l-2][k+2] == board[l-3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        k = 1;

        if(board[l][k] == board[l-1][k+1] && board[l-1][k+1] == board[l-2][k+2] && board[l-2][k+2] == board[l-3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        k = 0;
        l = 4;

        if(board[l][k] == board[l-1][k+1] && board[l-1][k+1] == board[l-2][k+2] && board[l-2][k+2] == board[l-3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        k = 1;

        if(board[l][k] == board[l-1][k+1] && board[l-1][k+1] == board[l-2][k+2] && board[l-2][k+2] == board[l-3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        //From right to left

        k = 0;
        l = 0;

        if(board[l][k] == board[l+1][k+1] && board[l+1][k+1] == board[l+2][k+2] && board[l+2][k+2] == board[l+3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        k = 1;

        if(board[l][k] == board[l+1][k+1] && board[l+1][k+1] == board[l+2][k+2] && board[l+2][k+2] == board[l+3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        k = 1;
        l = 1;

        if(board[l][k] == board[l+1][k+1] && board[l+1][k+1] == board[l+2][k+2] && board[l+2][k+2] == board[l+3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        k= 0;

        if(board[l][k] == board[l+1][k+1] && board[l+1][k+1] == board[l+2][k+2] && board[l+2][k+2] == board[l+3][k+3]) {
            if(board[l][k] != 0) return board[l][k];
        }

        return 0;
    }


    public int getValue(int r, int c) {
        return board[r][c];
    }


}
