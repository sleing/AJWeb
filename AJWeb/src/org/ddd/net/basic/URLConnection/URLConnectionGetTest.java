package org.ddd.net.basic.URLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLConnectionGetTest {
    public static void main(String[] args) {
        try{
            String urlName = "https://www.baidu.com/";
            URL url1 = new URL(urlName);
            URLConnection connection = url1.openConnection();
            //��������ϵͨ��
            connection.connect();
            //��ӡHTTP��ͷ����Ϣ
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String,List<String>> entry : headers.entrySet()){
                String key = entry.getKey();
                for(String value : entry.getValue())
                    System.out.println(key +": " + value);
            }
            //�����Ҫ��ȡ������������Ϣ
            System.out.println("---------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: " + connection.getDate());
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModified: " + connection.getLastModified());
            System.out.println("---------");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //����յ���HTML�ַ���
            String line = "";
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
