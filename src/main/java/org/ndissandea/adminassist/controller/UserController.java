package org.ndissandea.adminassist.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.ndissandea.adminassist.model.User;
import org.ndissandea.adminassist.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserController {

    private final UserServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserServiceImpl userService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestParam String password, @RequestParam String username, Model model) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("User {} successfully authenticated", username);
            return "redirect:/home"; // Redirect to a secure page after successful login
        } catch (Exception e) {
            log.error("Login failed for user {}", username);
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Redirect back to login page with error
        }
    }

    @GetMapping("/")
    public String redirectToLoginPage() {
        return "redirect:/login";
    }

    /*Register new user display the form*/

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }
    /*handle the user registration process*/
    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.warn("Sign-up attempt failed due to validation errors");
            return "sign-up";
        }
        /*Create user in the database*/
        userDetailsService.create(user);
        log.info("User {} created successfully", user.getUserName());
        model.addAttribute("username", user.getUserName());
        return "confirmation";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        log.info("Login page displayed");
        return "login";
    }

    @RequestMapping("/home")
    public String getHome() {
        log.info("Home page displayed");
        return "home";
    }
}