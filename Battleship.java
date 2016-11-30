/**
 * BATTLESHIP Version 1.0
 * @author Jacob Miecznikowski
 * May 02, 2016
 */

import java.util.Scanner;
import java.util.Random;

public class Battleship {
    static Board computerBoard = new Board();
    static Board guessBoard = new Board();
    static Board playerBoard = new Board();
    
    static Ship cPatrol, cSub, cBattle, pPatrol, pSub, pBattle;
    
    static Scanner reader = new Scanner(System.in);
    static Random rand = new Random();
    static boolean gameOver = false;
    
    static int firstHit = 0;
    static int x;
    static int y;
    
    static boolean shownPlayerPatrol, shownPlayerSub, shownPlayerBattle, shownCompPatrol, shownCompSub, shownCompBattle = false;
    
    public static void main(String[] args) {
        
        printTitle();
        createPlayerBoard();
        createComputerBoard(computerBoard);
        
        clearScreen(42);
        printTitle();
        
        while (!gameOver){
            enterGuess();
            updateDisplay();
            computerEnterGuess();
            updateDisplay();
            
            if (playerBoard.getSunkPlayer(pPatrol) && shownPlayerPatrol == false){
                System.out.println("Your patrol ship is sunk!");
                shownPlayerPatrol = true;
            }
            
            if (playerBoard.getSunkPlayer(pSub) && shownPlayerSub == false){
                System.out.println("Your submarine is sunk!");
                shownPlayerSub = true;
            }
            
            if (playerBoard.getSunkPlayer(pBattle) && shownPlayerBattle == false){
                System.out.println("Your battleship is sunk!");
                shownPlayerBattle = true;
            }
            
            if (guessBoard.getSunk(cPatrol) && shownCompPatrol == false){
                System.out.println("The computer's patrol ship is sunk!");
                shownCompPatrol = true;
            }
            
            if (guessBoard.getSunk(cSub) && shownCompSub == false){
                System.out.println("The computer's submarine is sunk!");
                shownCompSub = true;
            }
            
            if (guessBoard.getSunk(cBattle) && shownCompBattle == false){
                System.out.println("The computer's battleship is sunk!");
                shownCompBattle = true;
            }
            gameOver = isGameOver();
        }
    }
    
    public void testSunkPlayer(Ship ship){
        if (playerBoard.getSunk(ship)){
            System.out.println("Ship Sunk!");
        }
    }
    
    public static void createPlayerBoard(){
        Scanner reader = new Scanner(System.in);
        
        
        int xPatrol, yPatrol, xSub, ySub, xBattle, yBattle;
        int lPatrol = 2;
        int lSub = 3;
        int lBattle = 4;
        
        System.out.println("Enter the x location of the patrol ship (from top left): ");
        xPatrol = reader.nextInt();
        
        System.out.println("Enter the y location of the patrol ship (from top left): ");
        yPatrol = reader.nextInt();
        
        System.out.println("Enter the orientation of the patrol ship (left, up, down, right): ");
        String oPatrol = reader.next();
        
        pPatrol = new Ship(lPatrol, yPatrol - 1, xPatrol - 1, oPatrol);
        playerBoard.place(pPatrol);
                
        playerBoard.show();
        
        //Create the submarine
        System.out.println("Enter the x location of the submarine(from top left): ");
        xSub = reader.nextInt();
        
        System.out.println("Enter the y location of the submarine (from top left): ");
        ySub = reader.nextInt();
        
        System.out.println("Enter the orientation of the submarine (left, up, down, right): ");
        String oSub = reader.next();
        
        pSub = new Ship(lSub, ySub - 1, xSub - 1, oSub);
        playerBoard.place(pSub);
        
        playerBoard.show();
        
        //create battleship
        
        System.out.println("Enter the x location of the battleship (from top left): ");
        xBattle = reader.nextInt();
        
        System.out.println("Enter the y location of the abttleship (from top left): ");
        yBattle = reader.nextInt();
        
        System.out.println("Enter the orientation of the battleship (left, up, down, right): ");
        String oBattle = reader.next();
        
        pBattle = new Ship(lBattle, yBattle - 1, xBattle - 1, oBattle);
        playerBoard.place(pBattle);
        
        playerBoard.show();
//        foo = reader.next();
//        clearScreen(35);
        
    }
    
    public static void createComputerBoard(Board computerBoard){
        
        int xPatrol, yPatrol, xSub, ySub, xBattle, yBattle, oPatrolInt, oSubInt, oBattleInt;
        int lPatrol = 2;
        int lSub = 3;
        int lBattle = 4;
        String oPatrol;
        String oSub;
        String oBattle;
        
        xPatrol = rand.nextInt(10);
        yPatrol = rand.nextInt(10);
        
        
        //generate iteger between 1 and 4 to determine orientation
        oPatrolInt = rand.nextInt(4);
        oPatrol = getOrientationFromInteger(oPatrolInt);
        
        //change orientation if it is out of bounds
        if (xPatrol < lPatrol && oPatrol.equals("left")){
            oPatrol = "right";
        }
        
        if (xPatrol > (10 - lPatrol) && oPatrol.equals("right")){
            oPatrol = "left";
        }
        
        if (yPatrol < lPatrol && oPatrol.equals("up")){
            oPatrol = "down";
        }
        
        if (yPatrol > (10 - lPatrol) && oPatrol.equals("down")){
            oPatrol = "up";
        }
        
        
        //System.out.println(xPatrol + 1 + " " + (yPatrol + 1) + " " + oPatrol);
        
        
        cPatrol = new Ship(lPatrol, yPatrol, xPatrol, oPatrol);
        computerBoard.place(cPatrol);
        
        
        //create submarine
        
        xSub = rand.nextInt(10);
        ySub = rand.nextInt(10);
        
        
        //generate iteger between 1 and 4 to determine orientation
        oSubInt = rand.nextInt(4);
        oSub = getOrientationFromInteger(oSubInt);
        
        //change orientation if it is out of bounds
        if (xSub < lSub && oSub.equals("left")){
            oSub = "right";
        }
        
        if (xSub > (10 - lSub) && oSub.equals("right")){
            oSub = "left";
        }
        
        if (ySub < lSub && oSub.equals("up")){
            oSub = "down";
        }
        
        if (ySub > (10 - lSub) && oSub.equals("down")){
            oSub = "up";
        }
        
        
        //System.out.println(xSub + 1+ " " + (ySub + 1) + " " + oSub);
       
        cSub = new Ship(lSub, ySub, xSub, oSub);
        computerBoard.place(cSub);
                
        //create the batttleship
        
        xBattle = rand.nextInt(10);
        yBattle = rand.nextInt(10);
        
        
        //generate iteger between 1 and 4 to determine orientation
        oBattleInt = rand.nextInt(4);
        oBattle = getOrientationFromInteger(oBattleInt);
        
        //change orientation if it is out of bounds
        if (xBattle < lBattle && oBattle.equals("left")){
            oBattle = "right";
        }
        
        if (xBattle > (10 - lBattle) && oBattle.equals("right")){
            oBattle = "left";
        }
        
        if (yBattle < lBattle && oBattle.equals("up")){
            oBattle = "down";
        }
        
        if (yBattle > (10 - lBattle) && oBattle.equals("down")){
            oBattle = "up";
        }
        
        
        //System.out.println(xBattle + 1 + " " + (yBattle + 1) + " " + oBattle);
        
        
        cBattle = new Ship(lBattle, yBattle, xBattle, oBattle);
        computerBoard.place(cBattle);
        //computerBoard.show();
        //return computerBoard;
    }
    
    public static String getOrientationFromInteger(int orientationInt){
        switch (orientationInt){
            case 0:
                return "up";
            case 1:
                return "down";
            case 2:
                return "right";
            case 3:
                return "left";
            default:
                return null;
        }
    }
    

    
    public static void enterGuess(){
        
        //public static Board guessBoard = new Board();
        int x, y;
        
        System.out.println("Enter in the x coordinate of your guess: ");
        x = reader.nextInt();
        
        System.out.println("Enter in the y coordinate of your guess: ");
        y = reader.nextInt();

        //for (int i = 0; i< computerBoard.length; i)
        
        String[][] computerBoardArray = computerBoard.getArray();
        
        if (computerBoardArray[y - 1][x - 1].equals("X")){
            guessBoard.placeHit(x, y);
        } else{
            guessBoard.placeMiss(x, y);
        }
        
    }
    
    public static void computerEnterGuess(){
               
        String board[][] = playerBoard.getArray();
        
        int guessX = rand.nextInt(10);
        int guessY = rand.nextInt(10);
        
        //make sure the guesses are not where there is already a hit
        
        while (board[guessY][guessX].equals("+") || board[guessY][guessX].equals("O")){
                guessX = rand.nextInt(10);
                guessY = rand.nextInt(10);
                System.out.println();
        }
        
        System.out.println(guessX + " " + guessY);
        board = playerBoard.getArray();
        
        if (board[guessY][guessX].equals("X")){
            playerBoard.placePlus(guessX + 1, guessY + 1);
        }else if (board[guessY][guessX].equals(".")){
            playerBoard.placeMiss(guessX + 1, guessY + 1);
        }
        
        
//        if (firstHit == 0)
//            if (board[guessY - 1][guessX - 1].equals("X")){
//                playerBoard.placePlus(guessX, guessY);
//                firstHit = 2;
//                x = guessX;
//                y = guessY;
//            }else{
//                playerBoard.placeMiss(guessX, guessY);
//        }
//        
//        if (firstHit == 2){
//            if (board[y][x + 1].equals("X")){
//                playerBoard.placePlus(x + 1, y);
//                firstHit = 4;
//            }else{
//                firstHit = 0;
//                playerBoard.placeMiss(x + 1, y);
//            }
//        }
//        
//        if (firstHit == 4){
//            if (board[y][x + 2].equals("X")){
//                playerBoard.placePlus(x + 2, y);
//                firstHit = 6;
//            }else{
//                firstHit = 1;
//                playerBoard.placeMiss(x + 2, y);
//            }
//        }
//        
//        if (firstHit == 6){
//            if (board[y][x + 3].equals("X")){
//                playerBoard.placePlus(x + 3, y);
//                firstHit = 0;
//            }else{
//                firstHit = 1;
//            }
//        }
//        
//        
//        //firstHit2 means test to the left of the xy
//        if (firstHit == 3){
//            if (board[y][x - 1] == "X"){
//                playerBoard.placePlus(x - 1, y);
//                firstHit = 5;
//                
//            }else{
//                firstHit = 100;
//                playerBoard.placeMiss(x - 1, y);
//            }
//        }
   }
    
    public static void updateDisplay(){
        clearScreen(37);      
        printTitle();
        System.out.println();
        
        System.out.println("Your Guess Board:");
        System.out.println("____________________________"); 
        
        String guessBoardArray[][] = guessBoard.getArray();
        
        for (int i = 0; i < guessBoardArray.length; i++){
            for (int j = 0; j < guessBoardArray.length; j++){
                System.out.print(guessBoardArray[i][j] + "  ");
            }
            System.out.println();
        }
        
        System.out.println("____________________________");
        System.out.println();
        
        System.out.println("This is the player's board");
        System.out.println("____________________________");
        
        String playerBoardArray[][] = playerBoard.getArray();
        
        for (int i = 0; i < playerBoardArray.length; i++){
            for (int j = 0; j < playerBoardArray.length; j++){
                System.out.print(playerBoardArray[i][j] + "  ");
            }
            System.out.println();
        }
        
        System.out.println("____________________________");
    }
    
    public static boolean isGameOver(){
        if (guessBoard.getSunk(cPatrol) && guessBoard.getSunk(cSub) && guessBoard.getSunk(cBattle)){
            System.out.println("You win!");
            return true;
        }else if (playerBoard.getSunkPlayer(pPatrol) && playerBoard.getSunkPlayer(pSub) && playerBoard.getSunkPlayer(pBattle)){
            System.out.println("Game over, you lose!");
            return true;
        }else{
            return false;
        }  
    }    
    
    public static void printTitle(){
        System.out.println("                  ***BATTLESHIP***                 ");
        System.out.println();
    }
    
    public static void clearScreen(int lines){
        for (int i = 0; i < lines; ++i){
            System.out.println();
        }
    }
}