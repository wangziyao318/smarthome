$(function () {
	/**
	 * AJAX for DataSource initialisation
	 */
	$.ajax({
		type: "POST",
		contentType:"application/json;charset=UTF-8",
		url: "./servlet/user/login",
		data: JSON.stringify({
			"username": "0",
			"uPassword": "0"
		}),
		success: function (data) {}
	});

	$("#register").click(function () {
		location.href = "./register";
	});
	$("#user").click(function () {
		location.replace("./user");
	});
	$("#doc").click(function () {
		location.href = "./doc";
	});
});