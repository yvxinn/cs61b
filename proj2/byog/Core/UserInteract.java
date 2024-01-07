package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

public class UserInteract {
    TETile[][] finalWorldFrame;
    TERenderer ter;
    Position player;
    public UserInteract(TETile[][] finalWorldFrame, TERenderer ter,Position player) {
        this.finalWorldFrame=finalWorldFrame;
        this.ter=ter;
        this.player=player;
    }

    public char interact(){
        char thisType;
        while(true){
            if(!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            thisType=StdDraw.nextKeyTyped();
            if(thisType!='q'){
                move(thisType);
            }else{
                break;
            }
        }

        return thisType;
    }
    private void move(char dir){
        switch (dir){
            case 'a':
                if(finalWorldFrame[player.x-1][player.y]== Tileset.FLOOR){
                    finalWorldFrame[player.x][player.y]=Tileset.FLOOR;
                    finalWorldFrame[player.x-1][player.y]=Tileset.PLAYER;
                    player.x--;
                }
                break;
            case 'w':
                if(finalWorldFrame[player.x][player.y+1]== Tileset.FLOOR){
                    finalWorldFrame[player.x][player.y]=Tileset.FLOOR;
                    finalWorldFrame[player.x][player.y+1]=Tileset.PLAYER;
                    player.y++;
                }
                break;
            case 'd':
                if(finalWorldFrame[player.x+1][player.y]== Tileset.FLOOR){
                    finalWorldFrame[player.x][player.y]=Tileset.FLOOR;
                    finalWorldFrame[player.x+1][player.y]=Tileset.PLAYER;
                    player.x++;
                }
                break;
            case 's':
                if(finalWorldFrame[player.x][player.y-1]== Tileset.FLOOR){
                    finalWorldFrame[player.x][player.y]=Tileset.FLOOR;
                    finalWorldFrame[player.x][player.y-1]=Tileset.PLAYER;
                    player.y--;
                }
                break;
            default:
        }
        ter.renderFrame(finalWorldFrame);
    }
}
