/*******************************************************************************
 * Copyright (c) 2014 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/

package org.eclipse.rap.chartjs;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.chartjs.internal.ChartPaintListener;
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
  private static final String CHART_MIN_JS = "Chart.js";

  private static final String LINE_CHART = "Line";
  private static final String PIE_CHART = "Pie";
  private static final String BAR_CHART = "Bar";
  private static final String RADAR_CHART = "Radar";
  private static final String POLAR_AREA_CHART = "PolarArea";
  private static final String DOUGHNUT_CHART = "Doughnut";

  public Chart( Composite parent, int style ) {
    super( parent, style );
    registerJS();
    requireJS();
    // NOTE: RAP re-transfers all attached widget data, even if only one of them changes.
    //       That is used by the ChartPaintListener to prevent chart appear animations on resize.
    //       Bi-directional synchronization of the data would also work.
    WidgetUtil.registerDataKeys( CHART_TYPE, CHART_DATA, CHART_OPTIONS );
    addPaintListener();
    setData( CHART_OPTIONS, new JsonObject() );
    applyFixes();
  }

  public void drawLineChart( ChartRowData data ) {
    setData( CHART_TYPE, LINE_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  public void drawBarChart( ChartRowData data ) {
    setData( CHART_TYPE, BAR_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  public void drawRadarChart( ChartRowData data ) {
    setData( CHART_TYPE, RADAR_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  public void drawPolarAreaChart( ChartPointData data ) {
    setData( CHART_TYPE, POLAR_AREA_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  public void drawPieChart( ChartPointData data ) {
    setData( CHART_TYPE, PIE_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  public void drawDoughnutChart( ChartPointData data ) { // , ChartOptions...
    setData( CHART_TYPE, DOUGHNUT_CHART );
    setData( CHART_DATA, data.toJson() );
    redraw();
  }

  // updatePoint/updateRow

  private void addPaintListener() {
    addListener( SWT.Paint, ChartPaintListener.getInstance() );
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
      InputStream inputStream = ChartPaintListener.class.getResourceAsStream( CHART_MIN_JS );
      manager.register( CHART_MIN_JS, inputStream );
      try {
        inputStream.close();
      } catch( IOException e ) {
        throw new RuntimeException( e );
      }
    }
  }

}
