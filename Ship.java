public class Ship {
    private int boardSize = 10;
    private boolean sunk;
    public int length;
    public int hitSpots[];
    public String name;
    public String location[][];
    public String orientation;
    public int x;
    public int y;
    
    public Ship(int l, int locX, int locY, String orient){
        length = l;
        sunk = false;
        orientation = orient;
        x = locX;
        y = locY;
        
        location = new String[boardSize][boardSize];
        
        //print dots at all places of the ship board
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                location[i][j] = ".";
            }            
        } 
        
        //mark the ships entire location by its orientation and length
        switch (orientation){
            case "up":
                for (int i = 0; i < length; i++){
                    location[x - i][y] = "X";
                }
                break;
            case "down":
                for (int i = 0; i < length; i++){
                    location[x + i][y] = "X";
                }
                break;
            case "left":
                for (int i = 0; i < length; i++){
                    location[x][y - i] = "X";
                }
                break;
            case "right":
                for (int i = 0; i < length; i++){
                    location[x][y + i] = "X";
                }
                break;
        }
        
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getLength(){
        return length;
    }
    
    public String getOrientation(){
        return orientation;
    }
    
    public boolean isSunk(){
        return sunk;
    }
    
//    public boolean testInbound(int[][] pos){
//        
//    }
}
