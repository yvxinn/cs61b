package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Generate {
    private long seed;
    private int width,height;
    Deque<Room> rooms=new LinkedList<>();
    public Generate(long s,int w,int h){
        //世界参数
        seed=s;
        width=w;
        height=h;
    }
    public void GenerateRoom(TETile[][] finalWorldFrame, TERenderer ter){
        int num=RandomUtils.uniform(new Random(seed),15,40);
        Random thisRandom=new Random(seed);
        for(int i=0;i<num;){
            int xPos=RandomUtils.uniform(thisRandom,1,width);
            int yPos=RandomUtils.uniform(thisRandom,1,height);
            int w=RandomUtils.uniform(thisRandom,4,16);
            int h=RandomUtils.uniform(thisRandom,4,16);
            Room temp=new Room(xPos,yPos,w,h);
            if(temp.islegal(width-1,height-1,rooms)) {
                temp.drawRoom(finalWorldFrame, ter);
                rooms.addLast(temp);
                i++;
            }
        }
    }
    public void initHallways(TETile[][] finalWorldFrame, TERenderer ter){
        while(true) {
            Position thisPos = UnusedPos(finalWorldFrame);
            Stack<Position> line=new Stack<>();
            line.push(thisPos);
            if(thisPos!=null){
                finalWorldFrame[thisPos.x][thisPos.y]=Tileset.FLOOR;
                int count=0;
                initHallwaysHelper(finalWorldFrame,line,new Random(seed),count);
            }else{
                break;
            }
        }

    }
    private void initHallwaysHelper(TETile[][] finalWorldFrame,Stack<Position> line,Random thisRandom,int count){
        if(line.isEmpty()){
            return;
        }
        Position thisPos=line.peek();
        boolean up=false;
        boolean down=false;
        boolean left =false;
        boolean right=false;
        boolean succeed=false;
        Position nextPos=null;
        while(!succeed) {
            if(up&&down&&left&&right)
                break;
            switch (RandomUtils.uniform(thisRandom, 4)) {
                case 0:
                    if(thisPos.y+1<height-1) {
                        nextPos = new Position(thisPos.x, thisPos.y + 1);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 1&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            succeed = true;
                        }
                    }
                    up = true;
                    break;
                case 1:
                    if(thisPos.y-1>0) {
                        nextPos = new Position(thisPos.x, thisPos.y - 1);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 1&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            succeed = true;
                        }
                    }
                    down = true;
                    break;
                case 2:
                    if(thisPos.x-1>0) {
                        nextPos = new Position(thisPos.x - 1, thisPos.y);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 1&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            succeed = true;
                        }
                    }
                    left = true;
                    break;
                case 3:
                    if(thisPos.x+1<width-1) {
                        nextPos = new Position(thisPos.x + 1, thisPos.y);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 1&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            succeed = true;
                        }
                    }
                    right = true;
                    break;
                default:
            }
        }
        if(succeed){
            line.push(nextPos);
            initHallwaysHelper(finalWorldFrame,line,thisRandom,count+1);
        }else{
            line.pop();
            initHallwaysHelper(finalWorldFrame,line,thisRandom,count+1);
            //Todo:拿堆栈来记录路径
        }
    }
    private Position UnusedPos(TETile[][] finalWorldFrame){
        for(int i=1;i<width-1;i++){
            for(int j=1;j<height-1;j++){
                if(finalWorldFrame[i][j].equals(Tileset.FLOOR)){
                    continue;
                }
                Position thisPos=new Position(i,j);
                if(thisPos.openDirection(finalWorldFrame,width,height)==0){
                    return thisPos;
                }
            }
        }
        return null;
    }
}
