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

  private static final String LINE_CHART = "Line";
  private static final Object PIE_CHART = "Pie";

  public Chart( Composite parent, int style ) {
    super( parent, style );
    registerJS();
    requireJS();
    WidgetUtil.registerDataKeys( CHART_TYPE, CHART_DATA, CHART_OPTIONS );
    addPaintListener();
    setData( CHART_OPTIONS, new JsonObject() );
    applyFixes();
  }

  public void drawLineChart( DataRows data ) {
    setData( CHART_TYPE, LINE_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  public void drawPieChart( DataPoint data ) {
    setData( CHART_TYPE, PIE_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  private void addPaintListener() {
    ClientListener listener = new ClientListener(
        "function handleEvent( event ) {\n"
      + "  var type = event.widget.getData( \"" +  CHART_TYPE + "\" );\n"
      + "  var data = event.widget.getData( \"" +  CHART_DATA + "\" );\n"
      + "  var options = event.widget.getData( \"" +  CHART_OPTIONS + "\" );\n"
      // IE HACKS:
      + "  if( !event.gc.canvas ) {\n"
      + "    event.gc.canvas = {\n"
      + "      width : 200,\n"
      + "      height : 200\n"
      + "    };\n"
      + "    event.gc.measureText = function( txt ) {"
      + "      var fontStyle = {};\n"
      + "      rwt.html.Font.fromString( this.font ).renderStyle( fontStyle );\n"
      + "      return rwt.widgets.util.FontSizeCalculation.computeTextDimensions( txt, fontStyle );\n"
      + "    };\n"
      + "    event.gc.fillText = function(){};\n"
      + "  }\n"
      // IE HACKS END
      + "  new Chart( event.gc )[ type ]( data, options );\n"
      + "  options.animation = false;"
      + "}\n"
    );
    addListener( SWT.Paint, listener );
  }

  private void applyFixes() {
    // See RAP Bug 391414
    ClientListener listener = new ClientListener(
        "function handleEvent( event ) {\n"
      + "  event.widget.redraw();"
      + "}\n"
    );
    addListener( SWT.Show, listener );
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
