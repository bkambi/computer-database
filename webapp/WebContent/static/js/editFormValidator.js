$.validate({
	lang : 'fr',
	form : '#formEdit',
	modules : 'toggleDisabled',
	onSuccess : function($form) {
		var introduced = $("#introduced").val();
		var discontinued = $("#discontinued").val();
		if (introduced > discontinued) {
			alert("introduced cannot be bigger than discontinued");
			return false;
		}else{
			alert("computer update !")
		}
		return true;
	}
});