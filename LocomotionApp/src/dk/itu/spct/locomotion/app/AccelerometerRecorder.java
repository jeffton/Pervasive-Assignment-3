package dk.itu.spct.locomotion.app;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import dk.itu.spct.locomotion.shared.DataPoint;
import dk.itu.spct.locomotion.shared.LocomotionData;

public class AccelerometerRecorder {
  
  public interface Listener {
    public void recordingStarting();

    public void recordingDone(LocomotionData data);
  }

  private SensorManager _sensorManager = null;
  private Sensor _accelerometer = null;
  private Listener _listener;

  public AccelerometerRecorder(SensorManager sensorManager, Listener listener) {
    _listener = listener;
    _sensorManager = sensorManager;
    _accelerometer = _sensorManager
        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
  }

  public void startRecording(final int preDelay, final int length) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        runRecording(preDelay, length);
      }
    }).start();
  }

  private void runRecording(int preDelaySeconds, int recordSeconds) {
    sleepSeconds(preDelaySeconds);
    _listener.recordingStarting();
    RecordingSensorListener recorder = new RecordingSensorListener();
    _sensorManager.registerListener(recorder, _accelerometer, 50000); // microsecond interval
    sleepSeconds(recordSeconds);
    _sensorManager.unregisterListener(recorder);
    _listener.recordingDone(recorder.getLocomotionData());
  }

  private void sleepSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
    }
  }

  private class RecordingSensorListener implements SensorEventListener {

    private LocomotionData _locomotionData;
    private long _firstTimestamp = -1;
    
    public RecordingSensorListener() {
      _locomotionData = new LocomotionData();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
      DataPoint point = new DataPoint(getRelativeTimestamp(event.timestamp), event.values[0],
          event.values[1], event.values[2]);
      _locomotionData.addDataPoint(point);
    }
    
    private long getRelativeTimestamp(long timestamp) {
      if (_firstTimestamp == -1)
        _firstTimestamp = timestamp;
      
      return timestamp - _firstTimestamp;
    }

    public LocomotionData getLocomotionData() {
      return _locomotionData;
    }

  }

}
