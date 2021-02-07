package covid19.stats;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHTTPTest {

    public static void main() {
        String urlToCall = "https://covid2019-api.herokuapp.com/timeseries/confirmed";
        
        OkHttpClient client=new OkHttpClient();
        
        Request request = new Request.Builder().url(urlToCall).build();
        
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseString=response.body().string();
                System.out.println(responseString);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }           

    }
}
