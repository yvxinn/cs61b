package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class Generate {
    private long seed;
    private int width,height;
    Deque<Room> rooms=new LinkedList<>();
    Deque<Region> regions =new LinkedList<>();
    Set<ConnectWall> connectWalls=new HashSet<>();
    public Generate(long s,int w,int h){
        //世界参数
        seed=s;
        width=w;
        height=h;
    }
    public void GenerateRoom(TETile[][] finalWorldFrame, TERenderer ter){
        int num=RandomUtils.uniform(new Random(seed),(width*height)/300,(width*height)/100);
        Random thisRandom=new Random(seed);
        for(int i=0;i<num;){
            int xPos=RandomUtils.uniform(thisRandom,1,width);
            int yPos=RandomUtils.uniform(thisRandom,1,height);
            int w=RandomUtils.uniform(thisRandom,width/15,width/5);
            int h=RandomUtils.uniform(thisRandom,height/15,height/5);
            Room temp=new Room(xPos,yPos,w,h);
            if(temp.islegal(width,height,rooms)) {
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
                Region thisRegion =new Region();
                thisRegion.addPos(thisPos);
                finalWorldFrame[thisPos.x][thisPos.y]=Tileset.FLOOR;
                int count=0;
                initHallwaysHelper(finalWorldFrame,line,new Random(seed),count,thisRegion);
                if(thisRegion.positions.size()>1)
                    regions.addLast(thisRegion);
            }else{
                break;
            }
        }
        for(int i=1;i<width-1;i++){
            for(int j=1;j<height-1;j++){
                Position thisPos=new Position(i,j);
                if(finalWorldFrame[i][j].equals(Tileset.FLOOR)
                        &&thisPos.openDirection(finalWorldFrame,width,height)==0){
                    finalWorldFrame[i][j]=Tileset.WALL;
                }
            }
        }

    }
    private void initHallwaysHelper(TETile[][] finalWorldFrame, Stack<Position> line, Random thisRandom, int count, Region thisRegion){
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
                    if(thisPos.y+2<height-1) {
                        nextPos = new Position(thisPos.x, thisPos.y + 2);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 0&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            finalWorldFrame[nextPos.x][nextPos.y-1] = Tileset.FLOOR;
                            thisRegion.addPos(new Position(nextPos.x,nextPos.y-1));
                            succeed = true;
                        }
                    }
                    up = true;
                    break;
                case 1:
                    if(thisPos.y-2>0) {
                        nextPos = new Position(thisPos.x, thisPos.y - 2);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 0&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            finalWorldFrame[nextPos.x][nextPos.y+1] = Tileset.FLOOR;
                            thisRegion.addPos(new Position(nextPos.x,nextPos.y+1));
                            succeed = true;
                        }
                    }
                    down = true;
                    break;
                case 2:
                    if(thisPos.x-2>0) {
                        nextPos = new Position(thisPos.x - 2, thisPos.y);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 0&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            finalWorldFrame[nextPos.x+1][nextPos.y] = Tileset.FLOOR;
                            thisRegion.addPos(new Position(nextPos.x+1,nextPos.y));
                            succeed = true;
                        }
                    }
                    left = true;
                    break;
                case 3:
                    if(thisPos.x+2<width-1) {
                        nextPos = new Position(thisPos.x + 2, thisPos.y);
                        if (nextPos.openDirection(finalWorldFrame, width, height) == 0&&
                                !finalWorldFrame[nextPos.x][nextPos.y].equals(Tileset.FLOOR)) {
                            finalWorldFrame[nextPos.x][nextPos.y] = Tileset.FLOOR;
                            finalWorldFrame[nextPos.x-1][nextPos.y] = Tileset.FLOOR;
                            thisRegion.addPos(new Position(nextPos.x-1,nextPos.y));
                            succeed = true;
                        }
                    }
                    right = true;
                    break;
                default:
            }
        }
        if(succeed){
            thisRegion.addPos(nextPos);
            line.push(nextPos);
            initHallwaysHelper(finalWorldFrame,line,thisRandom,count+1, thisRegion);
        }else{
            line.pop();
            initHallwaysHelper(finalWorldFrame,line,thisRandom,count+1, thisRegion);
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
    private void RoomToRegion(){
        for(Room room:rooms){
            Region roomRegion=new Region();
            roomRegion.addRoom(room);
            regions.addLast(roomRegion);
        }
    }
    private void findConnectWall(TETile[][] finalWorldFrame){
        for(int i=1;i<width-1;i++){
            for(int j=1;j<height-1;j++){
                if(finalWorldFrame[i][j].equals(Tileset.FLOOR)){
                    continue;
                }
                Position thisPos=new Position(i,j);
                ConnectWall connectWall=thisPos.isConnectWall(finalWorldFrame,width,height,regions);
                if(connectWall!=null){
                    connectWalls.add(connectWall);
                    finalWorldFrame[connectWall.x][connectWall.y]=Tileset.FLOWER;
                }
            }
        }
        if(new Position(1,1).findRegion(regions)==null){
            System.out.print("yes");
        }
    }

    public void connectRegion(TETile[][] finalWorldFrame,TERenderer ter){
        RoomToRegion();
        findConnectWall(finalWorldFrame);
        return;
    }
    //Todo: 设计一个连接点类   包含sideA和sideB（Region）  传入Regions链表  找到AB
}
