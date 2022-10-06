$(function () {
	let user = JSON.parse(sessionStorage.getItem("user"));
	$("#username").append(user.username);
	$("#un").append(user.username);
	$("#fName").append(user.fName);
	$("#lName").append(user.lName);
	$("#fAddress").append(user.fAddress);

	$("#user").click(function () {
		location.href = "../user";
	});
	$("#doc").click(function () {
		location.href = "../doc";
	});
	$("#logout").click(function () {
		sessionStorage.clear();
		localStorage.clear();
		location.replace("../login"); // can't go back
	});
	$("#deregister").click(function () {
		$.ajax({
			type: "POST", // POST / GET
			contentType:"application/json;charset=UTF-8", // content post to server
			url: "../servlet/user/deregister",
			data: sessionStorage.getItem("user"), // JS object -> JSON
			success: function (data) {
				alert(data);
				sessionStorage.clear();
				localStorage.clear();
				location.replace("../");
			}
		});
	});
	$("#password_reset").click(function () {
		sessionStorage.clear();
		localStorage.clear();
		location.replace("../password_reset");
	});
});