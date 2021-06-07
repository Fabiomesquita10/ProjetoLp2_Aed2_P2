package figGeo;

import java.io.Serializable;

/**
 *
 * @author rjm
 */
public class Point implements Comparable<Point>, Serializable {

    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distanceX(Point p){
        return Math.abs(this.getX() - p.getX());
    }

    public double distanceY(Point p){
        return Math.abs(this.getY() - p.getY());
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow(distanceX(p), 2.0) + Math.pow(distanceY(p), 2.0));
    }

    @Override
    public String toString(){
        return "Point@{"+x+", "+y+"}";
    }

//    public int compareTo(Object o) {
//        Point origem = new Point (0.0, 0.0);
//        double d0 = this.distancia(origem);
//        double d1 = ((Point)o).distancia(origem);
//        return (int)(d0-d1);
//    }

    public int compareTo(Point p) {
        Point origem = new Point (0.0, 0.0);
        double d0 = this.distance(origem);
        double d1 = p.distance(origem);
        return (int)(d0-d1);
    }

    public boolean xBetweenPoints(Point leftPt, Point rightPt){
        return (getX()>leftPt.getX() && getX()<rightPt.getX());
    }

    public boolean yBetweenPoints(Point upperPt, Point lowerPt){
        return (getY()<upperPt.getY() && getY()>lowerPt.getY());
    }
}
