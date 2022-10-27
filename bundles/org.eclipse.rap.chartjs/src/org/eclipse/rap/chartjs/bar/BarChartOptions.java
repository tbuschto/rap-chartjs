package org.eclipse.rap.chartjs.bar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.chartjs.AbstarctChartOptions;
import org.eclipse.rap.chartjs.Axis;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;

public class BarChartOptions extends AbstarctChartOptions
{

    float      barPercentage      = 0.9f;
    float      categoryPercentage = 0.8f;
    Integer    barThickness;
    Integer    maxBarThickness;
    
    GridLines gridLines = new GridLines();

    List<Axis> yAxes              = new ArrayList<Axis>();
    List<Axis> xAxes              = new ArrayList<Axis>();

    public BarChartOptions()
    {
        yAxes.add(new Axis());// default
    }

    public List<Axis> getYAxes()
    {
        return yAxes;
    }
    
    public GridLines getGridLines()
    {
        return gridLines;
    }
    
    public List<Axis> getxAxes()
    {
        return xAxes;
    }

    public float getBarPercentage()
    {
        return barPercentage;
    }

    public void setBarPercentage(float barPercentage)
    {
        this.barPercentage = barPercentage;
    }

    public float getCategoryPercentage()
    {
        return categoryPercentage;
    }

    public void setCategoryPercentage(float categoryPercentage)
    {
        this.categoryPercentage = categoryPercentage;
    }

    public Integer getBarThickness()
    {
        return barThickness;
    }

    public void setBarThickness(Integer barThickness)
    {
        this.barThickness = barThickness;
    }

    public Integer getMaxBarThickness()
    {
        return maxBarThickness;
    }

    public void setMaxBarThickness(Integer maxBarThickness)
    {
        this.maxBarThickness = maxBarThickness;
    }

    @Override
    public JsonObject toJson()
    {
        JsonObject jsonObject = super.toJson();
        JsonArray yjsonArray = new JsonArray();
        JsonArray xjsonArray = new JsonArray();
        
        JsonObject axis = new JsonObject();
        
        axis.add("yAxes", yjsonArray);
       
        axis.add("xAxes", xjsonArray);
        
        jsonObject.add("scales", axis);
        for (Axis yaxis : yAxes)
        {
            JsonObject object = new JsonObject();
            object.add("gridLines", gridLines.toJson());
            object.add("display", yaxis.isDisplay());
            yjsonArray.add(object.add("ticks", yaxis.getTicks().toJson()));
        }
        
        for (Axis yaxis : xAxes)
        {
            JsonObject object = new JsonObject();
            object.add("gridLines", gridLines.toJson());
            object.add("display", yaxis.isDisplay());
            xjsonArray.add(object.add("ticks", yaxis.getTicks().toJson()));
        }
        
        if(xAxes.isEmpty())
        {
            JsonObject axisObj = new JsonObject();
            if (barThickness != null)
                axisObj.add("barThickness", barThickness);
            if (maxBarThickness != null)
                axisObj.add("maxBarThickness", maxBarThickness);
            axisObj.add("categoryPercentage", categoryPercentage);
            axisObj.add("barPercentage", barPercentage);
            xjsonArray.add(axisObj);
        }
        if(yAxes.isEmpty())
        {
            JsonObject axisObj = new JsonObject();
            if (barThickness != null)
                axisObj.add("barThickness", barThickness);
            if (maxBarThickness != null)
                axisObj.add("maxBarThickness", maxBarThickness);
            axisObj.add("categoryPercentage", categoryPercentage);
            axisObj.add("barPercentage", barPercentage);
            yjsonArray.add(axisObj);
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
