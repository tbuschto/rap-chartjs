package org.eclipse.rap.chartjs.demo;

import org.eclipse.rap.chartjs.Chart;
import org.eclipse.rap.chartjs.ChartStyle;
import org.eclipse.rap.chartjs.DataRows;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


public class ChartDemo extends AbstractEntryPoint {

  @Override
  protected void createContents( Composite parent ) {
    Chart chart = new Chart( parent, SWT.NONE );
    chart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    DataRows data = new DataRows( new String[] { "January","February","March","April","May","June","July", "August" } );
    data.addRow( new int[] { 28, 48, 40, 19, 96, 27, 100 }, new ChartStyle( 220, 220, 220 ) );
    data.addRow( new int[] { 100, 28, 48, 40, 19, 96, 27 }, new ChartStyle( 151, 187, 205 ) );
    chart.drawLineChart( data );
  }


}
