package dk.itu.spct.locomotion.shared;

import java.util.Date;

public class DataPoint {

  private Date _timestamp;
  private double _x;
  private double _y;
  private double _z;

  public DataPoint(Date timestamp, double x, double y, double z) {
    _timestamp = timestamp;
    _x = x;
    _y = y;
    _z = z;
  }

  public double getZ() {
    return _z;
  }

  public void setZ(double z) {
    _z = z;
  }

  public double getY() {
    return _y;
  }

  public void setY(double y) {
    _y = y;
  }

  public double getX() {
    return _x;
  }

  public void setX(double x) {
    _x = x;
  }

  public Date getTimestamp() {
    return _timestamp;
  }

  public void setTimestamp(Date timestamp) {
    _timestamp = timestamp;
  }

}
