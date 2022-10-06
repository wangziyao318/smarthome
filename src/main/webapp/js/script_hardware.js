$(function () {
	let user = JSON.parse(sessionStorage.getItem("user"));
	$("#username").append(user.username);

	/**
	 * AJAX for hardware query
	 */
	$.ajax({
		type: "POST", // POST / GET
		contentType:"application/json;charset=UTF-8", // content post to server
		url: "../../servlet/user/hardware", // url rewrite filter
		data: JSON.stringify({
			"familyId": user.familyId,
			"hwTypeId": hwTypeId,
			"dataTypeId": dataTypeId
		}), // JS object -> JSON
		success: function (data) {
			if (data.toString().charAt(0) === '[') { // JSON Array
				let al = JSON.parse(data);

				let hl = al[0]; // hardwareList
				hardwareTable(hl);

				let hc = al[1]; // highChartJSON
				$("#chart-container").highcharts(hc);
			} else
				$("#table-container").append("<h1 class='display-1'>Empty Set</h1>");
		}
	});

	$("#user").click(function () {
		location.href = "../../user";
	});
	$("#profile").click(function () {
		location.href = "../../user/profile";
	});
	$("#doc").click(function () {
		location.href = "../../doc";
	});
	$("#logout").click(function () {
		sessionStorage.clear();
		localStorage.clear();
		location.replace("../../login"); // can't go back
	});
});

/**
 * Print table using jQuery & html DOM
 * @param hl
 */
function hardwareTable (hl) {
	// th, tr, td
	if (hwTypeId === 1003) {
		$("#table-container").append("<table class='table'><tr>\n" +
			"<th>Hardware Name</th>\n" +
			"<th>Description</th>\n" +
			"<th>Bought Time</th>\n" +
			"<th>Damage Sign</th>\n" +
			"<th>Manufacturer</th>\n" +
			"<th>Address</th>\n" +
			"<th>Phone Number</th>\n" +
			"<th>Control</th>\n" +
			"</tr></table>");

		for (let hardware of hl) {
			$("table").append("<tr id=" + hardware.hardwareId + "></tr>");
			$("#" + hardware.hardwareId).append("<td>" + hardware.hwName + "</td>" +
				"<td>" + hardware.description + "</td>" +
				"<td>" + (new Date(hardware.buyTime)).toLocaleString() + "</td>" +
				"<td>" + hardware.damageSign + "</td>" +
				"<td>" + hardware.name + "</td>" +
				"<td>" + hardware.factoryAddress + "</td>" +
				"<td>" + hardware.phoneNumber + "</td>" +
				"<td><button id='btn_" + hardware.hardwareId + "' type='button' class='btn btn-outline-dark'>On/Off</button></td>");

			$("#btn_" + hardware.hardwareId).click(function () { // light ON/OFF
				if ($(this).prop("class") === "btn btn-outline-dark") {
					// TURN ON
					$.ajax({
						type: "POST", // POST / GET
						contentType:"application/json;charset=UTF-8", // content post to server
						url: "../../servlet/user/light", // url rewrite filter
						data: JSON.stringify({
							"state": true
						}), // JS object -> JSON
						success: function (data) {
							alert(data);
						}
					});

					$(this).prop("class", "btn btn-outline-light");
				} else {
					// TURN OFF
					$.ajax({
						type: "POST", // POST / GET
						contentType:"application/json;charset=UTF-8", // content post to server
						url: "../../servlet/user/light", // url rewrite filter
						data: JSON.stringify({
							"state": false
						}), // JS object -> JSON
						success: function (data) {
							alert(data);
						}
					});

					$(this).prop("class", "btn btn-outline-dark");
				}
			});
		}
	} else {
		$("#table-container").append("<table class='table'><tr>\n" +
			"<th>Hardware Name</th>\n" +
			"<th>Description</th>\n" +
			"<th>Bought Time</th>\n" +
			"<th>Damage Sign</th>\n" +
			"<th>Manufacturer</th>\n" +
			"<th>Address</th>\n" +
			"<th>Phone Number</th>\n" +
			"</tr></table>");

		for (let hardware of hl) {
			$("table").append("<tr id=" + hardware.hardwareId + "></tr>");
			$("#" + hardware.hardwareId).append("<td>" + hardware.hwName + "</td>" +
				"<td>" + hardware.description + "</td>" +
				"<td>" + (new Date(hardware.buyTime)).toLocaleString() + "</td>" +
				"<td>" + hardware.damageSign + "</td>" +
				"<td>" + hardware.name + "</td>" +
				"<td>" + hardware.factoryAddress + "</td>" +
				"<td>" + hardware.phoneNumber + "</td>");
		}
	}
}