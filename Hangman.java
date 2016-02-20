package project01.csc296.project01;

import android.util.Log;

import java.util.Random;

/**
 * Created by KEdgette1 on 10/9/15.
 */
public class Hangman {

    //words to chose from
    public String[] dictionary = {"chocolate", "pyramid", "shampoo", "popcorn", "helicopter", "bookcase", "heirloom", "pyjamas", "leather", "laundry"};

    public String currentWord, displayedWord;

    int wordLength;

    public int guessesRemaining;

    public String LOG = "IN HANGMAN LOGIC";

    int turnCounter;

    public Hangman(){

        //get a random word from "dictionary"

        Random random = new Random();

        int guess = random.nextInt(9);

        currentWord = dictionary[guess];

        Log.i(LOG, "Current Word is: " + currentWord);

        wordLength = currentWord.length();

        //Create displayed word
        displayedWord = "*";

        for (int i = 0; i < wordLength - 1; i++) {
            displayedWord = displayedWord + "*";
        }

        guessesRemaining = 8;
        turnCounter = 1;

    }

    //is the char in the word?
    public boolean inWord(String s) {

        if(currentWord.contains(s)) {
            Log.i(LOG, "Letter In Word!");
            return true;

        }
        else {
            return false;
        }
    }

    //find locations of char entered and replace "*" in displayed word with the char at those locations
    public String modifyDisplayedWord(String s) {

        char guess = s.charAt(0);

        char [] currentWordChars = currentWord.toCharArray();
        char [] displayedWordChars = displayedWord.toCharArray();

        Log.i(LOG, "Current Guess: " + guess);

        for(int i = 0; i < wordLength; i++) {

            if(currentWordChars[i] == guess) {
                Log.i(LOG, "Changing Displayed Word");
                displayedWordChars[i] = guess;
            }

        }

        displayedWord = new String(displayedWordChars);

        return displayedWord;

    }

    public void wrongLetter() {
        guessesRemaining--;
    }

    public int getGuessesRemaining() {
        return guessesRemaining;
    }

    public int win() {

        if(currentWord.equals(displayedWord)) {
               if(turnCounter % 2 == 1) {
                   return 1;
               }
            else{
                   return 2;
               }
        }

        return 0;

    }

    public void nextTurn() {
        turnCounter++;
    }

    public boolean noMoreGuesses() {
        if(guessesRemaining == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getDisplayedWord() {
        return displayedWord;
    }


}
