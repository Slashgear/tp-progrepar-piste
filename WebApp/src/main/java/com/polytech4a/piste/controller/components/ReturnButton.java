package com.polytech4a.piste.controller.components;

import org.springframework.ui.ModelMap;

/**
 * @author Alexandre
 *         10/06/2015
 */
public class ReturnButton {
    private static final String fieldName = "returnPage";

    public static void addToModel(final ModelMap pModel, String url) {
        pModel.addAttribute(fieldName, url);
    }
}
