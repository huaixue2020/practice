package com.example;

import com.example.util.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

  @Test
  public void m1() {}

  public static void main(String[] args) throws Exception {
    String url = "http://192.168.1.10:8080/kaptcha/valid" + "?code=" + "7a22";
    String result = HttpUtils.sendHttp(HttpUtils.HttpMethod.POST,url,null,null);
    System.out.println(result);
  }
}
