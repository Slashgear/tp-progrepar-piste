package com.polytech4a.piste.controller.components.chart;

/**
 * @author Alexandre
 *         16/06/2015
 */
public enum ChartType {
    PIECHART("PieChart");

    private final String label;

    ChartType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
