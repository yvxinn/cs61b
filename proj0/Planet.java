public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
              xxPos=xP;
              yyPos=yP;
              xxVel=xV;
              yyVel=yV;
              mass=m;
              imgFileName=img;
    }
    public Planet(Planet p){
    	this.xxPos=p.xxPos;
    	this.yyPos=p.yyPos;
    	this.xxVel=p.xxVel;
    	this.yyVel=p.yyVel;
    	this.mass=p.mass;
    	this.imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet p){
    	return Math.sqrt((p.xxPos-this.xxPos)*(p.xxPos-this.xxPos)+
    		(p.yyPos-this.yyPos)*(p.yyPos-this.yyPos));
    }
    public double calcForceExertedBy(Planet p){
    	double r=calcDistance(p);
    	return 6.67e-11*this.mass*p.mass
    		/(r*r);
    }
    public double calcForceExertedByX(Planet p){
   		return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)
   			/this.calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
   		return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)
   			/this.calcDistance(p);
   	}
   	public double calcNetForceExertedByX(Planet[] parray){
   		double fx=0;
   		for(Planet p:parray){
   			if(!this.equals(p)){
   				fx+=this.calcForceExertedByX(p);
   			}
   		}
   		return fx;
   	}
   	public double calcNetForceExertedByY(Planet[] parray){
   		double fy=0;
   		for(Planet p:parray){
   			if(!this.equals(p)){
   				fy+=this.calcForceExertedByY(p);
   			}
   		}
   		return fy;
   	}
   	public void update(double dt,double fx,double fy){
   		double ax =fx/ this.mass;
   		double ay =fy/ this.mass;
   		this.xxVel+=ax*dt;
   		this.yyVel+=ay*dt;
   		this.xxPos+=this.xxVel*dt;
   		this.yyPos+=this.yyVel*dt;
   	}
   	public void draw(){
   		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
   	}
}