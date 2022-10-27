package org.eclipse.rap.chartjs.line;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.chartjs.AbstarctChartOptions;
import org.eclipse.rap.chartjs.Axis;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;

public class LineChartOptions extends AbstarctChartOptions
{
    List<Axis> yAxes = new ArrayList<Axis>();

    GridLines gridLines = new GridLines();
    
    public GridLines getGridLines()
    {
        return gridLines;
    }
 
    
    public LineChartOptions()
    {
        yAxes.add(new Axis());//default
    }
    public List<Axis> getYAxes()
    {
        return yAxes;
    }

    @Override
    public JsonObject toJson()
    {
        JsonObject jsonObject = super.toJson();
        JsonArray jsonArray = new JsonArray();
        jsonObject.add("scales", new JsonObject().add("yAxes", jsonArray));
        
        
        for (Axis yaxis : yAxes)
        {
            JsonObject object = new JsonObject();
            object.add("gridLines", gridLines.toJson());
            jsonArray.add(object.add("ticks", yaxis.getTicks().toJson()));
        }

        return jsonObject;
    }
    
    public static class GridLines{
        boolean  display = true;
        boolean  drawBorder = false;
        
        public void setDisplay(boolean display)
        {
            this.display = display;
        }
        public boolean isDisplay()
        {
            return display;
        }
        
        public void setDrawBorder(boolean drawBorder)
        {
            this.drawBorder = drawBorder;
        }
        
        public boolean isDrawBorder()
        {
            return drawBorder;
        }
        
        public JsonObject toJson()
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("display", display);
            jsonObject.add("drawBorder", drawBorder);
            
            return jsonObject;
        }
        
    }
}
