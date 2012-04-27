package dk.itu.spct.locomotion.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

public class LocomotionData {

  private Date date;
  private String name;
  private ArrayList<DataPoint> dataPoints;

  public LocomotionData() {
    dataPoints = new ArrayList<DataPoint>();
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
	  this.name = name;
  }

  public ArrayList<DataPoint> getDataPoints() {
    return dataPoints;
  }

  public void addDataPoint(DataPoint point) {
    dataPoints.add(point);
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
