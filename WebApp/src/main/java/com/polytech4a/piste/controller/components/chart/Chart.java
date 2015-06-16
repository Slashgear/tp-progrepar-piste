package com.polytech4a.piste.controller.components.chart;

import java.util.ArrayList;

/**
 * @author Alexandre
 *         16/06/2015
 */
public class Chart {
    private static Long maxId = 0L;
    public final ArrayList<Data> data;
    protected final Long id;
    protected final Data dataTitle;
    protected final String[] colors = {"'#f44336'", "'#3f51b5'", "'#9c27b0'", "'#4caf50'", "'#ffc624'", "'#ff5722'"};
    private final ChartType chartType;
    private final String title;

    public Chart(ChartType chartType, String title, Data dataTitle) {
        this.data = new ArrayList<>();
        this.id = maxId;
        maxId++;
        this.chartType = chartType;
        this.dataTitle = dataTitle;
        this.title = title;
    }

    public Chart(ChartType chartType, String title) {
        this(chartType, title, new Data("", ""));
        //['Move', 'Nombre d\'apprenants']
    }

    public String getScript() {
        String chartName = String.format("%s_%s", chartType.getLabel(), id);
        String dataName = String.format("data_%s", chartName);
        String functionName = String.format("drawChart_%s", chartName);
        StringBuilder script = new StringBuilder("");
        script.append("<script type=\"text/javascript\">\n").
                append("google.load(\"visualization\", \"1\", {packages: [\"corechart\"]});\n").
                append(String.format("google.setOnLoadCallback(%s);\n", functionName)).
                append(String.format("function %s() {\n", functionName)).
                append(String.format("var %s = google.visualization.arrayToDataTable([\n", dataName)).
                append(String.format("['%s', '%s']", dataTitle.getKey(), dataTitle.getValue()));
        if (data.size() != 0) {
            script.append(",\n").
                    append(data.get(0));
        }
        for (int i = 1; i < data.size(); i++) {
            script.append(",\n").
                    append(data.get(i));
        }
        script.append("]);\n").
                append("var options = {\n").
                append("colors: [");
        if (colors != null) script.append(colors[0]);
        for (int i = 1; i < colors.length; i++) {
            script.append(", ").
                    append(colors[i]);
        }
        script.append("]\n};\n").
                append(
                        String.format(
                                "var %s = new google.visualization.%s(document.getElementById('%s'));\n",
                                chartName,
                                chartType.getLabel(), chartName)).
                append(String.format("%s.draw(%s, options);\n", chartName, dataName)).
                append("}\n").
                append("$(window).resize(function () {\n").
                append(String.format("%s();\n", functionName)).
                append("});\n").
                append("</script>\n");

        return script.toString();
    }

    public String getDiv() {
        StringBuilder div = new StringBuilder("");
        div.append(String.format("<h3>%s</h3>\n", title)).
                append("<div class=\"well\">\n").
                append(
                        String.format(
                                "<div id=\"%s_%s\" style=\"width: 100%%; height: 100%%;\"></div>\n",
                                chartType.getLabel(), id)).
                append("</div>\n");
        return div.toString();
    }
}
