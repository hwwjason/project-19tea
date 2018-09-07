package com.sckj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/index")
    public String index()  {
        return "shoucha/index.html";
//        return "index.jsp";
    }

}