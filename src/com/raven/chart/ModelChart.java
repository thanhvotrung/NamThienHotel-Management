package com.raven.chart;

public class ModelChart {

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public ModelChart(String label, int[] values) {
        this.label = label;
        this.values = values;
    }

    public ModelChart() {
    }

    private String label;
    private int values[];

    public int getMaxValues() {
        int max = 0;
        for (int v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
