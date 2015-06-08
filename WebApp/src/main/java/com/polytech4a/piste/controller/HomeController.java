package com.polytech4a.piste.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Controller
@RequestMapping("/")
@Component
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String displayIndex(final ModelMap pModel) {
        return "index";
    }
}
