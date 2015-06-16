package com.polytech4a.piste.controller.components.chart;

/**
 * @author Alexandre
 *         16/06/2015
 */
public class PieChart extends Chart {
    public PieChart(String title) {
        super(title, ChartType.PIECHART, new Data("Task", "Hours per Day"));
    }
}
