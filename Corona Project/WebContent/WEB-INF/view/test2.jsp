<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>막대그래프</title>
</head>
<body>
<!-- Styles -->
<style>
#chartdiv {
  width: 1000px;
  height: 500px;
}

</style>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

 // Create chart instance
var chart = am4core.create("chartdiv", am4charts.XYChart);

chart.legend = new am4charts.Legend()
chart.legend.position = 'bottom'
chart.legend.paddingBottom = 20
chart.legend.labels.template.maxWidth = 95

// Add data
chart.data = [{
  "year": "서울",
  "income": "47,035",
  "expenses": "132,787"
},{
  "year": "부산",
  "income": "13,600",
  "expenses": "41,186"  
},{
  "year": "경기",
  "income": "75,272",
  "expenses": "184,775"
},{
  "year": "전라",
  "income": "7,240",
  "expenses": "20,930"
},{
  "year": "경상",
  "income": "12,872",
  "expenses": "33,616"
}];

// Create axes
var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "year";
categoryAxis.renderer.inversed = true;
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.cellStartLocation = 0.1;
categoryAxis.renderer.cellEndLocation = 0.9;

var  valueAxis = chart.xAxes.push(new am4charts.ValueAxis()); 
valueAxis.renderer.opposite = true;

// Create series
function createSeries(field, name) {
  var series = chart.series.push(new am4charts.ColumnSeries());
  series.dataFields.valueX = field;
  series.dataFields.categoryY = "year";
  series.name = name;
  series.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
  series.columns.template.height = am4core.percent(100);
  series.sequencedInterpolation = true;

  var valueLabel = series.bullets.push(new am4charts.LabelBullet());
  valueLabel.label.text = "{valueX}";
  valueLabel.label.horizontalCenter = "left";
  valueLabel.label.dx = 10;
  valueLabel.label.hideOversized = false;
  valueLabel.label.truncate = false;

  var categoryLabel = series.bullets.push(new am4charts.LabelBullet());
  categoryLabel.label.text = "{name}";
  categoryLabel.label.horizontalCenter = "right";
  categoryLabel.label.dx = -10;
  categoryLabel.label.fill = am4core.color("#fff");
  categoryLabel.label.hideOversized = false;
  categoryLabel.label.truncate = false;
}

createSeries("income", "Income");
createSeries("expenses", "Expenses");

}); // end am4core.ready()
</script>

<!-- HTML -->
<div id="chartdiv"></div>
</body>
</html>