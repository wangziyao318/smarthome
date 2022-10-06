$(function () {
	/**
	 * jQuery AJAX for register
	 */
	$(":submit").click(function () {
		let flag = true;
		$("input").each(function () {
			if ($(this).val() === "") {
				flag = false;
				return false;
			}
		});

		if (flag) {
			$.ajax({
				type: "POST", // POST / GET
				contentType:"application/json;charset=UTF-8", // content post to server
				url: "./servlet/user/register",
				data: JSON.stringify({
					"username": $("input[name='username']").val(),
					"uPassword": SparkMD5.hash($(":password").val()),
					"fName": $("input[name='fName']").val(),
					"lName": $("input[name='lName']").val(),
					"fAddress": $("input[name='fAddress']").val(),
				}), // JS object -> JSON
				success: function (data) {
					if (data.toString().charAt(0) === '{') // JSONObject
						location.replace("./login");
					else
						alert(data);
				}
			});
		}
	});
});