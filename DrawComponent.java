import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.util.Random;
import java.util.ArrayList;
public class DrawComponent extends JPanel{
	private int shape,red,blue,green,ballcolor,white,speed,count;
	private ArrayList<Shape> SHAPE;
	private Random generator;
	public DrawComponent(){
		shape=1;
		red=0;
		blue=0;
		green=0;
		ballcolor=0;
		white=0;
		speed=1;
		count=0;
				
		generator=new Random();
		int i=generator.nextInt(100)+100;
		SHAPE=new ArrayList<Shape>();
		for(int j=0;j<i;j++){
			double x=generator.nextDouble()*getWidth();
			double y=generator.nextDouble()*getHeight();
			SHAPE.add(new Shape(x,y));		
		}
	}
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D) g;
		count++;
		g2.clearRect(0, 0, getWidth(), getHeight());
		g2.setColor(CreateColor());
		g2.fillRect(0, 0, getWidth(), getHeight());
		if(speed==1){
			if(ballcolor==0) g2.setColor(RandomColor());
			else if(ballcolor==1){
				if(white==0){
					g2.setColor(Color.white);
					white=1;
				}
				else {
					g2.setColor(Color.BLACK);
					white=0;
				}
			}
			for(int i=0;i<SHAPE.size();i++){
				if(shape==1){
					g2.fill(new Ellipse2D.Double(SHAPE.get(i).getX(),SHAPE.get(i).getY(),SHAPE.get(i).getR(),SHAPE.get(i).getR()));
				}
				else if(shape==2){
					g2.fill(new Rectangle((int)SHAPE.get(i).getX(),(int)SHAPE.get(i).getY(),(int)SHAPE.get(i).getR(),(int)SHAPE.get(i).getR()));
				}
			}
		}
		else if(speed!=1){
			if(count%speed==0){
				generator = new Random();
				int x=generator.nextInt(getWidth());
				int y=generator.nextInt(getHeight());
				for(int i=0;i<SHAPE.size();i++){
					SHAPE.get(i).setCordinate(x, y);
				}
				if(ballcolor==0) g2.setColor(RandomColor());
				else if(ballcolor==1){
					if(white==0){
						g2.setColor(Color.white);
						white=1;
					}
					else {
						g2.setColor(Color.BLACK);
						white=0;
					}
				}
				for(int i=0;i<SHAPE.size();i++){
					if(shape==1){
						g2.fill(new Ellipse2D.Double(SHAPE.get(i).getX(),SHAPE.get(i).getY(),SHAPE.get(i).getR(),SHAPE.get(i).getR()));
					}
					else if(shape==2){
						g2.fill(new Rectangle((int)SHAPE.get(i).getX(),(int)SHAPE.get(i).getY(),(int)SHAPE.get(i).getR(),(int)SHAPE.get(i).getR()));
					}
				}
			}
			else{
				if(ballcolor==0) g2.setColor(RandomColor());
				else if(ballcolor==1){
					if(white==0){
						g2.setColor(Color.white);
						white=1;
					}
					else {
						g2.setColor(Color.BLACK);
						white=0;
					}
				}
				for(int i=0;i<SHAPE.size();i++){
					if(shape==1){
						g2.fill(new Ellipse2D.Double(SHAPE.get(i).getX(),SHAPE.get(i).getY(),SHAPE.get(i).getR(),SHAPE.get(i).getR()));
					}
					else if(shape==2){
						g2.fill(new Rectangle((int)SHAPE.get(i).getX(),(int)SHAPE.get(i).getY(),(int)SHAPE.get(i).getR(),(int)SHAPE.get(i).getR()));
					}
				}
			}
		}
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public void gather(int shape,int red,int blue,int green,int ballcolor){
		this.shape=shape;
		this.red=red;
		this.blue=blue;
		this.green=green;
		this.ballcolor=ballcolor;
		for(int i=0;i<SHAPE.size();i++){
			if(SHAPE.get(i).checkBoundaries(getWidth(),getHeight())==false){
				SHAPE.get(i).regenerate(getWidth(), getHeight());
			}
			SHAPE.get(i).move();
		}
		repaint();
	}
	public Color CreateColor(){
		int r,b,g;
		if(red==1) r=255;else r=0;
		if(blue==1) b=255;else b=0;
		if(green==1) g=255;else g=0;
		return	new Color(r,g,b);
	}
	public Color RandomColor(){
		int r,b,g;
		generator=new Random();
		r=generator.nextInt(255);
		b=generator.nextInt(255);
		g=generator.nextInt(255);
		return new Color(r,g,b);
	}
	public void mouse(int x, int y){
		for(int i=0;i<SHAPE.size();i++){
			SHAPE.get(i).setCordinate(x, y);
		}
		repaint();
	}
}
