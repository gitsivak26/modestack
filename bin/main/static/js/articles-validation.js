
var start = 0;
var size = 3;

$(document).ready(function () {
	
	//var params = new URLSearchParams(window.location.search);
	$("#author_name, #author").val(username);
	
	$("#success").hide();
	
	$("#next").click(function() {
		start = start + size;
		getArticles();
	});
	
	$("#previous").click(function() {
		start = start - size;
		getArticles();
	});
	
	getArticles();
		
	$("#articleForm").validate({
		rules: {
			title: {
				required: true,
				maxlength: 20
			},
			message: {
				required: true,
				minlength: 50,
				maxlength: 250
			}
		},
		
		messages: {
			title: {
				required: "Please enter title.",
				maxlength: "The maximum charactor is 20."
			},
			message: {
				required: "Please enter article.",
				minlength: "Password must be atlease 50 characters.",
				maxlength: "The maximum charactor is 250."
			}
		},
		submitHandler: function(form) {
			$.ajax({
				type: "POST",
				url: "/api/v1/articles/",
				dataType: 'json',
				data: $("#articleForm").serialize(),
				success: function (response) {
					var response = JSON.stringify(response);
					
					// convert string to JSON
					response = $.parseJSON(response);
					
					console.log(response.status);

					$("#success").show();
					$("#title").val('');
					$("#message").val('');
				},
				error: function (error) {
					console.log(error.status);
					$("#success").hide();
					$("#title").val('');
					$("#message").val('');
				}
			});
		}
	});
});

function getArticles() {
	$.ajax({
		type: "GET",
		url: "/api/v1/articles/" + start + "/" + size,
		dataType: 'json',
		success: function (response) {
			var response = JSON.stringify(response);
			
			// convert string to JSON
			response = $.parseJSON(response);
			
			$(function() {
				$("#articles").empty();
				$("#articles").append($('<tr/>').append($('<th/>').text("Articles From Database")));
				$.each(response, function(i, item) {
					$("#articles").append($('<tr/>').append($('<td/>').text(item.author_name)));
					$("#articles").append($('<tr/>').append($('<td/>').text(item.title)));
					$("#articles").append($('<tr/>').append($('<td/>').text(item.message)));
				});
			});
		},
		error: function (error) {
			console.log(error.status);
		}
	});
}