package com.sadiki.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by X-MART on 6/28/2017.
 */
@RefreshScope
@RestController
public class BourseRestService {
   // @Value("${me}")
    private String message="me";
    @RequestMapping("/messages")
    public String tellMe(){
        System.out.println("---------------------------");
        System.out.println("C'est moi qui repond");
        System.out.println("---------------------------");
        return message;
    }
}
