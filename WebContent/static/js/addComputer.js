$( "#addComputer" ).submit(function( event ) {
  
	 var computerName= $( "#computerName" ).val();
	 var introduced = $( "#introduced" ).val();
	  var discontinued = $( "#discontinued" ).val();
	  var company = $('#companyId').find(":selected").text();
    $( "#msg" ).text( "Validated..." ).show();
 
  $( "#msg" ).text( "Not valid!" ).show().fadeOut( 1000 );
  event.preventDefault();
});