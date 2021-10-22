

function getTotalVaccine() {
	$.ajax({
		url : "vaccine/getVaccination.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			
			var vaccine1 = ("총 1차 접종 실적 : " + json[0].totalVaccine1 + "명");
			var vaccine2 = ("총 접종완료 실적 : " + json[0].totalVaccine2 + "명");
			
			$('#vaccine1').html(vaccine1);
			$('#vaccine2').html(vaccine2);
			
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_material);
		am4core.useTheme(am4themes_animated);
		// Themes end

		 // Create chart instance
		var chart = am4core.create("vaccinediv", am4charts.XYChart);

		chart.legend = new am4charts.Legend()
		chart.legend.position = 'bottom'
		chart.legend.paddingBottom = 20
		chart.legend.labels.template.maxWidth = 95
		// Add data
		chart.data = [{	
		  "city": json[1].city,
		  "1차 접종": json[1].totalVaccine1,
		  "접종완료": json[1].totalVaccine2
		},{
		  "city": json[2].city,
		  "1차 접종": json[2].totalVaccine1,
		  "접종완료": json[2].totalVaccine2  
		},{
		  "city": json[3].city,
		  "1차 접종": json[3].totalVaccine1,
		  "접종완료": json[3].totalVaccine2
		},{
		  "city": json[4].city,
		  "1차 접종": json[4].totalVaccine1,
		  "접종완료": json[4].totalVaccine2
		},{
		  "city": json[5].city,
		  "1차 접종": json[5].totalVaccine1,
		  "접종완료": json[5].totalVaccine2
		},{
		  "city": json[6].city,
		  "1차 접종": json[6].totalVaccine1,
		  "접종완료": json[6].totalVaccine2
		},{
		  "city": json[7].city,
		  "1차 접종": json[7].totalVaccine1,
		  "접종완료": json[7].totalVaccine2
		},{
		  "city": json[8].city,
		  "1차 접종": json[8].totalVaccine1,
		  "접종완료": json[8].totalVaccine2
		},{
		  "city": json[9].city,
		  "1차 접종": json[9].totalVaccine1,
		  "접종완료": json[9].totalVaccine2
		},{
		  "city": json[10].city,
		  "1차 접종": json[10].totalVaccine1,
		  "접종완료": json[10].totalVaccine2
		},{
		  "city": json[11].city,
		  "1차 접종": json[11].totalVaccine1,
		  "접종완료": json[11].totalVaccine2
		},{
		  "city": json[12].city,
		  "1차 접종": json[12].totalVaccine1,
		  "접종완료": json[12].totalVaccine2
		},{
		  "city": json[13].city,
		  "1차 접종": json[13].totalVaccine1,
		  "접종완료": json[13].totalVaccine2
		},{
		  "city": json[14].city,
		  "1차 접종": json[14].totalVaccine1,
		  "접종완료": json[14].totalVaccine2
		},{
		  "city": json[15].city,
		  "1차 접종": json[15].totalVaccine1,
		  "접종완료": json[15].totalVaccine2
		},{
		  "city": json[16].city,
	      "1차 접종": json[16].totalVaccine1,
		  "접종완료": json[16].totalVaccine2
		},{
		  "city": json[17].city,
		  "1차 접종": json[17].totalVaccine1,
		  "접종완료": json[17].totalVaccine2
		}];

		// Create axes
		var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "city";
		categoryAxis.renderer.inversed = true;
		categoryAxis.numberFormatter.numberFormat = "#";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.9;

		var  valueAxis = chart.xAxes.push(new am4charts.ValueAxis()); 
		valueAxis.renderer.opposite = true;

		// Create series
		function createSeries(field, name) {
		  var series = chart.series.push(new am4charts.ColumnSeries());
		  series.dataFields.valueX = field;
		  series.dataFields.categoryY = "city";
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

		createSeries("1차 접종", "1차 접종");
		createSeries("접종완료", "접종완료");

		}); // end am4core.ready()
		}
	})
	
}
