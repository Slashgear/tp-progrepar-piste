package com.polytech4a.piste;

import org.springframework.ui.ModelMap;

/**
 * @author Alexandre
 *         08/06/2015
 */
public class Error {
    private static final String DIR_VIEW = "common";
    private static final String ERROR_VIEW = "error";

    public static String newError(final ModelMap pModel, String message) {
        pModel.addAttribute("message", message);
        return String.format("%s/%s", DIR_VIEW, ERROR_VIEW);
    }
}
