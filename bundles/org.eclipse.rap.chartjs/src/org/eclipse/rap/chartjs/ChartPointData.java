package org.eclipse.rap.chartjs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;


public class ChartPointData {

  private final List<Integer> points = new ArrayList<Integer>( 5 );
  private final List<ChartStyle> colors = new ArrayList<ChartStyle>( 5 );

  public void addPoint( int value, ChartStyle chartStyle ) {
    points.add( value );
    colors.add( chartStyle );
  }

  JsonArray toJson() {
    JsonArray result = new JsonArray();
    for( int i = 0; i < points.size(); i++ ) {
      result.add( new JsonObject()
        .add( "value", points.get( i ) )
        .add( "color", ChartStyle.asCss( colors.get( i ).getFillColor() ) )
      );
    }
    return result;
  }

}
