package org.eclipse.rap.chartjs.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.rap.chartjs.AbstarctChartOptions;
import org.eclipse.rap.chartjs.Axis;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;

public class LineChartOptions extends AbstarctChartOptions
{
    List<Axis> yAxes = new ArrayList<Axis>();

 
    
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
            jsonArray.add(new JsonObject().add("ticks", yaxis.getTicks().toJson()));
        }

        return jsonObject;
    }
}
