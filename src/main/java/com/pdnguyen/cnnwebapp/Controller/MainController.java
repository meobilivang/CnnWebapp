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
 * Controlling the routes on interface of Webapp
 *
 * */
@Controller
public class MainController {
    /***
     *
     * Route for login
     * @return login
     */
    @GetMapping(path = "/login")
    public String login() {
        return "loginPage";
    }

    /**
     * Route for Error page
     * @param model
     * @param principal
     * @return
     */
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

    /**
     * Routes for welcome page
     * @param model
     * @return
     */
    @GetMapping(path = { "/", "/welcomePage"} )
    public String mainPage(Model model) {
        model.addAttribute("title", "Welcome to my website");
        model.addAttribute("message", "First Page");

        return "index";
    }

    /**
     * Route for user-list page
     * @param model
     * @return
     */
    @GetMapping(path = "/user-list")
    public String userList(Model model) {
        model.addAttribute("title", "User List");

        return "user-list";
    }

    /**
     * Route for add-user page
     * @param model
     * @return
     */
    @GetMapping(path = "/add-user")
    public String addUser(Model model) {
        model.addAttribute("title", "Add New User");
        model.addAttribute("userId", -1);
        return "edit-user";
    }

    /**
     * Route for edit user page
     * @param userId
     * @param model
     * @return
     */
    @GetMapping(path = "/edit-user")
    public String editUser(@RequestParam(value = "id") int userId, Model model) {
        model.addAttribute("title", "Edit User");
        model.addAttribute("userId", userId);
        return "edit-user";
    }

    /**
     * Route for change password page
     * @param model
     * @return
     */
    @GetMapping(path = "/change-password")
    public String changePassword(Model model) {
        model.addAttribute("title", "Change Password");
        model.addAttribute("userId", 1);
        return "change-password";
    }

}
