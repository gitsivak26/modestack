$(document).ready(function () {

	$("#duplicate").hide();
	
	// Does not allow special characters and numerics
	$.validator.addMethod("specialCharacters", function (value, element) {
		var userData = element.value;
		var regex = /^[A-Za-z0-9\s]*$/;
		
		if(userData == regex)
			return false;
		else
			return true;
	});
	
	$("#loginForm").validate({
		rules: {
			userName: {
				required: true,
				maxlength: 20,
				specialCharacters: true
			},
			password: {
				required: true,
				minlength: 6,
				maxlength: 8,
				specialCharacters: true
			}
		},
		
		messages: {
			userName: {
				required: "Please enter username.",
				maxlength: "The maximum charactor is 20.",
				specialCharacters: "Does not allow special characters and numbers."
			},
			password: {
				required: "Please enter password.",
				minlength: "Password must be atlease 6 charactors.",
				maxlength: "The maximum charactor is 8.",
				specialCharacters: "Special charactor not allowd."
			}
		},
		submitHandler: function(form) {
			var redirect_url = "/articles/?accessToken="
			$.ajax({
				type: "POST",
				async: true,
				url: "/login/",
				dataType: 'json',
				data: $("#loginForm").serialize(),
				success: function (response) {
					var response = JSON.stringify(response);
					
					// convert string to JSON
					response = $.parseJSON(response);
					console.log(response);
					console.log(response.accessToken);
					
					window.location.href = redirect_url + response.accessToken;
				},
				error: function (error) {
					console.log(error.status);
					if (error.status == 400)
						$("#duplicate").show();
				}
			});
		}
	});
});