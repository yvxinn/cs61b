package byog.Core;

import java.util.HashSet;
import java.util.Set;

public class Region {
    Set<Position> positions=new HashSet<>();
    public void addPos(Position position){
        positions.add(position);
    }
    public void addRoom(Room room){
        for(int i= room.getX();i<= room.getX2();i++){
            for(int j=room.getY();j<= room.getY2();j++){
                addPos(new Position(i,j));
            }
        }
    }
    public boolean inRegion(Position position){
        return positions.contains(position);
    }
    public static Region combine(Region A,Region B){
        Region ret=new Region();
        ret.positions.addAll(A.positions);
        ret.positions.addAll(B.positions);
        return ret;
    }
}
