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


public class ChartDemo extends AbstractEntryPoint {

  @Override
  protected void createContents( Composite parent ) {
    ChartRowData rowData = new ChartRowData( new String[] { "January","February","March","April","May","June","July" } );
    rowData.addRow( new int[] { 28, 48, 40, 19, 96, 27, 100 }, new ChartStyle( 220, 220, 220, 0.8f ) );
    rowData.addRow( new int[] { 27, 100, 28, 48, 40, 19, 96 }, new ChartStyle( 151, 187, 205, 0.7f ) );
    ChartPointData pointData = new ChartPointData();
    pointData.addPoint( 28, new ChartStyle( 250, 187, 105, 0.9f ) );
    pointData.addPoint( 88, new ChartStyle( 50, 187, 205, 0.9f ) );
    pointData.addPoint( 51, new ChartStyle( 10, 17, 133, 0.9f ) );
    parent.setLayout( new GridLayout( 3, true ) );
    createChart( parent ).drawLineChart( rowData );
    createChart( parent ).drawBarChart( rowData );
    createChart( parent ).drawRadarChart( rowData );
    createChart( parent ).drawPolarAreaChart( pointData );
    createChart( parent ).drawPieChart( pointData );
    createChart( parent ).drawDoughnutChart( pointData );
  }

  private Chart createChart( Composite parent ) {
    Chart chart = new Chart( parent, SWT.NONE );
    chart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    return chart;
  }


}
