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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;


public class ChartPointData {

  private final List<Integer> points = new ArrayList<Integer>( 5 );
  private final List<ChartStyle> colors = new ArrayList<ChartStyle>( 5 );

  public void addPoint( int value, ChartStyle chartStyle ) {
    points.add( value );
    colors.add( chartStyle );
  }

  JsonArray toJson() {
    JsonArray result = new JsonArray();
    for( int i = 0; i < points.size(); i++ ) {
      result.add( new JsonObject()
        .add( "value", points.get( i ) )
        .add( "color", ChartStyle.asCss( colors.get( i ).getFillColor() ) )
      );
    }
    return result;
  }

}
