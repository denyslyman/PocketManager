package com.pocketmanager.controller;

import com.pocketmanager.dto.UserDto;
import com.pocketmanager.entity.User;
import com.pocketmanager.service.UserService;
import com.pocketmanager.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyController {

    private UserServiceImpl userService;

    public MyController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/sign-in")
    public String loginForm() {
        return "sign-in";
    }

    @GetMapping("/account")
    public String accountForm(){
        return "account";
    }


    // handler method to handle user registration request
    @GetMapping("sign-up")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "sign-up";
    }

    // handler method to handle register user form submit request
    @PostMapping("/sign-up")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null,
                    "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "sign-up";
        }
        userService.saveUser(user);
        System.out.println(user);
        return "redirect:/sign-up?success";
    }


}
