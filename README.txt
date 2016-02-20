README

Contact Information:

Name: Kyle Edgette
Course: CSC 296 FALL 2015
Assignment: Project 01
Date Due: October 9th, 2015


Description:

MainActivity contains EditTexts to set the two players' names, a scoreboard, and three Buttons 
to launch the three game activities. MainActivityLogic is used to store the player names and scores 
and has methods for retrieving the names and scores for each player.

TicTacToe activity contains a GridLayout with 9 ImageButtons. Each ImageButton is tagged with an 
Integer [] for its row and column number. The Board class has a constructor that generates a 2D 
array of ints, that represent spaces clicked by the players. Board also contains the logic for 
the win conditions of Tic Tac Toe. The Board class informs TicTacToeActivity if the move is valid, 
which piece should be displayed by the ImageButton, etc.

HangManActivity contains the layout for displaying the number of guesses remaining, the string that 
is composed of "*" for undiscovered letters and actual chars for discovered letters, an EditText for the 
user to enter their guess and a Button to submit their guess. The Hangman class contains the logic 
for generating the new word, seeing if the guess is valid, changing the "displayed" string to include 
discovered chars, etc. Hangman also contains the win conditions.

Connect4 contains the Grid Layout with Buttons above each column and and ImageViews for future pieces.
C4Board contains the game logic including if columns still have a space available for the next piece, 
where the piece would fall if placed in a column, and all of the various win conditions.

ScoreBoardFragment displays the players' names and their respective scores. Each of the game activities 
has a ScoreBoardFragment at the top of the screen.


Academic Honesty Pledge:

I affirm that I did not give or receive any unauthorized help on this project, and that all work is my 
own.

- Kyle A Edgette