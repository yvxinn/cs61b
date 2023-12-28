package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static int[] xx;
    private static int[] yy;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    public static void drawHex(TETile[][] world,int x,int y,int length){
        for(int i=0;i<length;i++){
            drawLowerOneRow(world,x,y,length,i);
        }
        for(int i=0;i<length;i++){
            drawUpperOneRow(world,x,y,length,i);
        }
    }
    private static void drawLowerOneRow(TETile[][] world,int x,int y,int length,int floor){
        for(int i=0;i<length+2*(length-1);i++){
            if(i<length-1-floor||i>length*2-2+floor){
                world[i+x][y+floor]=Tileset.NOTHING;
            }else{
                world[i+x][y+floor]=Tileset.FLOWER;
            }
        }
    }
    private static void drawUpperOneRow(TETile[][] world,int x,int y,int length,int floor){
        for(int i=0;i<length+2*(length-1);i++){
            if(i<floor||i>3*length-3-floor){
                world[i+x][y+floor+length]=Tileset.NOTHING;
            }else{
                world[i+x][y+floor+length]=Tileset.FLOWER;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        drawHex(world,1,5,4);
        // draws the world to the screen
        ter.renderFrame(world);
    }
}
