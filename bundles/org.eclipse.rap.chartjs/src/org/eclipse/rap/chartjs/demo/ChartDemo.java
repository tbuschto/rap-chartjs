package org.eclipse.rap.chartjs.demo;

import org.eclipse.rap.chartjs.Chart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


public class ChartDemo extends AbstractEntryPoint {

  @Override
  protected void createContents( Composite parent ) {
    Chart chart = new Chart( parent, SWT.NONE );
    chart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    chart.setChartType( "Line" );
    JsonObject data = new JsonObject()
      .add( "labels", asJson( "January","February","March","April","May","June","July" ) )
      .add( "datasets", asJson(
        new JsonObject()
          .add( "fillColor", "rgba(220,220,220,0.5)" )
          .add( "strokeColor", "rgba(220,220,220,1)" )
          .add( "pointColor", "rgba(220,220,220,1)" )
          .add( "pointStrokeColor", "#fff" )
          .add( "data", asJson( 65, 59, 90, 81, 56, 55, 40 ) ),
        new JsonObject()
          .add( "fillColor", "rgba(151,187,205,0.5)" )
          .add( "strokeColor", "rgba(151,187,205,1)" )
          .add( "pointColor", "rgba(151,187,205,1)" )
          .add( "pointStrokeColor", "#fff" )
          .add( "data", asJson( 28, 48, 40, 19, 96, 27, 100 ) )
      )
    );
    chart.setChartData( data );
    chart.setChartOptions( new JsonObject() );
  }

  private JsonArray asJson( String... strings ) {
    JsonArray result = new JsonArray();
    for( int i = 0; i < strings.length; i++ ) {
      result.add( strings[ i ] );
    }
    return result;
  }

  private JsonArray asJson( JsonObject... jsonObject ) {
    JsonArray result = new JsonArray();
    for( int i = 0; i < jsonObject.length; i++ ) {
      result.add( jsonObject[ i ] );
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
