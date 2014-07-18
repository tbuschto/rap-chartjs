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

package org.eclipse.rap.chartjs.demo;

import org.eclipse.rap.chartjs.Chart;
import org.eclipse.rap.chartjs.ChartPointData;
import org.eclipse.rap.chartjs.ChartRowData;
import org.eclipse.rap.chartjs.ChartStyle;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class ChartDemo extends AbstractEntryPoint {

  private static final String HAS_ALT_DATA = "hasAltData";
  private static final String IS_ROW_DATA_CHART = "isRowDataChart";
  private ChartRowData rowData1;
  private ChartRowData rowData2;
  private ChartStyle chartStyle1;
  private ChartStyle chartStyle2;
  private ChartStyle chartStyle3;
  private ChartStyle chartStyle4;
  private ChartStyle chartStyle5;
  private ChartPointData pointData1;
  private ChartPointData pointData2;

  @Override
  protected void createContents( Composite parent ) {
    parent.setLayout( new GridLayout( 3, true ) );
    createChartStyles();
    createRowData();
    createPointData();
    createChart( parent, true ).drawLineChart( rowData1 );
    createChart( parent, true ).drawBarChart( rowData1 );
    createChart( parent, true ).drawRadarChart( rowData1 );
    createChart( parent, false ).drawPolarAreaChart( pointData1 );
    createChart( parent, false ).drawPieChart( pointData1 );
    createChart( parent, false ).drawDoughnutChart( pointData1 );
  }

  private void createPointData() {
    pointData1 = new ChartPointData();
    pointData1.addPoint( 28, chartStyle3 );
    pointData1.addPoint( 88, chartStyle4 );
    pointData1.addPoint( 51, chartStyle5 );
    pointData2 = new ChartPointData();
    pointData2.addPoint( 48, chartStyle3 );
    pointData2.addPoint( 50, chartStyle4 );
    pointData2.addPoint( 39, chartStyle5 );
  }

  private void createRowData() {
    rowData1 = new ChartRowData( new String[] { "January","February","March","April","May","June","July" } );
    rowData1.addRow( new int[] { 28, 48, 40, 19, 96, 27, 100 }, chartStyle1 );
    rowData1.addRow( new int[] { 27, 100, 28, 48, 40, 19, 96 }, chartStyle2 );
    rowData2 = new ChartRowData( new String[] { "January","February","March","April","May","June","July" } );
    rowData2.addRow( new int[] { 18, 30, 43, 19, 65, 20, 10 }, chartStyle1 );
    rowData2.addRow( new int[] { 20, 90, 18, 38, 30, 59, 80 }, chartStyle2 );
  }

  private void createChartStyles() {
    chartStyle1 = new ChartStyle( 220, 220, 220, 0.8f );
    chartStyle2 = new ChartStyle( 151, 187, 205, 0.7f );
    chartStyle3 = new ChartStyle( 250, 187, 105, 0.9f );
    chartStyle4 = new ChartStyle( 50, 187, 205, 0.9f );
    chartStyle5 = new ChartStyle( 10, 17, 133, 0.9f );
  }

  private Chart createChart( Composite parent, boolean isRowDataChart ) {
    Chart chart = new Chart( parent, SWT.NONE );
    chart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    chart.setData( IS_ROW_DATA_CHART, new Boolean( isRowDataChart ) );
    chart.setData( HAS_ALT_DATA, Boolean.FALSE );
    chart.addListener( SWT.MouseDown, new ChangeDataListener() );
    return chart;
  }

  private final class ChangeDataListener implements Listener {

    @Override
    public void handleEvent( Event event ) {
      Chart chart = ( Chart )event.widget;
      boolean hasAltData = ( ( Boolean )chart.getData( HAS_ALT_DATA ) ).booleanValue();
      boolean isRowDataChart = ( ( Boolean )chart.getData( IS_ROW_DATA_CHART ) ).booleanValue();
      if( ( event.stateMask & SWT.CTRL ) != 0 ) {
        if( isRowDataChart ) {
          chart.drawBarChart( hasAltData ? rowData1 : rowData2 );
        } else {
          chart.drawPieChart( hasAltData ? pointData1 : pointData2 );

        }
      }
      chart.setData( HAS_ALT_DATA, new Boolean( !hasAltData ) );
    }
  }

}
