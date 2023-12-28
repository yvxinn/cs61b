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
}
