package com.luv2code.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showForm(){
        return "home";
    }
    // add a requset mapping for /leaders
@GetMapping("/leaders")
public String showleaders(){
    return "leaders";
}  
// controller for admin
// @GetMapping("/systems")
//     public String showsystems(){
//         return "systems";
//     }
@GetMapping("/systems")
public String showSystems(){
    return "systems";
}
@GetMapping("/empl")
public String showempl(){
    return "empl";
}
@GetMapping("/char")
public String showchar(){
    return "char";
}

}  
