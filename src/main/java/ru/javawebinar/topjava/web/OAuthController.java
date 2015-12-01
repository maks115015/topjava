package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin on 01.12.2015.
 */
@Controller
public class OAuthController {
    @RequestMapping(value = "/login/OAuthVK", method = RequestMethod.GET)
    public String root() {


        return "";
    }
}
