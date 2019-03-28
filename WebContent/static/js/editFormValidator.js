$.validate({
	lang : 'fr',
	form : '#formEdit',
	modules : 'toggleDisabled',
	onSuccess : function($form) {
		var introduced = $("#introduced").val();
		var discontinued = $("#discontinued").val();
		if (introduced < discontinued) {
			return false;
		}
		return true;
	}
});