$(function () {
	/**
	 * jQuery AJAX for login
	 */
	$(":submit").click(function () {
		let user = {
			"username": $(":text").val(),
			"uPassword": SparkMD5.hash($(":password").val())
		};

		if (user.username !== "" && user.uPassword !== SparkMD5.hash("")) {
			$.ajax({
				type: "POST", // POST / GET
				contentType:"application/json;charset=UTF-8", // content post to server
				url: "./servlet/user/login",
				data: JSON.stringify(user), // JS object -> JSON
				success: function (data) {
					if (data.toString().charAt(0) === '{') { // JSONObject
						sessionStorage.setItem("user", data); // sessionStorage temporary, localStorage permanent; send object via JSON string
						if ($(":checkbox").prop("checked"))
							localStorage.setItem("user", JSON.stringify(user)); // remember me
						location.replace("./user"); // can't go back
					} else
						alert(data);
				}
			});
		}
	});
});