package com.polytech4a.piste.controller.components.chart;

/**
 * @author Alexandre
 *         16/06/2015
 */
public class Data {
    private final String key;
    private final String value;

    public Data(Object key, Object value) {
        this.key = key.toString();
        this.value = value.toString();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("['%s', %s]", key, value);
    }
}
