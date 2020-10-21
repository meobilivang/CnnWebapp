package com.pdnguyen.cnnwebapp.Controller;

import com.pdnguyen.cnnwebapp.Utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/***
 *
 * Main Controller for CnnWebApp
 * Navigate to main pages
 *
 * */

@Controller
public class MainController {

    @GetMapping(path = "/login")
    public String login() {
        return "loginPage";
    }

    @GetMapping(path = "/403")
    public String denyAccess(Model model, Principal principal) {
        if (principal != null) {
            User user = (User)  ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(user);

            model.addAttribute("userInfo", userInfo);
            String message = "Stop there ! " + user.getUsername().toUpperCase()  + ", you are not allowed to be here !";
            model.addAttribute("message", message);
        }

        return "403Page";
    }

    @GetMapping(path = { "/", "/welcomePage"} )
    public String mainPage(Model model) {
        model.addAttribute("title", "Welcome to my website");
        model.addAttribute("message", "First Page");

        return "index";
    }

    @GetMapping(path = "/user-list")
    public String userList(Model model) {
        model.addAttribute("title", "User List");

        return "user-list";
    }

    @GetMapping(path = "/add-user")
    public String addUser(Model model) {
        model.addAttribute("title", "Add New User");
        model.addAttribute("userId", -1);
        return "edit-user";
    }

    @GetMapping(path = "/edit-user")
    public String editUser(@RequestParam(value = "id") int userId, Model model) {
        model.addAttribute("title", "Edit User");
        model.addAttribute("userId", userId);
        return "edit-user";
    }

    @GetMapping(path = "/change-password")
    public String changePassword(Model model) {
        model.addAttribute("title", "Change Password");
        model.addAttribute("userId", 1);
        return "change-password";
    }

}
