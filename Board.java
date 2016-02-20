package project01.csc296.project01;

import android.media.Image;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by KEdgette1 on 10/6/15.
 */
public class Board {

    public int turnCounter = 1;

    public int [][] squares;

    public int rows, columns;

    //constructor
    public Board(int rows, int columns){

        squares = new int [rows][columns];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {

                squares[i][j] = 0;
            }

        }

        this.rows = rows;
        this.columns = columns;

    }

    //is the space empty?
    public boolean validMove(int row, int col) {

        if(squares[row][col] == 0) {
            return true;
        }
        else {
            return false;
        }


    }

    public boolean p1Turn() {

        if(turnCounter % 2 == 1) {
            return true;
        }
        else {
            return false;
        }


    }

    //fill in the array
    public void makeMove(int row, int col, int playerNum) {

        squares[row][col] = playerNum;

    }

    public void nextTurn() {

        turnCounter++;
    }
    

    //WIN CONDITIONS
    public int gameOver(){

        for(int i = 0; i < 3; i++) {

            if(squares[i][0] == squares[i][1] && squares[i][1] == squares[i][2]) {
                return squares[i][0];
            }

            if(squares[0][i] == squares[1][i] && squares[1][i] == squares[2][i]) {
                return squares[0][i];
            }


        }


        //diagonal win conditions
        if(squares[0][0] == squares[1][1] && squares[1][1] == squares[2][2]){
            return squares[0][0];
        }

        if(squares[0][2] == squares[1][1] && squares[1][1] == squares[2][0]){
            return squares[0][2];
        }

        return -1;

    }

    public boolean noMoreMoves() {

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {

                if(squares[i][j] == 0) {
                    return false;
                }


            }
        }

        return true;
    }


    public int rowCount() {
        return rows;
    }

    public int colsCount() {
        return columns;
    }




}
