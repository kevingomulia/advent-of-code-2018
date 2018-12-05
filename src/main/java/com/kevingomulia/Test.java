package main.java.com.kevingomulia;

import java.net.URI;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Test {
    private final static String CLIENT_ID = "687066559051-328tgdc80k8s3jha566ohod8sthg00na.apps.googleusercontent.com";
    private final static String REDIRECT_URI = "http://localhost:8400";
    private final static String CLIENT_SECRET = "Xmo2zd8iEEYCETNPMl-QQTW9";
    private final static String GRANT_TYPE = "authorization_code";
    private final static String GOOGLE_OAUTH_URL = "https://www.googleapis.com/oauth2/v4/token";
    private static String authorizationCode = "4/qQCAHbhRbaVC4qnGtGfPHvZs1w21TFqEydPi4p0LoSTH5R5l9UpUTCRJ8BVYBH8BxzXfvuuZ21qL0YuIDJxiT_s";

    public static void main(String[] args) {
        try {
            String body = "{\"code\":\"" + authorizationCode + "\"," +
                    "\"redirect_uri\":\"" + encode(REDIRECT_URI) + "\"," +
                    "\"client_id\":\"" + encode(CLIENT_ID) + "\"," +
                    "\"client_secret\":\"" + CLIENT_SECRET + "\"," +
                    "\"scope\":\"\"," +
                    "\"grant_type\":\"" + GRANT_TYPE + "\",";
            System.out.println(body);
            StringEntity entity = new StringEntity(body, ContentType.APPLICATION_FORM_URLENCODED);

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(GOOGLE_OAUTH_URL);
            request.setEntity(entity);

            HttpResponse response = client.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity());

            System.out.println(response.getEntity());
            System.out.println(jsonString);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String encode(String stringToEncode) throws Exception{
        return URLEncoder.encode(stringToEncode, "utf-8");
    }
}
