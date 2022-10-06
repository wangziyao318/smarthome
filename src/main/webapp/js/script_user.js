$(function () {
	/**
	 * prevent from unauthorised access
	 */
	if (sessionStorage.getItem("user") === null) { // direct url access
		if (localStorage.getItem("user") === null) // identity check, prevent unauthorised direct url access
			location.replace("./login");
		else {
			$.ajax({
				type: "POST",
				contentType:"application/json;charset=UTF-8", // content post to server
				url: "./servlet/user/login",
				data: localStorage.getItem("user"),
				success: function (data) {
					if (data.toString().charAt(0) === '{') { // JSONObject
						sessionStorage.setItem("user", data); // sessionStorage temporary, localStorage permanent; send object via JSON string
						history.go(0); // refresh page
					} else // customer misbehaviour
						alert(data);
				}
			});
		}
	}

	let user = JSON.parse(sessionStorage.getItem("user"));
	$("#username").append(user.username);

	$("#profile").click(function () {
		location.href = "./user/profile";
	});
	$("#doc").click(function () {
		location.href = "./doc";
	});
	$("#logout").click(function () {
		sessionStorage.clear();
		localStorage.clear();
		location.replace("./login"); // can't go back
	});
	$("#temperature").click(function () {
		location.href ="./user/hardware/temperature";
	});
	$("#humidity").click(function () {
		location.href = "./user/hardware/humidity";
	});
	$("#brightness").click(function () {
		location.href = "./user/hardware/brightness";
	});
	$("#alarm").click(function () {
		location.href = "./user/hardware/alarm";
	});
});