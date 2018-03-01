package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class Client {

  private String url;
  
  public Client(String url) {
    this.url = url;
  }
  
  public String getJson() {

    StringBuilder response = new StringBuilder();    
    if(! isHttpOk()) {
      return "[]";
    }

    try {
      BufferedReader reader =
        new BufferedReader(new InputStreamReader
                           (new URL(url).openStream()));
      for(String line : reader.lines().collect(Collectors.toList())) {
        response.append(line);
      }
    } catch (IOException e) {
      System.err.println("Error reading Json: " +
                         e.getMessage());
    }
    return response.toString();
  }

  private boolean isHttpOk() {
    try {
      HttpURLConnection connection =
        (HttpURLConnection) new URL(url).openConnection();
      connection.setRequestMethod("GET");
      //connection.connect();
      int code = connection.getResponseCode();
      //connection.disconnect();
      return code == 200;
    } catch (IOException e) {
      System.err.println("Error testing http status: " + e.getMessage());
    }
    return false;
  }
}
