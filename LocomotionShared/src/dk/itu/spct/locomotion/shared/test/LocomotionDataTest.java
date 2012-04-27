package dk.itu.spct.locomotion.shared.test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import dk.itu.spct.locomotion.shared.DataPoint;
import dk.itu.spct.locomotion.shared.LocomotionData;

public class LocomotionDataTest {

  @Test
  public void testToJson() {
    LocomotionData data = new LocomotionData();
    data.setDate(new Date(80000));
    data.setName("some name");
    data.addDataPoint(new DataPoint(new Date(80001), 1d, 2d, 3d));
    String json = LocomotionData.toJson(data);
    
    LocomotionData actual = LocomotionData.fromJson(json);
    assertEquals(data.getName(), actual.getName());
    
  }

}
