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
        if(x+1<maxX-1&&finalWorldFrame[x+1][y].equals(Tileset.FLOOR)){
           temp++;
        }
        if(y+1<maxY-1&&finalWorldFrame[x][y+1].equals(Tileset.FLOOR)){
            temp++;
        }
        if(x-1>0&&finalWorldFrame[x-1][y].equals(Tileset.FLOOR)){
            temp++;
        }
        if(y-1>0&&finalWorldFrame[x][y-1].equals(Tileset.FLOOR)){
            temp++;
        }
        return temp;
    }
    @Override
    public boolean equals(Object o){
        System.out.print("in");
        if(o==null){
            return false;
        }
        if(o.getClass()!=this.getClass()){
            return false;
        }
        Position other=(Position) o;
        return (x == other.x) && (y == other.y);
    }
    public ConnectWall isConnectWall(TETile[][] finalWorldFrame,int maxX,int maxY,Deque<Region> regions) {
        Position p0=null;
        Position p1=null;
        boolean flag=false;
        if(openDirection(finalWorldFrame,maxX,maxY)!=2) {
            return null;
        }
        if(finalWorldFrame[x-1][y].equals(Tileset.FLOOR)&&finalWorldFrame[x+1][y].equals(Tileset.FLOOR)){
            p0=new Position(x-1,y);
            p1=new Position(x+1,y);
            flag=true;
        }
        if(finalWorldFrame[x][y-1].equals(Tileset.FLOOR)&&finalWorldFrame[x][y+1].equals(Tileset.FLOOR)){
            p0=new Position(x,y-1);
            p1=new Position(x,y+1);
            flag=true;
        }
        if(flag&&(p0.findRegion(regions)!=(p1.findRegion(regions)))){
            return new ConnectWall(this, p0.findRegion(regions), p1.findRegion(regions) );
        }
        return null;
    }
    public Region findRegion(Deque<Region> regions){
        for(Region region:regions){
            for(Position p:region.positions){
                if(p.x==x&&p.y==y)
                    return region;
            }
        }
        return null;
    }
}
