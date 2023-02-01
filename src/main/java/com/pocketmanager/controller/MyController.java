package com.pocketmanager.controller;

import com.pocketmanager.entity.CustomUser;
import com.pocketmanager.entity.UserDto;
import com.pocketmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.element.ModuleElement;

@Controller
public class MyController {

    private final UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String homePage(){
        return "index";
    }
    @GetMapping("/sign-up")
    public String registration(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "sign-up";
    }
    @GetMapping("/sign-in")
    public String login(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "sign-in";
    }

    @PostMapping("/sign-up-user")
    public String registrationUser(@Valid @ModelAttribute ("userDto") UserDto userDto,
                                   BindingResult result){
        CustomUser existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "sign-up";
        }
        userService.saveUser(userDto);
        return "redirect:/sign-in";


    }
    @PostMapping("/sign-in-user")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password){
        if (!userService.loginUser(email,password)){
            return "/sign-in";
        }else {
            return "/account";
        }
    }
}
