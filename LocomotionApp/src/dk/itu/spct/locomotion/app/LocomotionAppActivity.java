package dk.itu.spct.locomotion.app;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;
import dk.itu.spct.locomotion.shared.LocomotionData;

public class LocomotionAppActivity extends Activity implements
    AccelerometerRecorder.Listener {

  private Vibrator _vibrator;
  private AccelerometerRecorder _recorder;
  private final static int VIBRATION_TIME = 300;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    _vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    _recorder = new AccelerometerRecorder(sensorManager, this);
    setContentView(R.layout.main);
  }

  public void onRecordButtonClicked(View view) {
    int pause = 5;
    showToast("Will record in " + pause + " second(s).");
    _recorder.startRecording(pause, 10);
  }

  @Override
  public void recordingStarting() {
    _vibrator.vibrate(VIBRATION_TIME);
    sleep(VIBRATION_TIME);
    showToast("Now recording");
  }

  private void showToast(final String text) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(LocomotionAppActivity.this, text, Toast.LENGTH_SHORT)
            .show();
      }
    });
  }

  private void sleep(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
    }
  }

  @Override
  public void recordingDone(final LocomotionData data) {
    _vibrator.vibrate(VIBRATION_TIME);
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        saveRecording(data);
      }
    });
  }

  protected void saveRecording(final LocomotionData data) {
    UITools.promptForString(this, "Save recording", "Name:",
        new UITools.PromptResultHandler() {
          @Override
          public void closed(boolean accepted, String value) {
            if (accepted && value.length() > 0)
              uploadRecording(value, data);
          }
        });
  }

  protected void uploadRecording(String value, final LocomotionData data) {
    showToast("Upload started");
    new Thread(new Runnable() {
      @Override
      public void run() {
        DataUploader.upload(data);
        showToast("Upload done!");
      }
    }).start();
  }

}
