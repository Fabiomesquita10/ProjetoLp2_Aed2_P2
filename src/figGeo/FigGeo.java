package figGeo;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class FigGeo implements FigGeoDimsI, FigGeoDrawI, FigGeoRelsI, Serializable {

  private Color color;
  private final ArrayList<Point> points = new ArrayList<>();

  public FigGeo() { }

  public FigGeo(Color c) {
    this.color = c;
  }

  public void addPoint(Point p) {
    this.points.add(p);
  }

  public Point getPoint(int index) {
    return this.points.get(index);
  }

  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "FigGeo{" +
            "color=" + color +
            ", points=" + points +
            '}';
  }
}