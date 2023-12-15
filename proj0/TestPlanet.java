public class TestPlanet{
	public static void main(String[] args){
		System.out.println("hello");
		Planet earth=new Planet(0,0,0,0,100,"earth");
		Planet mars=new Planet(1,1,0,0,200,"mars");
		System.out.println(mars.calcForceExertedBy(earth));
	}
}