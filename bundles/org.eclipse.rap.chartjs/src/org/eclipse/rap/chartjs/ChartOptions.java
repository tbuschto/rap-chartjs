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

import org.eclipse.rap.json.JsonObject;

public class ChartOptions {

  private boolean animation = true;
  private boolean showToolTips = true;
  private boolean scaleBeginAtZero = true;
  private boolean bezierCurve = true;
  private boolean showFill = true;
  private boolean scaleShowLabels = true;
  private int pointDotRadius = 3;
  private int strokeWidth = 2;

  public ChartOptions setAnimation( boolean animation ) {
    this.animation = animation;
    return this;
  }

  public boolean getAnimation() {
    return this.animation;
  }

  public ChartOptions setShowToolTips( boolean showToolTips ) {
    this.showToolTips = showToolTips;
    return this;
  }

  public boolean getShowToolTips() {
    return this.showToolTips;
  }

  public boolean getScaleBeginAtZero() {
    return scaleBeginAtZero;
  }

  public ChartOptions setScaleBeginAtZero( boolean scaleBeginAtZero ) {
    this.scaleBeginAtZero = scaleBeginAtZero;
    return this;
  }

  public boolean getBezierCurve() {
    return bezierCurve;
  }

  public ChartOptions setBezierCurve( boolean bezierCurve ) {
    this.bezierCurve = bezierCurve;
    return this;
  }

  public boolean getShowFill() {
    return showFill;
  }

  public ChartOptions setShowFill( boolean showFill ) {
    this.showFill = showFill;
    return this;
  }

  public int getPointDotRadius() {
    return pointDotRadius;
  }

  public ChartOptions setPointDotRadius( int pointDotRadius ) {
    this.pointDotRadius = pointDotRadius;
    return this;
  }

  public int getStrokeWidth() {
    return strokeWidth;
  }

  public ChartOptions setStrokeWidth( int strokeWidth ) {
    this.strokeWidth = strokeWidth;
    return this;
  }

  public boolean getScaleShowLabels() {
    return scaleShowLabels;
  }

  public ChartOptions setScaleShowLabels( boolean scaleShowLabels ) {
    this.scaleShowLabels = scaleShowLabels;
    return this;
  }

  JsonObject toJson() {
    JsonObject result = new JsonObject();
    result.add( "animation", animation );
    result.add( "showTooltips", showToolTips );
    result.add( "scaleBeginAtZero", scaleBeginAtZero );
    result.add( "bezierCurve", bezierCurve );
    result.add( "datasetFill", showFill );
    result.add( "pointDot", pointDotRadius > 0 ? true : false );
    result.add( "pointDotRadius", pointDotRadius );
    result.add( "showStroke", strokeWidth > 0 ? true : false );
    result.add( "strokeWidth", strokeWidth );
    result.add( "scaleShowLabels", scaleShowLabels );
    return result;
  }

}

