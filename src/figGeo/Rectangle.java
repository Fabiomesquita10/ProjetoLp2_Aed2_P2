package figGeo;


import java.awt.*;

public class Rectangle extends FigGeo implements Comparable<Rectangle> {

  public Rectangle() {
    super();
  }

  public Rectangle(Color c) {
    super(c);
  }

  public Rectangle(Point p1, Point p2, Color c){
    super(c);
    this.addPoint(p1);
    this.addPoint(p2);
  }

  @Override
  public String toString() {
    return "Rectangle@{" +  super.toString() + "}";
  }

  public int compareTo(Rectangle r) {
    return (int)(this.area() - r.area());
  }

  public void draw(Graphics g) {
    Point p1 = this.getPoint(0);
    Point p2 = this.getPoint(1);
    int x1 = (int)p1.getX();
    int y1 = (int)p1.getY();
    int width = (int)Math.abs(p1.distanceX(p2));
    int heigth = (int)Math.abs(p1.distanceY(p2));
    g.setColor(this.getColor());
    g.fillRect(x1, y1, width, heigth);
  }

  public double area() {
    Point p1 = this.getPoint(0);
    Point p2 = this.getPoint(1);
    double distanceX = p1.distanceX(p2);
    double distanceY = p1.distanceY(p2);
    return Math.abs(distanceY * distanceX);
  }

  public double perimeter() {
    Point p1 = this.getPoint(0);
    Point p2 = this.getPoint(1);
    double distanceX = p1.distanceX(p2);
    double distanceY = p2.distanceY(p2);
    return Math.abs((distanceY*2)+(distanceX*2));
  }

  public boolean isInside(FigGeo f) {
    if(f instanceof Rectangle){
      Point p1 = this.getPoint(0);
      Point p2 = this.getPoint(1);
      Point p3 = f.getPoint(0);
      Point p4 = f.getPoint(1);
      return p3.xBetweenPoints(p1, p2) && p3.yBetweenPoints(p1, p2) && p4.xBetweenPoints(p1, p2) && p4.yBetweenPoints(p1, p2);
    }
    return false;
  }

  public boolean isInside(Point p) {
    if(p instanceof Point){
      Point p1 = this.getPoint(0);
      Point p2 = this.getPoint(1);
      return p.xBetweenPoints(p1, p2) && p.yBetweenPoints(p2, p1);
    }
    return false;
  }

  public boolean isInterceptedBy(FigGeo f) {
    if(f instanceof Rectangle){
      Point p1 = this.getPoint(0);
      Point p2 = this.getPoint(1);
      Point p3 = f.getPoint(0);
      Point p4 = f.getPoint(1);
      Point p5 = new Point(p3.getX(), p4.getY());
      Point p6 = new Point(p4.getX(), p3.getY());
      return (p3.xBetweenPoints(p1, p2) && p3.yBetweenPoints(p1, p2)) || (p4.xBetweenPoints(p1, p2) && p4.yBetweenPoints(p1, p2)) || (p5.xBetweenPoints(p1,p2) && p5.yBetweenPoints(p1,p2)) || (p6.xBetweenPoints(p1,p2) && p6.yBetweenPoints(p1,p2));
    }
    return false;
  }

}