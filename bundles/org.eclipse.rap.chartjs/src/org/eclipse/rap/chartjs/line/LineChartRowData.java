package org.eclipse.rap.chartjs.line;

import static org.eclipse.rap.chartjs.ChartStyle.asCss;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.chartjs.ChartStyle;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;

public class LineChartRowData
{

    private final String[]      labels;
    private final List<RowInfo> rowlabels = new ArrayList<RowInfo>(1);
    private final List<float[]> rows      = new ArrayList<float[]>(1);

    public LineChartRowData(String[] labels)
    {
        this.labels = labels;
    }

    public LineChartRowData addRow(RowInfo rowInfo, float[] row)
    {
        rowlabels.add(rowInfo);
        rows.add(row);
        return this;
    }

    JsonObject toJson()
    {
        JsonObject result = new JsonObject();
        result.add("labels", asJson(labels));
        JsonArray rowsJson = new JsonArray();
        for (int i = 0; i < rows.size(); i++)
        {
            RowInfo rowInfo = rowlabels.get(i);
            rowsJson.add(new JsonObject().
                    add("label", (rowInfo.label)).add("pointStyle", (rowInfo.pointStyle)).
                    add("showLine", (rowInfo.showLine)).
                    add("fill", rowInfo.fill).
                    add("borderWidth", rowInfo.lineWidth).
                    add("lineTension", rowInfo.lineTension).
                    add("steppedLine", rowInfo.steppedLine).
                    add("backgroundColor", asCss(rowInfo.chartStyle.getFillColor(), rowInfo.chartStyle.getFillOpacity()))
                    .add("borderColor", asCss(rowInfo.chartStyle.getStrokeColor())).
                    add("pointBackgroundColor", asCss(rowInfo.chartStyle.getPointColor())).
                    add("pointBorderColor", asCss(rowInfo.chartStyle.getPointColor())).
                    add("pointBorderWidth", rowInfo.lineWidth).add("data", asJson(rows.get(i))));
        }
        result.add("datasets", rowsJson);
        return result;
    }

    private JsonArray asJson(String... strings)
    {
        JsonArray result = new JsonArray();
        for (int i = 0; i < strings.length; i++)
        {
            result.add(strings[i]);
        }
        return result;
    }

    private JsonArray asJson(float... ints)
    {
        JsonArray result = new JsonArray();
        for (int i = 0; i < ints.length; i++)
        {
            result.add(ints[i]);
        }
        return result;
    }

    public static class RowInfo
    {

        String     label;
        String     pointStyle = "circle";
        boolean    fill;
        boolean    showLine   = true;
        ChartStyle chartStyle;
        int        lineWidth  = 1;
        double     lineTension  = 0.4;
        String     steppedLine  = "false";

        public String getLabel()
        {
            return label;
        }

        public void setLabel(String label)
        {
            this.label = label;
        }
        
        public String getSteppedLine()
        {
            return steppedLine;
        }
        public void setSteppedLine(String steppedLine)
        {
            if("before".equals(steppedLine)||"after".equals(steppedLine) )
            {
                this.steppedLine = steppedLine;
            }
            else
                this.steppedLine = null;
            
        }

        public boolean isFill()
        {
            return fill;
        }

        public void setFill(boolean fill)
        {
            this.fill = fill;
        }

        public ChartStyle getChartStyle()
        {
            return chartStyle;
        }

        public void setChartStyle(ChartStyle chartStyle)
        {
            this.chartStyle = chartStyle;
        }

        public void setLineWidth(int lineWidth)
        {
            this.lineWidth = lineWidth;
        }

        public int getLineWidth()
        {
            return lineWidth;
        }

        public void setPointStyle(String pointStyle)
        {
            this.pointStyle = pointStyle;
        }

        public String getPointStyle()
        {
            return pointStyle;
        }
        
        public void setShowLine(boolean showLine)
        {
            this.showLine = showLine;
        }
        public boolean isShowLine()
        {
            return showLine;
        }
        
        public double getLineTension()
        {
            return lineTension;
        }
        public void setLineTension(double lineTension)
        {
            this.lineTension = lineTension;
        }

    }

}
