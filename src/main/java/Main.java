import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Main {

    private static final String NASA_URI = "https://api.nasa.gov" +
            "/planetary/apod?api_key=Hld72kK9zSU6tlJI4yJeGULJXVMiI3hHEFkfRvDe";

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(NASA_URI);
        CloseableHttpResponse response = httpClient.execute(request);
    }
}
