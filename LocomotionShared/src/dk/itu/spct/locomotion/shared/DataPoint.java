package dk.itu.spct.locomotion.shared;

public class DataPoint {

  private long _timestamp;
  private float _x;
  private float _y;
  private float _z;

  public DataPoint(long timestamp, float x, float y, float z) {
    _timestamp = timestamp;
    _x = x;
    _y = y;
    _z = z;
  }

  public float getZ() {
    return _z;
  }

  public void setZ(float z) {
    _z = z;
  }

  public float getY() {
    return _y;
  }

  public void setY(float y) {
    _y = y;
  }

  public float getX() {
    return _x;
  }

  public void setX(float x) {
    _x = x;
  }

  public long getTimestamp() {
    return _timestamp;
  }

  public void setTimestamp(long timestamp) {
    _timestamp = timestamp;
  }

}
