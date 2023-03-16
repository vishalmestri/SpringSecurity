package io.avec.securityldap.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

//    @GetMapping("/hello")
//    public String hello(Model model, Principal principal) {
//        model.addAttribute("user", principal.getName());
//        return "hello";
//    }

    @GetMapping("/hello")
    public String hello(Model model, Authentication authentication,HttpServletRequest req) {
        model.addAttribute("user", authentication.getName());
        model.addAttribute("httpServletRequest",req);
        model.addAttribute("authentication",authentication);
        model.addAttribute("authority",authentication.getAuthorities());
        
        return "hello";
    }
    
    @ModelAttribute("remoteUser")
    public Object remoteUser(final HttpServletRequest request) {
        return request.getRemoteUser();
    }
    
    @ModelAttribute("getAuthorities")
    public Object getAuthorities(final Authentication authentication) {
        return authentication.getAuthorities();
    }
    
}
