package com.bolsaideas.springboot.web.app.controllers;

import com.bolsaideas.springboot.web.app.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model){
        User user = new User();
        user.setName("rubi");
        user.setLast_name("Ramirez");
        model.addAttribute("titulo", "K ondi".concat(user.getName()));
        model.addAttribute("user", user);
        return "profile";
    }

}
