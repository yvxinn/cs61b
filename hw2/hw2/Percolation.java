package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {
    private int [][]sites;      //0:close 1:open 2:full
    private int OpenSites;
    private int up;
    private int down;
    private WeightedQuickUnionUF w;
    private WeightedQuickUnionUF w2;
    public Percolation(int N){
        w=new WeightedQuickUnionUF(N*N+3);
        w2=new WeightedQuickUnionUF(N*N+3);
        up=N*N+1;
        down=N*N+2;
        if(N<=0){
            throw new IllegalArgumentException();
        }
        sites=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sites[i][j]=0;
            }
        }
        OpenSites=0;
    }
    private int IDof(int row,int col){
        return row*sites.length+col;
    }
    private void ConnectSites(int row,int col){
        if(row+1<sites.length&&sites[row+1][col]>=1){
            w.union(IDof(row, col),IDof(row+1, col));
            w2.union(IDof(row, col),IDof(row+1, col));
        }
        if(col+1<sites.length&&sites[row][col+1]>=1){
            w.union(IDof(row, col),IDof(row, col+1));
            w2.union(IDof(row, col),IDof(row, col+1));
        }
        if(row-1>=0&&sites[row-1][col]>=1){
            w.union(IDof(row, col),IDof(row-1, col));
            w2.union(IDof(row, col),IDof(row-1, col));
        }
        if(col-1>=0&&sites[row][col-1]>=1){
            w.union(IDof(row, col),IDof(row, col-1));
            w2.union(IDof(row, col),IDof(row, col-1));
        }
    }
    public void open(int row, int col){
        if(row==0){
            w.union(IDof(row,col), up);
            w2.union(IDof(row,col), up);
        }
        if (row == sites.length - 1) {
            w.union(IDof(row,col),down);
        }
        if(row>sites.length-1||col>sites.length||row<0||col<0){
            throw new IndexOutOfBoundsException();
        }
        if(!isOpen(row, col)) {
            sites[row][col] = 1;
            OpenSites++;
        }
        ConnectSites(row,col);
    }
    public boolean isOpen(int row, int col){
        if(row>sites.length-1||col>sites.length||row<0||col<0){
            throw new IndexOutOfBoundsException();
        }
        return sites[row][col]>=1;
    }
    public boolean isFull(int row, int col){
        if(!isOpen(row,col)){
            return false;
        }
        if(row>sites.length-1||col>sites.length||row<0||col<0){
            throw new IndexOutOfBoundsException();
        }
        return w2.connected(IDof(row, col),up);
    }
    public int numberOfOpenSites(){
        return OpenSites;
    }
    public boolean percolates(){
        return w.connected(up,down);
    }

    public static void main(String[] args){
        Percolation p=new Percolation(5);
        p.open(1,1);
        assertTrue(p.isOpen(1,1));
        assertFalse(p.isOpen(0,0));
        assertFalse(p.isFull(0,0));
        assertFalse(p.isFull(1,1));
        p.open(0,1);
        assertTrue(p.isFull(1,1));
        p.open(2,1);
        p.open(3,4);
        assertFalse(p.isFull(3,4));
        p.open(3,1);
        assertFalse(p.percolates());
        p.open(4,1);
        assertTrue(p.percolates());
        assertEquals(6,p.numberOfOpenSites());
    }
}
