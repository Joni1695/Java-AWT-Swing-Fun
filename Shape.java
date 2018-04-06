import java.util.Random;
public class Shape {
	private double x;
	private double y;
	private double r;
	private double kend;
	public Shape(double x,double y){
		this.x=x;
		this.y=y;
		r=10;
		Random generator=new Random();
		kend=generator.nextDouble()*360;
	}
	public double getX(){
		return x;
	}
	public void setCordinate(int x,int y){
		this.x=x;
		this.y=y;
	}
	public double getY(){
		return y;
	}
	public double getR(){
		return r;
	}
	public void move(){
		x+=(10*Math.cos(kend));
		y+=(10*Math.sin(kend));	
	}
	public boolean checkBoundaries(double width,double height){
		if(x<0||x>width||y>height||y<0) return false;
		else return true;
	}
	public void regenerate(double width,double height){
		Random generator=new Random();
		kend=generator.nextDouble()*360;
		x=generator.nextDouble()*width;
		y=generator.nextDouble()*height;
	}
}
