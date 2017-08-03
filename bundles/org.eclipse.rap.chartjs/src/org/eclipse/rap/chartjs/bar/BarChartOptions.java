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

    List<Axis> yAxes              = new ArrayList<Axis>();

    public BarChartOptions()
    {
        yAxes.add(new Axis());// default
    }

    public List<Axis> getYAxes()
    {
        return yAxes;
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
        JsonArray jsonArray = new JsonArray();
        if (barThickness != null)
            jsonObject.add("barThickness", barThickness);
        if (maxBarThickness != null)
            jsonObject.add("maxBarThickness", maxBarThickness);
        jsonObject.add("categoryPercentage", categoryPercentage);
        jsonObject.add("barPercentage", barPercentage);
        jsonObject.add("scales", new JsonObject().add("yAxes", jsonArray));
        for (Axis yaxis : yAxes)
        {
            jsonArray.add(new JsonObject().add("ticks", yaxis.getTicks().toJson()));
        }

        return jsonObject;
    }
}
