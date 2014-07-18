function handleEvent( event ) {
  if( event.gc.measureText ) { // exclude old IE
    var type = event.widget.getData( "chartType" );
    var data = event.widget.getData( "chartData" );
    var options = event.widget.getData( "chartOptions" );
    new Chart( event.gc )[ type ]( data, options );
    options.animation = false;
  }
}
