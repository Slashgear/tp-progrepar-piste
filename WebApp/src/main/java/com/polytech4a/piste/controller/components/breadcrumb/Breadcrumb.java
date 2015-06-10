package com.polytech4a.piste.controller.components.breadcrumb;

import org.springframework.ui.ModelMap;

import java.util.ArrayList;

/**
 * @author Alexandre
 *         10/06/2015
 */
public class Breadcrumb extends ArrayList<BreadcrumbItem> {
    private static final String fieldName = "breadcrumbList";

    private Breadcrumb() {
    }

    public Breadcrumb(BreadcrumbItem... items) {
        super();
        if (items != null)
            for (int i = 0; i < items.length; i++)
                add(items[i]);
    }

    public static void addToModel(final ModelMap pModel, Breadcrumb breadcrumbList) {
        pModel.addAttribute(fieldName, breadcrumbList);
    }
}
