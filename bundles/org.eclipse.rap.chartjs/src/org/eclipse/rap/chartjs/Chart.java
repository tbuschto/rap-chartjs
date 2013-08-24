package org.eclipse.rap.chartjs;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.lifecycle.WidgetUtil;
import org.eclipse.rap.rwt.scripting.ClientListener;
import org.eclipse.rap.rwt.service.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;


public class Chart extends Canvas {

  private static final String CHART_OPTIONS = "chartOptions";
  private static final String CHART_DATA = "chartData";
  private static final String CHART_TYPE = "chartType";
  private static final String CHART_MIN_JS = "Chart.min.js";

  public Chart( Composite parent, int style ) {
    super( parent, style );
    registerJS();
    requireJS();
    WidgetUtil.registerDataKeys( CHART_TYPE, CHART_DATA, CHART_OPTIONS );
    addPaintListener();
  }

  public void setChartType( String type ) {
    setData( CHART_TYPE, type );
    redraw();
  }

  public void setChartData( JsonObject data ) {
    setData( CHART_DATA, data );
    redraw();
  }

  public void setChartOptions( JsonObject data ) {
    setData( CHART_OPTIONS, data );
    redraw();
  }

  private void addPaintListener() {
    ClientListener listener = new ClientListener(
        "function handleEvent( event ) {\n"
      + "  var gc = event.gc;\n"
      + "  var type = event.widget.getData( \"" +  CHART_TYPE + "\" );\n"
      + "  var data = event.widget.getData( \"" +  CHART_DATA + "\" );\n"
      + "  var options = event.widget.getData( \"" +  CHART_OPTIONS + "\" );\n"
      + "  new Chart( gc )[ type ]( data, options );"
      + "}\n"
    );
    addListener( SWT.Paint, listener );
  }

  private void requireJS() {
    JavaScriptLoader service = RWT.getClient().getService( JavaScriptLoader.class );
    service.require( RWT.getResourceManager().getLocation( CHART_MIN_JS ) );
  }

  private void registerJS() {
    ResourceManager manager = RWT.getResourceManager();
    if( !manager.isRegistered( CHART_MIN_JS ) ) {
      InputStream inputStream = getClass().getResourceAsStream( CHART_MIN_JS );
      manager.register( CHART_MIN_JS, inputStream );
      try {
        inputStream.close();
      } catch( IOException e ) {
        throw new RuntimeException( e );
      }
    }
  }

}
