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

import org.eclipse.swt.graphics.RGB;


public class ChartStyle {

  private RGB fillColor = new RGB( 128, 128, 128 );
  private float fillOpacity = 1;
  private RGB strokeColor = new RGB( 0, 0, 0 );
  private RGB pointColor = new RGB( 0, 0, 0 );
  private RGB pointStrokeColor = new RGB( 255, 255, 255 );

  public ChartStyle() {
    // keep all initial values
  }

  public ChartStyle( int red, int green, int blue ) {
    this( red, green, blue, 1 );
  }

  public ChartStyle( int red, int green, int blue, float fillOpacity ) {
    RGB rgb = new RGB( red, green, blue );
    setFillColor( rgb );
    setStrokeColor( rgb );
    setPointColor( rgb );
    setFillOpacity( fillOpacity );
  }

  public void setFillColor( RGB fillColor ) {
    this.fillColor = fillColor;
  }

  public RGB getFillColor() {
    return fillColor;
  }

  public void setFillOpacity( float fillOpacity ) {
    this.fillOpacity = fillOpacity;
  }

  public float getFillOpacity() {
    return this.fillOpacity;
  }

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

  static String asCss( RGB rgb, float fillOpacity ) {
    return "rgba( " + rgb.red + ", " + rgb.green + ", " + rgb.blue + "," + fillOpacity + ")";
  }

}
