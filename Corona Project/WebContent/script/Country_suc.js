function getSeoul() {

	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[1].countryName);
			var countrycase = ("확진자 : " + json[1].countrycase + "명 [+"
					+ json[1].newCase + "명]");
			var recovered = ("완치자 : " + json[1].recovered + "명");
			var death = ("사망자 : " + json[1].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})

	$.ajax({
		url : "/country/getSeoulcity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : json[0].city,
				"count" : json[0].cases
			}, {

				"word" : json[1].city,
				"count" : json[1].cases
			}, {

				"word" : json[2].city,
				"count" : json[2].cases
			}, {

				"word" : json[3].city,
				"count" : json[3].cases
			}, {

				"word" : json[4].city,
				"count" : json[4].cases
			}, {

				"word" : json[5].city,
				"count" : json[5].cases
			}, {

				"word" : json[6].city,
				"count" : json[6].cases
			}, {

				"word" : json[7].city,
				"count" : json[7].cases
			}, {

				"word" : json[8].city,
				"count" : json[8].cases
			}, {

				"word" : json[9].city,
				"count" : json[9].cases
			}, {

				"word" : json[10].city,
				"count" : json[10].cases
			}, {

				"word" : json[11].city,
				"count" : json[11].cases
			}, {

				"word" : json[12].city,
				"count" : json[12].cases
			}, {

				"word" : json[13].city,
				"count" : json[13].cases
			}, {

				"word" : json[14].city,
				"count" : json[14].cases
			}, {

				"word" : json[15].city,
				"count" : json[15].cases
			}, {

				"word" : json[16].city,
				"count" : json[16].cases
			}, {

				"word" : json[17].city,
				"count" : json[17].cases
			}, {

				"word" : json[18].city,
				"count" : json[18].cases
			}, {

				"word" : json[19].city,
				"count" : json[19].cases
			}, {

				"word" : json[20].city,
				"count" : json[20].cases
			}, {

				"word" : json[21].city,
				"count" : json[21].cases
			}, {

				"word" : json[22].city,
				"count" : json[22].cases
			}, {

				"word" : json[23].city,
				"count" : json[23].cases
			}, {

				"word" : json[24].city,
				"count" : json[24].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getBusan() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[2].countryName);
			var countrycase = ("확진자 : " + json[2].countrycase + "명 [+"
					+ json[2].newCase + "명]");
			var recovered = ("완치자 : " + json[2].recovered + "명");
			var death = ("사망자 : " + json[2].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})

	$.ajax({
		url : "/country/getBusancity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : json[19].city,
				"count" : json[19].cases
			}, {

				"word" : json[20].city,
				"count" : json[20].cases
			}, {

				"word" : json[21].city,
				"count" : json[21].cases
			}, {

				"word" : json[22].city,
				"count" : json[22].cases
			}, {

				"word" : json[23].city,
				"count" : json[23].cases
			}, {

				"word" : json[24].city,
				"count" : json[24].cases
			}, {

				"word" : json[25].city,
				"count" : json[25].cases
			}, {

				"word" : json[26].city,
				"count" : json[26].cases
			}, {

				"word" : json[27].city,
				"count" : json[27].cases
			}, {

				"word" : json[28].city,
				"count" : json[28].cases
			}, {

				"word" : json[29].city,
				"count" : json[29].cases
			}, {

				"word" : json[30].city,
				"count" : json[30].cases
			}, {

				"word" : json[31].city,
				"count" : json[31].cases
			}, {

				"word" : json[32].city,
				"count" : json[32].cases
			}, {

				"word" : json[33].city,
				"count" : json[33].cases
			}, {

				"word" : json[34].city,
				"count" : json[34].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})

}

function getDaegu() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[3].countryName);
			var countrycase = ("확진자 : " + json[3].countrycase + "명 [+"
					+ json[3].newCase + "명]");
			var recovered = ("완치자 : " + json[3].recovered + "명");
			var death = ("사망자 : " + json[3].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getDaegucity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			
			const dalsung = [json[7].cases];
			dalsung[0] = dalsung[0].split(" ");
			dalsung1 = dalsung[0][3];
			console.log(dalsung1);
			
			const jung = [dalsung1];
			const k = [];
			
			for (i = 0; i< jung.length; i++) {
				k[i] = jung[i].split("명");
				console.log(k[i]);
			}
			
			const dalseo = [json[6].cases];
			dalseo[0] = dalseo[0].split("(");
			
			
			

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : "중구",
				"count" : json[0].cases
			}, {

				"word" : "동구",
				"count" : json[1].cases
			}, {

				"word" : "서구",
				"count" : json[2].cases
			}, {

				"word" : "북구",
				"count" : json[3].cases
			}, {

				"word" : "수성구",
				"count" : json[4].cases
			}, {

				"word" : "남구",
				"count" : json[5].cases
			}, {

				"word" : "달서구",
				"count" : dalseo[0][0]
			}, {

				"word" : "달성군",
				"count" : k[0][0]
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})

}

function getIncheon() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[4].countryName);
			var countrycase = ("확진자 : " + json[4].countrycase + "명 [+"
					+ json[4].newCase + "명]");
			var recovered = ("완치자 : " + json[4].recovered + "명");
			var death = ("사망자 : " + json[4].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getIncheoncity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : "중구",
				"count" : json[1].cases
			}, {

				"word" : "동구",
				"count" : json[2].cases
			}, {

				"word" : "미추홀구",
				"count" : json[3].cases
			}, {

				"word" : "연수구",
				"count" : json[4].cases
			}, {

				"word" : "남동구",
				"count" : json[5].cases
			}, {

				"word" : "부평구",
				"count" : json[6].cases
			}, {

				"word" : "계양구",
				"count" : json[7].cases
			}, {

				"word" : "서구",
				"count" : json[8].cases
			}, {

				"word" : "강화군",
				"count" : json[9].cases
			}, {

				"word" : "옹진군",
				"count" : json[10].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})

}

function getGwangju() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[5].countryName);
			var countrycase = ("확진자 : " + json[5].countrycase + "명 [+"
					+ json[5].newCase + "명]");
			var recovered = ("완치자 : " + json[5].recovered + "명");
			var death = ("사망자 : " + json[5].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getGwangjucity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : "동구",
				"count" : json[1].cases
			}, {

				"word" : "서구",
				"count" : json[2].cases
			}, {

				"word" : "남구",
				"count" : json[3].cases
			}, {

				"word" : "북구",
				"count" : json[4].cases
			}, {

				"word" : "광산구",
				"count" : json[5].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getDaejeon() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[6].countryName);
			var countrycase = ("확진자 : " + json[6].countrycase + "명 [+"
					+ json[6].newCase + "명]");
			var recovered = ("완치자 : " + json[6].recovered + "명");
			var death = ("사망자 : " + json[6].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})

	$.ajax({
		url : "/country/getDaejeoncity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : json[0].city,
				"count" : json[0].cases
			}, {

				"word" : json[1].city,
				"count" : json[1].cases
			}, {

				"word" : json[2].city,
				"count" : json[2].cases
			}, {

				"word" : json[3].city,
				"count" : json[3].cases
			}, {

				"word" : json[4].city,
				"count" : json[4].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getUlsan() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[7].countryName);
			var countrycase = ("확진자 : " + json[7].countrycase + "명 [+"
					+ json[7].newCase + "명]");
			var recovered = ("완치자 : " + json[7].recovered + "명");
			var death = ("사망자 : " + json[7].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})

	$.ajax({
		url : "/country/getUlsancity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
	
			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : "중구",
				"count" : json[7].cases
			}, {

				"word" : "남구",
				"count" : json[8].cases
			}, {

				"word" : "동구",
				"count" : json[9].cases
			}, {

				"word" : "북구",
				"count" : json[10].cases
			}, {

				"word" : "울주군",
				"count" : json[11].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getSejong() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[8].countryName);
			var countrycase = ("확진자 : " + json[8].countrycase + "명 [+"
					+ json[8].newCase + "명]");
			var recovered = ("완치자 : " + json[8].recovered + "명");
			var death = ("사망자 : " + json[8].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);


			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : "세종",
				"count" : json[8].countrycase
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"
		}
	})
}

function getGyeonggi() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[9].countryName);
			var countrycase = ("확진자 : " + json[9].countrycase + "명 [+"
					+ json[9].newCase + "명]");
			var recovered = ("완치자 : " + json[9].recovered + "명");
			var death = ("사망자 : " + json[9].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})

	$.ajax({
		url : "/country/getGyeonggicity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : '수원',
				"count" : json[1].cases
			}, {

				"word" : '고양',
				"count" : json[2].cases
			}, {

				"word" : '용인',
				"count" : json[3].cases
			}, {

				"word" : '성남',
				"count" : json[4].cases
			}, {

				"word" : '부천',
				"count" : json[5].cases
			}, {

				"word" : '안산',
				"count" : json[6].cases
			}, {

				"word" : '화성',
				"count" : json[7].cases
			}, {

				"word" : '남양주',
				"count" : json[8].cases
			}, {

				"word" : '안양',
				"count" : json[9].cases
			}, {

				"word" : '평택',
				"count" : json[10].cases
			}, {

				"word" : '의정부',
				"count" : json[11].cases
			}, {

				"word" : '파주',
				"count" : json[12].cases
			}, {

				"word" : '시흥',
				"count" : json[13].cases
			}, {

				"word" : '김포',
				"count" : json[14].cases
			}, {

				"word" : '광명',
				"count" : json[15].cases
			}, {

				"word" : '광주',
				"count" : json[16].cases
			}, {

				"word" : '군포',
				"count" : json[17].cases
			}, {

				"word" : '이천',
				"count" : json[18].cases
			}, {

				"word" : '오산',
				"count" : json[19].cases
			}, {

				"word" : '하남',
				"count" : json[20].cases
			}, {

				"word" : '양주',
				"count" : json[21].cases
			}, {

				"word" : '구리',
				"count" : json[22].cases
			}, {

				"word" : '안성',
				"count" : json[23].cases
			}, {

				"word" : '포천',
				"count" : json[24].cases
			}, {

				"word" : '의왕',
				"count" : json[25].cases
			}, {

				"word" : '여주',
				"count" : json[26].cases
			}, {

				"word" : '양평',
				"count" : json[27].cases
			}, {

				"word" : '동두천',
				"count" : json[28].cases
			}, {

				"word" : '과천',
				"count" : json[29].cases
			}, {

				"word" : '가평',
				"count" : json[30].cases
			}, {

				"word" : '연천',
				"count" : json[31].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getGangwon() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[10].countryName);
			var countrycase = ("확진자 : " + json[10].countrycase + "명 [+"
					+ json[10].newCase + "명]");
			var recovered = ("완치자 : " + json[10].recovered + "명");
			var death = ("사망자 : " + json[10].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})

	$.ajax({
		url : "/country/getGangwoncity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : '춘천',
				"count" : json[1].cases
			}, {

				"word" : '원주',
				"count" : json[2].cases
			}, {

				"word" : '강릉',
				"count" : json[3].cases
			}, {

				"word" : '동해',
				"count" : json[4].cases
			}, {

				"word" : '태백',
				"count" : json[5].cases
			}, {

				"word" : '속초',
				"count" : json[6].cases
			}, {

				"word" : '삼척',
				"count" : json[7].cases
			}, {

				"word" : '홍천',
				"count" : json[8].cases
			}, {

				"word" : '횡성',
				"count" : json[9].cases
			}, {

				"word" : '영월',
				"count" : json[10].cases
			}, {

				"word" : '평창',
				"count" : json[11].cases
			}, {

				"word" : '정선',
				"count" : json[12].cases
			}, {

				"word" : '철원',
				"count" : json[13].cases
			}, {

				"word" : '화천',
				"count" : json[14].cases
			}, {

				"word" : '양구',
				"count" : json[15].cases
			}, {

				"word" : '인제',
				"count" : json[16].cases
			}, {

				"word" : '고성',
				"count" : json[17].cases
			}, {

				"word" : '양양',
				"count" : json[18].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getNtChungcheong() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[11].countryName);
			var countrycase = ("확진자 : " + json[11].countrycase + "명 [+"
					+ json[11].newCase + "명]");
			var recovered = ("완치자 : " + json[11].recovered + "명");
			var death = ("사망자 : " + json[11].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getNtChungcheongcity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			const array = [json[0].cases, json[1].cases, json[2].cases, json[3].cases, json[4].cases,
				json[5].cases, json[6].cases, json[7].cases, json[8].cases,
				json[9].cases, json[10].cases];
			const k = [];
			
			for (i = 0; i< array.length; i++) {
				k[i] = array[i].split("(");
			}

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : '단양',
				"count" : k[0][0]
			}, {

				"word" : '제천',
				"count" : k[1][0]
			}, {

				"word" : '충주',
				"count" : k[2][0]
			}, {

				"word" : '음성',
				"count" : k[3][0]
			}, {

				"word" : '괴산',
				"count" : k[4][0]
			}, {

				"word" : '증평',
				"count" : k[5][0]
			}, {

				"word" : '진천',
				"count" : k[6][0]
			}, {

				"word" : '청주',
				"count" : k[7][0]
			}, {

				"word" : '보은',
				"count" : k[8][0]
			}, {

				"word" : '옥천',
				"count" : k[9][0]
			}, {

				"word" : '영동',
				"count" : k[10][0]
			}
			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getStChungcheong() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[12].countryName);
			var countrycase = ("확진자 : " + json[12].countrycase + "명 [+"
					+ json[12].newCase + "명]");
			var recovered = ("완치자 : " + json[12].recovered + "명");
			var death = ("사망자 : " + json[12].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getStChungcheongcity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {			
			
			const array = [json[2].cases, json[3].cases, json[4].cases,
				json[5].cases, json[6].cases, json[7].cases, json[8].cases,
				json[9].cases, json[10].cases, json[11].cases, json[12].cases,
				json[13].cases, json[14].cases, json[15].cases, json[16].cases];
			const k = [];
			
			for (i = 0; i< array.length; i++) {
				k[i] = array[i].split(" ");
			}
			

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);
			
			

			series.data = [ {

				"word" : '천안',
				"count" : k[0][0]
			}, {

				"word" : '공주',
				"count" : k[1][0]
			}, {

				"word" : '보령',
				"count" : k[2][0]
			}, {

				"word" : '아산',
				"count" : k[3][0]
			}, {

				"word" : '서산',
				"count" : k[4][0]
			}, {

				"word" : '논산',
				"count" : k[5][0]
			}, {

				"word" : '계룡',
				"count" : k[6][0]
			}, {

				"word" : '당진',
				"count" : k[7][0]
			}, {

				"word" : '금산',
				"count" : k[8][0]
			}, {

				"word" : '부여',
				"count" : k[9][0]
			}, {

				"word" : '서천',
				"count" : k[10][0]
			}, {

				"word" : '청양',
				"count" : k[11][0]
			}, {

				"word" : '홍성',
				"count" : k[12][0]
			}, {

				"word" : '예산',
				"count" : k[13][0]
			}, {

				"word" : '태안',
				"count" : k[14][0]
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getNtJeolla() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[13].countryName);
			var countrycase = ("확진자 : " + json[13].countrycase + "명 [+"
					+ json[13].newCase + "명]");
			var recovered = ("완치자 : " + json[13].recovered + "명");
			var death = ("사망자 : " + json[13].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getNtJeollacity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			
			const array = [json[0].cases, json[1].cases, json[2].cases, json[3].cases, json[4].cases,
				json[5].cases, json[6].cases, json[7].cases, json[8].cases,
				json[9].cases, json[10].cases, json[11].cases, json[12].cases, json[13].cases];
			const k = [];
			
			for (i = 0; i< array.length; i++) {
				k[i] = array[i].split("명");
			}

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : '전주',
				"count" : k[0][0]
			}, {

				"word" : '군산',
				"count" : k[1][0]
			}, {

				"word" : '익산',
				"count" : k[2][0]
			}, {

				"word" : '정읍',
				"count" : k[3][0]
			}, {

				"word" : '남원',
				"count" : k[4][0]
			}, {

				"word" : '김제',
				"count" : k[5][0]
			}, {

				"word" : '완주',
				"count" : k[6][0]
			}, {

				"word" : '진안',
				"count" : k[7][0]
			}, {

				"word" : '무주',
				"count" : k[8][0]
			}, {

				"word" : '장수',
				"count" : k[9][0]
			}, {

				"word" : '임실',
				"count" : k[10][0]
			}, {

				"word" : '순창',
				"count" : k[11][0]
			}, {

				"word" : '고창',
				"count" : k[12][0]
			}, {

				"word" : '부안',
				"count" : k[13][0]
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getStJeolla() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[14].countryName);
			var countrycase = ("확진자 : " + json[14].countrycase + "명 [+"
					+ json[14].newCase + "명]");
			var recovered = ("완치자 : " + json[14].recovered + "명");
			var death = ("사망자 : " + json[14].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getStJeollacity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : '목포',
				"count" : json[0].cases
			}, {

				"word" : '여수',
				"count" : json[1].cases
			}, {

				"word" : '순천',
				"count" : json[2].cases
			}, {

				"word" : '나주',
				"count" : json[3].cases
			}, {

				"word" : '광양',
				"count" : json[4].cases
			}, {

				"word" : '담양',
				"count" : json[5].cases
			}, {

				"word" : '곡성',
				"count" : json[6].cases
			}, {

				"word" : '구례',
				"count" : json[7].cases
			}, {

				"word" : '고흥',
				"count" : json[8].cases
			}, {

				"word" : '보성',
				"count" : json[9].cases
			}, {

				"word" : '화순',
				"count" : json[10].cases
			}, {

				"word" : '장흥',
				"count" : json[11].cases
			}, {

				"word" : '강진',
				"count" : json[12].cases
			}, {

				"word" : '해남',
				"count" : json[13].cases
			},{

				"word" : '영암',
				"count" : json[14].cases
			},{

				"word" : '무안',
				"count" : json[15].cases
			},{

				"word" : '함평',
				"count" : json[16].cases
			},{

				"word" : '영광',
				"count" : json[17].cases
			},{

				"word" : '장성',
				"count" : json[18].cases
			},{

				"word" : '완도',
				"count" : json[19].cases
			},{

				"word" : '진도',
				"count" : json[20].cases
			},{

				"word" : '신안',
				"count" : json[21].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getNtGyeongsang() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[15].countryName);
			var countrycase = ("확진자 : " + json[15].countrycase + "명 [+"
					+ json[15].newCase + "명]");
			var recovered = ("완치자 : " + json[15].recovered + "명");
			var death = ("사망자 : " + json[15].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getNtGyeongsangcity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : '포항',
				"count" : json[2].cases
			}, {

				"word" : '경주',
				"count" : json[3].cases
			}, {

				"word" : '김천',
				"count" : json[4].cases
			}, {

				"word" : '안동',
				"count" : json[5].cases
			}, {

				"word" : '구미',
				"count" : json[6].cases
			}, {

				"word" : '영주',
				"count" : json[7].cases
			}, {

				"word" : '영천',
				"count" : json[8].cases
			}, {

				"word" : '상주',
				"count" : json[9].cases
			}, {

				"word" : '문경',
				"count" : json[10].cases
			}, {

				"word" : '경산',
				"count" : json[11].cases
			}, {

				"word" : '군위',
				"count" : json[12].cases
			}, {

				"word" : '의성',
				"count" : json[13].cases
			},{

				"word" : '청송',
				"count" : json[14].cases
			},{

				"word" : '영양',
				"count" : json[15].cases
			},{

				"word" : '영덕',
				"count" : json[16].cases
			},{

				"word" : '청도',
				"count" : json[17].cases
			},{

				"word" : '고령',
				"count" : json[18].cases
			},{

				"word" : '성주',
				"count" : json[19].cases
			},{

				"word" : '칠곡',
				"count" : json[20].cases
			},{

				"word" : '예천',
				"count" : json[21].cases
			},{

				"word" : '봉화',
				"count" : json[22].cases
			},{

				"word" : '울진',
				"count" : json[23].cases
			},{

				"word" : '울릉',
				"count" : json[24].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getStGyeongsang() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[16].countryName);
			var countrycase = ("확진자 : " + json[16].countrycase + "명 [+"
					+ json[16].newCase + "명]");
			var recovered = ("완치자 : " + json[16].recovered + "명");
			var death = ("사망자 : " + json[16].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);
		}
	})
	
	$.ajax({
		url : "/country/getStGyeongsangcity.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : '창원',
				"count" : json[2].cases
			}, {

				"word" : '진주',
				"count" : json[3].cases
			}, {

				"word" : '통영',
				"count" : json[4].cases
			}, {

				"word" : '사천',
				"count" : json[5].cases
			}, {

				"word" : '김해',
				"count" : json[6].cases
			}, {

				"word" : '밀양',
				"count" : json[7].cases
			}, {

				"word" : '거제',
				"count" : json[8].cases
			}, {

				"word" : '양산',
				"count" : json[9].cases
			}, {

				"word" : '의령',
				"count" : json[10].cases
			}, {

				"word" : '함안',
				"count" : json[11].cases
			}, {

				"word" : '창녕',
				"count" : json[12].cases
			}, {

				"word" : '고성',
				"count" : json[13].cases
			},{

				"word" : '남해',
				"count" : json[14].cases
			},{

				"word" : '하동',
				"count" : json[15].cases
			},{

				"word" : '산청',
				"count" : json[16].cases
			},{

				"word" : '함양',
				"count" : json[17].cases
			},{

				"word" : '거창',
				"count" : json[18].cases
			},{

				"word" : '합천',
				"count" : json[19].cases
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}

function getJeju() {
	$.ajax({
		url : "/corona/countryMain.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			var countryName = ("지역 : " + json[17].countryName);
			var countrycase = ("확진자 : " + json[17].countrycase + "명 [+"
					+ json[17].newCase + "명]");
			var recovered = ("완치자 : " + json[17].recovered + "명");
			var death = ("사망자 : " + json[17].death + "명");

			$('#countryName').html(countryName);
			$('#countrycase').html(countrycase);
			$('#recovered').html(recovered);
			$('#death').html(death);

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv",
					am4plugins_wordCloud.WordCloud);
			var series = chart.series
					.push(new am4plugins_wordCloud.WordCloudSeries());

			series.accuracy = 4;
			series.step = 15;
			series.rotationThreshold = 0.7;
			series.maxCount = 200;
			series.minWordLength = 2;
			series.labels.template.margin(4, 4, 4, 4);
			series.labels.template.tooltipText = "{text}:{value}";
			series.maxFontSize = am4core.percent(30);

			series.data = [ {

				"word" : "제주",
				"count" : json[17].countrycase
			}

			]
			series.dataFields.value = "count";
			series.dataFields.text = "word";

			series.colors = new am4core.ColorSet();
			series.colors.passOptions = {}; // makes it loop

			// series.labelsContainer.rotation = 45;
			series.angles = [ 0, -90 ];
			series.fontWeight = "700"

		}

	})
}