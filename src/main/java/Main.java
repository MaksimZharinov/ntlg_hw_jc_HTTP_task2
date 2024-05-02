import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static final String NASA_URI = "https://api.nasa.gov" +
            "/planetary/apod?api_key=Hld72kK9zSU6tlJI4yJeGULJXVMiI3hHEFkfRvDe";

    private static final String PATH = "C:\\Users\\minim\\STUDY\\NETOLOGY\\" +
            "JD-62\\Java Core\\HTTP\\ntlg_hw_jc_HTTP_task2\\NASA_Media";

    private static ObjectMapper mapper = new ObjectMapper();

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

        NASAResponse nasaResponse = mapper.readValue(response.getEntity().getContent(),
                new TypeReference<>() {});

        String[] separatedUrl = nasaResponse.getUrl().split("/");
        String fileName = separatedUrl[separatedUrl.length - 1];
        File file = new File(PATH, fileName);

        request = new HttpGet(nasaResponse.getUrl());
        response = httpClient.execute(request);

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(response.getEntity().getContent().readAllBytes());

        httpClient.close();
        response.close();
    }
}
