package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Deque;

public class Position {
    public int x,y;
    Position(int px,int py){
        x=px;
        y=py;
    }

    int openDirection(TETile[][] finalWorldFrame,int maxX,int maxY){
        int temp=0;
        if(x+1<maxX-1&&!finalWorldFrame[x+1][y].equals(Tileset.WALL)){
           temp++;
        }
        if(y+1<maxY-1&&!finalWorldFrame[x][y+1].equals(Tileset.WALL)){
            temp++;
        }
        if(x-1>0&&!finalWorldFrame[x-1][y].equals(Tileset.WALL)){
            temp++;
        }
        if(y-1>0&&!finalWorldFrame[x][y-1].equals(Tileset.WALL)){
            temp++;
        }
        return temp;
    }
}
