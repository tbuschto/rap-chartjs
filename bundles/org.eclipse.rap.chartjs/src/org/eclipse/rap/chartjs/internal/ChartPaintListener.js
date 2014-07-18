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

function handleEvent( event ) {
  if( event.gc.measureText ) { // exclude old IE
    var type = event.widget.getData( "chartType" );
    var data = event.widget.getData( "chartData" );
    var options = event.widget.getData( "chartOptions" );
    new Chart( event.gc )[ type ]( data, options );
    // on later redraws we don't want to replay the appear animation:
    options.animation = false; // will be reset by server for new charts
  }
}
