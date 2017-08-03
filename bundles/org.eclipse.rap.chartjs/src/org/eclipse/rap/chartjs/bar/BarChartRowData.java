package org.eclipse.rap.chartjs.bar;

import static org.eclipse.rap.chartjs.ChartStyle.asCss;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.chartjs.ChartStyle;
import org.eclipse.rap.chartjs.line.LineChartRowData.RowInfo;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;

public class BarChartRowData
{

    private final String[]      labels;
    private final List<RowInfo> rowlabels = new ArrayList<RowInfo>(1);
    private final List<float[]> rows      = new ArrayList<float[]>(1);

    public BarChartRowData(String[] labels)
    {
        this.labels = labels;
    }

    public BarChartRowData addRow(RowInfo rowInfo, float[] row)
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
        JsonArray rowsAction = new JsonArray();
        for (int i = 0; i < rows.size(); i++)
        {
            RowInfo rowInfo = rowlabels.get(i);
            rowsAction.add(rowInfo.action);
            rowsJson.add(new JsonObject().add("label", (rowInfo.label)).
                  
                    add("hidden", (rowInfo.hidden))
                    .add("backgroundColor", asCss(rowInfo.chartStyle.getFillColor(), rowInfo.chartStyle.getFillOpacity())).
                    add("borderColor", asCss(rowInfo.chartStyle.getStrokeColor()))
                    .add("pointBorderColor", asCss(rowInfo.chartStyle.getPointColor()))
                    .add("data", asJson(rows.get(i))));
        }
        result.add("datasets", rowsJson);

        result.add("actions", rowsAction);
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

        public String action ="select";

        String     label;
       
        ChartStyle chartStyle;

        private boolean hidden;

        public String getLabel()
        {
            return label;
        }

        public boolean isHidden()
        {
            return hidden;
        }

        public void setHidden(boolean hidden)
        {
            this.hidden = hidden;
        }

        public void setLabel(String label)
        {
            this.label = label;
        }

        
        public void setAction(String action)
        {
            this.action = action;
        }
        
        public String getAction()
        {
            return action;
        }
      

      

       
        public ChartStyle getChartStyle()
        {
            return chartStyle;
        }

        public void setChartStyle(ChartStyle chartStyle)
        {
            this.chartStyle = chartStyle;
        }

      

      

    }

}
