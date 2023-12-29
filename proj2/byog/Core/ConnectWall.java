package byog.Core;

public class ConnectWall extends Position{
    ConnectWall(int px, int py) {
        super(px, py);
    }
    public Region A;
    public Region B;
    ConnectWall(Position p,Region a,Region b){
        super(p.x,p.y);
        A=a;
        B=b;
    }

    public boolean SameOf(Object o){
        if(o==null){
            return false;
        }
        if(o.getClass()!=this.getClass()){
            return false;
        }
        ConnectWall other=(ConnectWall) o;
        return (A==other.A&&B==other.B)||(A==other.B&&B==other.A);
    }
}
