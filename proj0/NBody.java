public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String filename){
		In in =new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] parray=new Planet[num];
		for (int i = 0; i < num; i++) {
			double xp = in.readDouble();
			double yp = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			parray[i] = new Planet(xp, yp, vx, vy, m, img);	
		}
		return parray;
	}

	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double radius=readRadius(filename);
		Planet[] planets=readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(Planet p:planets){
			p.draw();
		}
		StdDraw.enableDoubleBuffering();
		for(double t=0;t<=T;t+=dt){
			double[] xForces=new double[planets.length];
			double[] yForces=new double[planets.length];
			for(int i=0;i<planets.length;i++){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(int i=0;i<planets.length;i++){
				planets[i].update(dt,xForces[i],yForces[i]);
				StdDraw.picture(planets[i].xxPos,planets[i].yyPos,
					"images/"+planets[i].imgFileName);
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
   			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}