package org.eclipse.rap.chartjs;

import org.eclipse.swt.graphics.RGB;


public class ChartStyle {

  private RGB fillColor = new RGB( 128, 128, 128 );
//  private double fillOpacity;
  private RGB strokeColor = new RGB( 0, 0, 0 );
  private RGB pointColor = new RGB( 0, 0, 0 );
  private RGB pointStrokeColor = new RGB( 255, 255, 255 );

  public ChartStyle() {
    // TODO Auto-generated constructor stub
  }

  public ChartStyle( int red, int green, int blue ) {
    RGB rgb = new RGB( red, green, blue );
    setFillColor( rgb );
    setStrokeColor( rgb );
    setPointColor( rgb );
  }

  public void setFillColor( RGB fillColor ) {
    this.fillColor = fillColor;
  }

  public RGB getFillColor() {
    return fillColor;
  }

//  public void setFillOpacity( double fillOpacity ) {
//    this.fillOpacity = fillOpacity;
//  }

  public void setStrokeColor( RGB strokeColor ) {
    this.strokeColor = strokeColor;
  }

  public RGB getStrokeColor() {
    return strokeColor;
  }

  public void setPointColor( RGB pointColor ) {
    this.pointColor = pointColor;
  }

  public RGB getPointColor() {
    return pointColor;
  }

  public void setPointStrokeColor( RGB pointStrokeColor ) {
    this.pointStrokeColor = pointStrokeColor;
  }

  public RGB getPointStrokeColor() {
    return pointStrokeColor;
  }

  static String asCss( RGB rgb ) {
    return "rgb( " + rgb.red + ", " + rgb.green + ", " + rgb.blue + ")";
  }

}
