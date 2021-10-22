<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/bootstrap/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(window).on("load", function() {
		getInformation();
	});

	function getInformation() {

		$.ajax({
			url : "/corona/coronaMain.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {

				var corona = "";
				for (var i = 0; i < json.length; i++) {
					corona += (json[i].TotalCase + " | ");
					corona += (json[i].TotalRecovered + " | ");
					corona += (json[i].TotalDeath + " | ");
					corona += (json[i].newCase + " | ");
					corona += (json[i].TodayRecovered + " | ");
					corona += (json[i].TodayDeath + " | ");
				}
				$('#corona').html(corona);
			}
		})
	}
</script>

<meta charset="UTF-8">

</head>
<body>
	<h1>코로나</h1>
	<hr />
	<div id="corona"></div>
	<br />
	<hr />

</body>
</html>