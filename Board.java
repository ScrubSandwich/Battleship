
/**
 *
 * @author Jacob
 */

public class Board {
    private int boardSize = 10;
    private String board[][];
    
    public Board(){
        board = new String[boardSize][boardSize];
    
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                board[i][j] = ".";
            }            
        }    
    }
    
    public String[][] getArray(){
        return board;
    }
    
    //print out the board
    public void show(){
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
    //mark an X for each place the ship should go
    public void place(Ship ship){
        int x = ship.getX();
        int y = ship.getY();
        int length = ship.getLength();
        String orientation = ship.getOrientation();
        
        board[x][y] = "X";
        
        if (orientation.equals("up")){
            for (int i = 1; i < length; i++){
                if (board[x - i][y].equals("."))
                    board[x - i][y] = "X";
                else
                    System.out.println("ERROR");
                    //orientation = "down";
        
            }
        }    
        
        if (orientation.equals("down")){
            for (int i = 1; i < length; i++){
                if (board[x + i][y].equals("."))
                    board[x + i][y] = "X";
                else
                    System.out.println("ERROR");
                    //handleError(ship, board[x + i][y]);
                    //orientation = "up";
            }
        }
        
        if (orientation.equals("right")){
            for (int i = 1; i < length; i++){
                if (board[x][y + i].equals("."))
                    board[x][y + i] = "X";
                else
                    System.out.println("ERROR");
                    //handleError(ship, board[x][y + i]);
                    //orientation = "left";
            
            }
        }
        
        if (orientation.equals("left")){
            for (int i = 1; i < length; i++){
                
                if (board[x][y - i].equals("."))
                    board[x][y - i] = "X";
               
                else
                    System.out.println("ERROR");
                    //handleError(ship, board[x][y - i]);
                    //orientation = "right";
                
            }
        }
        
        
        
        
        
        
        
//        for (int i = 0; i < boardSize; i++){
//            for (int j = 0; j < boardSize; j++)
//                if (board[i][j].equals(".")){
//                    
////                    board[i][j] = ship.location[i][j];
//                    
//                } else if (!(board[i][j].equals("."))){
////                    System.out.println("Space Occupied");
////                    board[i][j] = ".";
//                    break;
//                }
//        }
    }
    
    
    public void handleError(Ship ship, String location){
            int x= ship.getX();
            int y = ship.getY();
        }
    
    public boolean getSunk(Ship ship){
        int x = ship.getX();
        int y = ship.getY();
        int length = ship.getLength();
        String orientation = ship.getOrientation();
        
        boolean sunk = false;
               
        if (orientation.equals("up")){
            for (int i = 1; i < length; i++){
                if (board[x - i][y].equals("X")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        }    
        
        if (orientation.equals("down")){
            for (int i = 1; i < length; i++){
                if (board[x + i][y].equals("X")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        } 
        
        if (orientation.equals("right")){
            for (int i = 1; i < length; i++){
                if (board[x][y + i].equals("X")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        } 
       
        
        if (orientation.equals("left")){
            for (int i = 1; i < length; i++){
                if (board[x][y - i].equals("X")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        }     
        
//        System.out.println("SUNK");
        return sunk;
    }
     
    public boolean getSunkPlayer(Ship ship){
        int x = ship.getX();
        int y = ship.getY();
        int length = ship.getLength();
        String orientation = ship.getOrientation();
        
        boolean sunk = false;
               
        if (orientation.equals("up")){
            for (int i = 1; i < length; i++){
                if (board[x - i][y].equals("+")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        }    
        
        if (orientation.equals("down")){
            for (int i = 1; i < length; i++){
                if (board[x + i][y].equals("+")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        } 
        
        if (orientation.equals("right")){
            for (int i = 1; i < length; i++){
                if (board[x][y + i].equals("+")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        }        
        
        if (orientation.equals("left")){
            for (int i = 1; i < length; i++){
                if (board[x][y - i].equals("+")){
                    sunk = true;
                }
                else{
                    sunk = false;
                    return false;
                }        
            }
        }     
        
//        System.out.println("SUNK");
        return sunk;
    }
    
    //check to see if the location has a dot in it
    public boolean open(String str){
        if (str.equals("."))
            return true;
        else
            return false;
        //return ".".equals(str);
    }
    
    public void placeHit(int x, int y){
        board[y - 1][x - 1] = "X";
    }
    
    public void placeMiss(int x, int y){
        board[y - 1][x - 1] = "O";
    }
    
    public void placePlus(int x, int y){
        board[y - 1][x - 1] = "+";
        System.out.println("The computer has guessed correctly at " + x + " and " + y);
    }
}