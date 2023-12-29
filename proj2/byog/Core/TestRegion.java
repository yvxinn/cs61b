package byog.Core;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Deque;
import java.util.LinkedList;

public class TestRegion {
    @Test
    public void TestFindRegion(){
        Position p0=new Position(0,0);
        Position p1=new Position(1,1);
        Position p2=new Position(1,2);
        Position p3=new Position(1,3);
        Position p4=new Position(1,2);
        Deque<Region> regions =new LinkedList<>();
        Region r1=new Region();
        Region r2=new Region();
        Region r3=new Region();

        regions.add(r1);
        regions.add(r2);
        r1.positions.add(p1);
        r2.positions.add(p2);
        r2.positions.add(p3);
        r3.positions.add(p0);
        assertTrue(p2.equals(p4));
        assertFalse(p1.findRegion(regions)==p2.findRegion(regions));
        assertTrue(p2.findRegion(regions)==p3.findRegion(regions));
        assertFalse(p1.findRegion(regions)==p4.findRegion(regions));
        assertTrue(p4.findRegion(regions)==p3.findRegion(regions));


    }

}
