package hw2;

public class PercolationStats {
    double results[];
    int T;

    public PercolationStats(int N, int T, PercolationFactory pf){
        this.T=T;
        results=new double[T];
        for(int i=0;i<T;i++) {
            Percolation percolation = pf.make(N);
            while(!percolation.percolates()){
                int row=  edu.princeton.cs.introcs.StdRandom.uniform(N);
                int col= edu.princeton.cs.introcs.StdRandom.uniform(N);
                percolation.open(row,col);
            }
            results[i]= (double) percolation.numberOfOpenSites() /(N*N);
        }
    }
    public double mean(){
        return edu.princeton.cs.introcs.StdStats.mean(results);
    }
    public double stddev(){
        return  edu.princeton.cs.introcs.StdStats.stddev(results);
    }
    public double confidenceLow(){
        return mean()-1.96*stddev()/Math.sqrt(T);
    }
    public double confidenceHigh(){
        return mean()+1.96*stddev()/Math.sqrt(T);
    }
}
