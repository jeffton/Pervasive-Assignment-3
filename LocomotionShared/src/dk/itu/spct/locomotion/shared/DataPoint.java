package dk.itu.spct.locomotion.shared;

public class DataPoint {

  private long timestamp;
  private float x;
  private float y;
  private float z;

  public DataPoint(long timestamp, float x, float y, float z) {
    this.timestamp = timestamp;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public float getZ() {
    return this.z;
  }

  public void setZ(float z) {
    this.z = z;
  }

  public float getY() {
    return this.y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getX() {
    return this.x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

}
