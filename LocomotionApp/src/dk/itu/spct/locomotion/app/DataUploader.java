package dk.itu.spct.locomotion.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import dk.itu.spct.locomotion.shared.LocomotionData;

public class DataUploader {

  private final static String URL = "http://locomotion-at-itu.appspot.com/put";

  public static void upload(LocomotionData data) {
    try {
      HttpClient httpClient = new DefaultHttpClient();
      HttpPost httpPost = new HttpPost(URL);
      String json = LocomotionData.toJson(data);
      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
      nameValuePairs.add(new BasicNameValuePair("data", json));
      httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      httpClient.execute(httpPost);
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
