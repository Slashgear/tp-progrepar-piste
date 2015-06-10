package com.polytech4a.piste.controller.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;

/**
 * @author Alexandre
 *         08/06/2015
 */
public class ErrorPage {
    private static final Logger logger = LogManager.getLogger(ErrorPage.class.getSimpleName());
    private static final String DIR_VIEW = "common";
    private static final String ERROR_VIEW = "error";

    public static String newError(final ModelMap pModel, String message) {
        return newError(pModel, message, null);
    }

    public static String newError(final ModelMap pModel, String errorMessage, String errorReturnPage) {
        pModel.addAttribute("errorMessage", errorMessage);
        pModel.addAttribute("errorReturnPage", errorReturnPage);
        logger.error(String.format("An error occured : %s", errorMessage));
        return String.format("%s/%s", DIR_VIEW, ERROR_VIEW);
    }
}
