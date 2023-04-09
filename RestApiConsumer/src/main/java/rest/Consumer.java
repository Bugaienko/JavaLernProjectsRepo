package rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergii Bugaienko
 */

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
//        headers.add("user-agent", "Application");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "test Name");
        jsonToSend.put("job", "test Job");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend, headers);




        String postUrl = "https://reqres.in/api/users";
        String response2 = restTemplate.postForObject(postUrl, request,  String.class);

        String url = "https://reqres.in/api/users/2";
//        String response = restTemplate.getForObject(url, String.class, entity);
        String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        System.out.println(response);
        System.out.println(response2);
    }
}
