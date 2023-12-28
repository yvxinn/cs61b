package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Deque;
import java.util.Random;

public class Room {
    private int x;
    private int y;
    private int width;
    private int height;
    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public int getX2(){
        return x+width;
    }
    public int getY2(){
        return y+height;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public Room(int px,int py,int pwidth,int pheight){
        x=px;
        y=py;
        width=pwidth;
        height=pheight;
    }
    public void drawRoom(TETile[][] finalWorldFrame,TERenderer ter){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                finalWorldFrame[x + j][y + i] = Tileset.FLOOR;
            }
        }
    }
    public boolean islegal(int worldWidth, int worldHeight, Deque<Room> rooms){
        if((x+width>=worldWidth||y+height>=worldHeight)){
            return false;
        }
        for(Room room:rooms){
            if(isCovered(room)){
                return false;
            }
        }
        return true;
    }
    public boolean isCovered(Room otherRoom){
        return (this.getX() < otherRoom.getX2() && this.getX2() > otherRoom.getX())
                && (this.getY() < otherRoom.getY2() && this.getY2() > otherRoom.getY());
    }

}
