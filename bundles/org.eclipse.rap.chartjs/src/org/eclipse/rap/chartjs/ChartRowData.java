package org.eclipse.rap.chartjs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;


public class ChartRowData {

  private final String[] labels;
  private final List<int[]> rows = new ArrayList<int[]>( 1 );
  private final List<ChartStyle> colors = new ArrayList<ChartStyle>( 1 );

  public ChartRowData( String[] labels ) {
    this.labels = labels;
  }

  public void addRow( int[] row, ChartStyle colors ) {
    rows.add( row );
    this.colors.add( colors );
  }

  JsonObject toJson() {
    JsonObject result = new JsonObject();
    result.add( "labels" , asJson( labels ) );
    JsonArray rowsJson = new JsonArray();
    for( int i = 0; i < rows.size(); i++ ) {
      ChartStyle rowColors = colors.get( i );
      rowsJson.add( new JsonObject()
        .add( "fillColor", ChartStyle.asCss( rowColors.getFillColor() ) )
        .add( "strokeColor", ChartStyle.asCss( rowColors.getStrokeColor() ) )
        .add( "pointColor", ChartStyle.asCss( rowColors.getPointColor() ) )
        .add( "pointStrokeColor", ChartStyle.asCss( rowColors.getPointStrokeColor() ) )
        .add( "data", asJson( rows.get( i ) ) )
      );
    }
    result.add( "datasets", rowsJson );
    return result;
  }


  private JsonArray asJson( String... strings ) {
    JsonArray result = new JsonArray();
    for( int i = 0; i < strings.length; i++ ) {
      result.add( strings[ i ] );
    }
    return result;
  }

  private JsonArray asJson( int... ints ) {
    JsonArray result = new JsonArray();
    for( int i = 0; i < ints.length; i++ ) {
      result.add( ints[ i ] );
    }
    return result;
  }

}
