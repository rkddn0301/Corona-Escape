function doRegGubunCheck(a) {
	console.log(a);
	if (a.gubun.value == "") {
		alert("구분을 입력해주세요.");
		return false;
	}
	if (a.gubun.value == "age" && a.choice.value == "구분 별 내용") {
		alert("연령대를 입력해주세요.");
		return false;
	}
	if (a.gubun.value == "gender" && a.choice.value == "구분 별 내용") {
		alert("성별을 입력해주세요.");
		return false;
	}

	return true;
}

function gubunSelect() {
	var gubun = ""; //연령별, 성별
	var confcase = ""; // 확진자
	var death = ""; // 사망자
	var confcaserate = ""; // 확진률
	var deathrate = ""; // 사망률
	var criticalrate = ""; // 치명률
	var inter = []; // 순위를 구하기 위한 확진자 수 배열 집계
	var rank = []; // 순위
	var k = []; // inter에 있는 문자형식 및 콤마를 변환해줌. 
	var rankrate =""; // 랭킹
	
	if (doRegGubunCheck(document.getElementById('gubun_select'))) {
		var choice = document.getElementById('reg_choice');
		$.ajax({
				url : '/gubun/gubunsuccess.do',
				type : 'post',
				data : {
						'gubun' : $('#reg_gubun').val()
					},
					success : function(a) {
						console.log(a);
						if (a == 1) {
							$.ajax({
									url : '/gubun/getgubun.do',
									type : 'post',
									dataType : "JSON",
									contentType : "application/json; charset=UTF-8",
									success : function(json) {
										// 확진자 수 배열로 집계
										inter = [json[0].confcase,json[1].confcase,json[2].confcase,json[3].confcase,
												json[4].confcase,json[5].confcase,json[6].confcase,json[7].confcase,json[8].confcase];
								
										// String형식에다가 콤마가 있으니 콤마를 없앤 후 int형으로 변환
										for (var i=0; i < inter.length; i++) {
											k[i] = inter[i].replace(/,/g, "");
											console.log("k" + i + "에 들어간 값 : " + inter[i])
											k[i] = parseInt(k[i]);
											console.log("변환된 값 : " + k[i]);
											}
										// 랭킹을 집계
										for (var a=0; a < k.length; a++){
											rank[a] = 1;
										
											for (var b=0; b < k.length; b++){
												if(k[a] < k[b]) rank[a]++;
												console.log(a + "의 현랭킹" + rank[a]);
											}
										}

											if (choice.value == "0~9세") {
												gubun = ("선택한 연령대 : " + json[8].gubun + "세");
												confcase = ("확진자 : " + json[8].confcase + "명");
												death = ("사망자 : " + json[8].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[8] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#e07979")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#e07979")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;

											}
											/**
											 * 10대 차트
											 */
											else if (choice.value == "10~19세") {
												gubun = ("선택한 연령대 : "+ json[7].gubun + "세");
												confcase = ("확진자 : "+ json[7].confcase + "명");
												death = ("사망자 : "+ json[7].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[7] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											} 
											/**
											 * 20대 차트
											 */
											
											else if (choice.value == "20~29세") {
												gubun = ("선택한 연령대 : "+ json[6].gubun + "세");
												confcase = ("확진자 : "+ json[6].confcase + "명");
												death = ("사망자 : "+ json[6].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[6] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#e07979")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											}
											/**
											 * 30대 차트 
											 */
											
											else if (choice.value == "30~39세") {
												gubun = ("선택한 연령대 : "+ json[5].gubun + "세");
												confcase = ("확진자 : "+ json[5].confcase + "명");
												death = ("사망자 : "+ json[5].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[5] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											} 
											/**
											 * 40대 차트
											 */
											else if (choice.value == "40~49세") {
												gubun = ("선택한 연령대 : "+ json[4].gubun + "세");
												confcase = ("확진자 : "+ json[4].confcase + "명");
												death = ("사망자 : "+ json[4].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[4] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											} 
											/**
											 * 50대 차트
											 */
											else if (choice.value == "50~59세") {
												gubun = ("선택한 연령대 : "+ json[3].gubun + "세");
												confcase = ("확진자 : "+ json[3].confcase + "명");
												death = ("사망자 : "+ json[3].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[3] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											} 
											/**
											 * 60대 차트
											 */
											else if (choice.value == "60~69세") {
												gubun = ("선택한 연령대 : "+ json[2].gubun + "세");
												confcase = ("확진자 : "+ json[2].confcase + "명");
												death = ("사망자 : "+ json[2].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[2] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											} 
											/**
											 * 70대 차트
											 */
											else if (choice.value == "70~79세") {
												gubun = ("선택한 연령대 : "+ json[1].gubun + "세");
												confcase = ("확진자 : "+ json[1].confcase + "명");
												death = ("사망자 : "+ json[1].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[1] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														}];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											} 
											/**
											 * 80대 차트
											 */
											
											else if (choice.value == "80세 이상") {
												gubun = ("선택한 연령대 : " + json[0].gubun);
												confcase = ("확진자 : "+ json[0].confcase + "명");
												death = ("사망자 : "+ json[0].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("연령대 순위 : " + rank[0] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].confcase,
															"color" : am4core.color("#64debb")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].confcase,
															"color" : am4core.color("#64debb")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[7].gubun,
															"value" : json[7].death,
															"color" : am4core.color("#6b5a60")
														},
														{
															"key" : json[8].gubun,
															"value" : json[8].death,
															"color" : am4core.color("#6b5a60")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#e07979")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[2].gubun,
															"value" : json[2].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[3].gubun,
															"value" : json[3].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[4].gubun,
															"value" : json[4].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[5].gubun,
															"value" : json[5].criticalrate,
															"color" : am4core.color("#ed8013")
														},
														{
															"key" : json[6].gubun,
															"value" : json[6].criticalrate,
															"color" : am4core.color("#ed8013")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
											}
											$('#gubun').html(gubun);
											$('#confcase').html(confcase);
											$('#death').html(death);
											$('#confcaserate').html(confcaserate);
											$('#deathrate').html(deathrate);
											$('#criticalrate').html(criticalrate);
											$('#rankrate').html(rankrate);
										}
									})
						} else if (a == 2) {
							$
									.ajax({
										url : '/gubun/getgender.do',
										type : 'post',
										dataType : "JSON",
										contentType : "application/json; charset=UTF-8",
										success : function(json) {
											// 확진자 수 배열로 집계
											inter = [json[0].confcase,json[1].confcase];
									
											// String형식에다가 콤마가 있으니 콤마를 없앤 후 int형으로 변환
											for (var i=0; i < inter.length; i++) {
												k[i] = inter[i].replace(/,/g, "");
												console.log("k" + i + "에 들어간 값 : " + inter[i])
												k[i] = parseInt(k[i]);
												console.log("변환된 값 : " + k[i]);
												}
											// 랭킹을 집계
											for (var a=0; a < k.length; a++){
												rank[a] = 1;
											
												for (var b=0; b < k.length; b++){
													if(k[a] < k[b]) rank[a]++;
													console.log(a + "의 현랭킹" + rank[a]);
												}
											}

											if (choice.value == "남성") {
												gubun = ("선택한 성별 : " + json[0].gubun);
												confcase = ("확진자 : "+ json[0].confcase + "명");
												death = ("사망자 : "+ json[0].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("성별 순위 : " + rank[0] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#398ddb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#ed3b56")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#398ddb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#ed3b56")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#398ddb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed3b56")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
											} else if (choice.value == "여성") {
												gubun = ("선택한 성별 : " + json[1].gubun);
												confcase = ("확진자 : "+ json[1].confcase + "명");
												death = ("사망자 : "+ json[1].death + "명");
												confcaserate = "확진률";
												deathrate = "사망률";
												criticalrate = "치명률";
												rankrate = ("성별 순위 : " + rank[1] + "위");

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("confchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].confcase,
															"color" : am4core.color("#398ddb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].confcase,
															"color" : am4core.color("#ed3b56")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝
												
												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end

												// Create chart instance
												var chart = am4core.create("deathchart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].death,
															"color" : am4core.color("#398ddb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].death,
															"color" : am4core.color("#ed3b56")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
												
												// 차트 끝

												// Themes begin
												am4core.useTheme(am4themes_animated);
												// Themes end
												// Create chart instance
												var chart = am4core.create("crichart", am4charts.PieChart);

												// Add data
												chart.data = [
														{
															"key" : json[0].gubun,
															"value" : json[0].criticalrate,
															"color" : am4core.color("#398ddb")
														},
														{
															"key" : json[1].gubun,
															"value" : json[1].criticalrate,
															"color" : am4core.color("#ed3b56")
														} ];

												// Set inner radius
												chart.innerRadius = am4core.percent(50);
												chart.radius = am4core.percent(40);

												// Add and configure Series
												var pieSeries = chart.series.push(new am4charts.PieSeries());
												pieSeries.dataFields.value = "value";
												pieSeries.dataFields.category = "key";
												pieSeries.slices.template.propertyFields.fill = "color";
												pieSeries.slices.template.stroke = am4core.color("#fff");
												pieSeries.slices.template.strokeWidth = 2;
												pieSeries.slices.template.strokeOpacity = 1;

												// This creates initial
												// animation
												pieSeries.hiddenState.properties.opacity = 2;
												pieSeries.hiddenState.properties.endAngle = -90;
												pieSeries.hiddenState.properties.startAngle = -90;
											}
											$('#gubun').html(gubun);
											$('#confcase').html(confcase);
											$('#death').html(death);
											$('#confcaserate').html(confcaserate);
											$('#deathrate').html(deathrate);
											$('#criticalrate').html(criticalrate);
											$('#rankrate').html(rankrate);
										}
									})
						}
					}
				})

	}

}