package dk.itu.spct.locomotion.app;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import dk.itu.spct.locomotion.shared.LocomotionData;

public class DataUploader {

  private final static String URL = "http://anti.zno.dk/whatever";

  public static void upload(LocomotionData data) {
    try {
      HttpClient httpClient = new DefaultHttpClient();
      HttpPost httpPost = new HttpPost(URL);
      MultipartEntity entity = new MultipartEntity();
      entity.addPart("data", new StringBody(LocomotionData.toJson(data)));
      httpPost.setEntity(entity);
      httpClient.execute(httpPost);
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
