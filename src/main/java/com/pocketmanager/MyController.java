package com.pocketmanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping(value = "/sign-up")
    public String index(){
        return "sign-up";
    }
}
