package com.polytech4a.piste.controller.components.breadcrumb;

/**
 * @author Alexandre
 *         10/06/2015
 */
public class BreadcrumbItem {
    private final boolean isActive;
    private final String label;
    private final String url;

    private BreadcrumbItem(boolean isActive, String label, String url) {
        this.isActive = isActive;
        this.label = label;
        this.url = url;
    }

    public BreadcrumbItem(String label) {
        this(true, label, null);
    }

    public BreadcrumbItem(String label, String url) {
        this(false, label, url);
    }

    public boolean isActive() {
        return isActive;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }
}
