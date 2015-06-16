package com.polytech4a.piste.controller.components.chart;

import java.util.ArrayList;

/**
 * @author Alexandre
 *         16/06/2015
 */
public abstract class Chart {
    private static Long maxId = 0L;
    public final ArrayList<Data> data;
    protected final Long id;
    protected final Data dataTitle;
    protected final String[] colors = {"'#f44336'", "'#3f51b5'", "'#9c27b0'", "'#4caf50'", "'#ffc624'", "'#ff5722'"};
    private final ChartType chartType;
    private final String title;

    public Chart(String title, ChartType chartType, Data dataTitle) {
        this.data = new ArrayList<>();
        this.id = maxId;
        maxId++;
        this.chartType = chartType;
        this.dataTitle = dataTitle;
        this.title = title;
    }

    public String getScript() {
        String chartName = String.format("%s_%s", chartType.getLabel(), id);
        String dataName = String.format("data_%s", chartName);
        String functionName = String.format("drawChart_%s", chartName);
        StringBuilder script = new StringBuilder("");
        script.append("<script type=\"text/javascript\">\n");
        script.append("google.load(\"visualization\", \"1\", {packages: [\"corechart\"]});\n");
        script.append(String.format("google.setOnLoadCallback(%s);\n", functionName));
        script.append(String.format("function %s() {\n", functionName));
        script.append(String.format("var %s = google.visualization.arrayToDataTable([\n", dataName));
        script.append(String.format("['%s', '%s']", dataTitle.getKey(), dataTitle.getValue()));
        if (data.size() != 0) {
            script.append(",\n");
            script.append(data.get(0));
        }
        for (int i = 1; i < data.size(); i++) {
            script.append(",\n");
            script.append(data.get(i));
        }
        script.append("]);\n");
        script.append("var options = {\n");
        script.append("colors: [");
        if (colors != null) script.append(colors[0]);
        for (int i = 1; i < colors.length; i++) {
            script.append(", ");
            script.append(colors[i]);
        }
        script.append("]\n};\n");
        script.append(
                String.format(
                        "var %s = new google.visualization.%s(document.getElementById('%s'));\n",
                        chartName,
                        chartType.getLabel(), chartName));
        script.append(String.format("%s.draw(%s, options);\n", chartName, dataName));
        script.append("}\n");
        script.append("$(window).resize(function () {\n");
        script.append(String.format("%s();\n", functionName));
        script.append("});\n");
        script.append("</script>\n");

        return script.toString();
    }

    public String getDiv() {
        StringBuilder div = new StringBuilder("");
        div.append(String.format("<h3>%s</h3>\n", title));
        div.append("<div class=\"well\">\n");
        div.append(
                String.format(
                        "<div id=\"%s_%s\" style=\"width: 100%%; height: 100%%;\"></div>\n",
                        chartType.getLabel(), id));
        div.append("</div>\n");

        return div.toString();
    }
}