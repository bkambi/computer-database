$(document)
.ready(
		function() {
			$("#btnSubmit")
			.click(
					function(event) {

						var computerName = $("#computerName")
						.val();
						var intro = $("#introduced").val();
						var disco = $("#discontinued").val();
						var companyName = $('#companyId').val();
						if (intro <= disco & !(intro == "")
								& !(disco == "" ) & !(companyName == "" ) &!(computerName == "" )) {
							$( "#addComputer" ).submit();
						} else {
							$("#msg")
							.val(
							"<div class=\"alert alert-danger\">Error Data incorrect ! <br /> Discontinued is bigger than Introduced ! </div>")
							.show().fadeOut(10000);
						}
					});

		});
