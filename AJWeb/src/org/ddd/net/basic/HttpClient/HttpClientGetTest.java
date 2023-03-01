package org.ddd.net.basic.HttpClient;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class HttpClientGetTest {
 //����ȫ��HttpClient:
static HttpClient httpClient = HttpClient.newBuilder().build();

    public static void main(String[] args) throws Exception {
        String url = "https://www.baidu.com/";
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                // ����Header:
                .header("User-Agent", "Java HttpClient").header("Accept", "*/*")
                // ���ó�ʱ:
                .timeout(Duration.ofSeconds(5))
                // ���ð汾:Z
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // HTTP�����ظ���Header�����һ��Header�ɶ�Ӧ���Value:
        Map<String, List<String>> headers = response.headers().map();
        for (String header : headers.keySet()) {
            System.out.println(header + ": " + headers.get(header).get(0));
        }
        System.out.println(response.body().substring(0, 1024) + "...");
    }
}
