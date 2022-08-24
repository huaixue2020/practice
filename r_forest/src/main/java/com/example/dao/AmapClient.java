package com.example.dao;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Request;
import com.dtflys.forest.annotation.DataParam;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface AmapClient {

    /**
     * 聪明的你一定看出来了@Get注解代表该方法专做GET请求
     * 在url中的{0}代表引用第一个参数，{1}引用第二个参数
     */
    @Get("http://127.0.0.1:4523/m1/1012462-0-default/pet/{0}")
    Map getLocation(Integer petId);

    @Post("http://127.0.0.1:4523/m1/1012462-0-default/pet")
    Map getPost(String name,String status);
}
