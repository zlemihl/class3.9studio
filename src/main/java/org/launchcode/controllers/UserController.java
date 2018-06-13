package org.launchcode.controllers;

import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="user")
public class UserController {

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/user/add";
    }
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify){
        if(errors.hasErrors()){
            model.addAttribute("user", user);
            return "/user/add";
        }
        if(verify==null){
            verify="";
        }
        if(!user.getPassword().equals(verify)){
            user.setPassword("");
            model.addAttribute("user", user);
            String verror= "password submissions must match";
            model.addAttribute("error", verror);
            return "/user/add";
        }
            String greeting = "WELCOME "+user.getUsername();
            model.addAttribute("greeting", greeting);
            return "/user/index";
    }
}
