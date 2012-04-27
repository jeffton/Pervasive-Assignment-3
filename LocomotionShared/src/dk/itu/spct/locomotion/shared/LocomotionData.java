package dk.itu.spct.locomotion.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

public class LocomotionData {

  private Date _date;
  private String _name;
  private ArrayList<DataPoint> _dataPoints;

  public LocomotionData() {
    _dataPoints = new ArrayList<DataPoint>();
  }

  public Date getDate() {
    return _date;
  }

  public void setDate(Date date) {
    _date = date;
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public ArrayList<DataPoint> getDataPoints() {
    return _dataPoints;
  }

  public void addDataPoint(DataPoint point) {
    _dataPoints.add(point);
  }

  public static String toJson(LocomotionData data) {
    Gson gson = new Gson();
    return gson.toJson(data);
  }

  public static LocomotionData fromJson(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, LocomotionData.class);
  }

}
