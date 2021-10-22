function doRegRouteCheck(a) {
	if (a.city.value == "") {
		alert("시도별 선택을 입력해주세요.");
		return false;
	}
	return true;
}

function panTo() {
	if (doRegRouteCheck(document.getElementById('route_select'))) {
		var city = document.getElementById('reg_city');
		var choice = document.getElementById('reg_choice');
		var moveLatLon = "";

		// 시.도별 이동
		if (city.value == "seoul" && choice.value == "시군구별 선택" ) {
			moveLatLon = new kakao.maps.LatLng(37.551165, 126.988226);
			map.panTo(moveLatLon);
		} else if (city.value == "busan" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(35.161019, 129.047770);
			map.panTo(moveLatLon);
		} else if (city.value == "daegu" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(35.829999, 128.565319);
			map.panTo(moveLatLon);
		} else if (city.value == "incheon" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(37.456292, 126.704555);
			map.panTo(moveLatLon);
		} else if (city.value == "gwangju" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(35.154728, 126.832806);
			map.panTo(moveLatLon);
		} else if (city.value == "daejeon" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(36.341498, 127.393599);
			map.panTo(moveLatLon);
		} else if (city.value == "ulsan" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(35.545769, 129.257422);
			map.panTo(moveLatLon);
		} else if (city.value == "gyeonggi" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(37.412167, 127.521148);
			map.panTo(moveLatLon);
		} else if (city.value == "gangwon" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(37.827889, 128.179967);
			map.panTo(moveLatLon);
		} else if (city.value == "northcc" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(37.001548, 127.686259);
			map.panTo(moveLatLon);
		} else if (city.value == "southcc" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(36.712858, 126.787846);
			map.panTo(moveLatLon);
		} else if (city.value == "northjl" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(35.717925, 127.160236);
			map.panTo(moveLatLon);
		} else if (city.value == "southjl" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(34.883126, 127.012346);
			map.panTo(moveLatLon);
		} else if (city.value == "northgs" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(36.282241, 128.898895);
			map.panTo(moveLatLon);
		} else if (city.value == "southgs" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(35.463241, 128.209271);
			map.panTo(moveLatLon);
		} else if (city.value == "jeju" && choice.value == "시군구별 선택") {
			moveLatLon = new kakao.maps.LatLng(33.418925, 126.789882);
			map.panTo(moveLatLon);
		} else if (city.value == "sejong") {
			moveLatLon = new kakao.maps.LatLng(36.563753, 127.255646);
			map.panTo(moveLatLon);
		} 
		
		// 시군구별 이동
		
		// 서울
	if (city.value == "seoul"){
		if (choice.value == "강남구") {
			moveLatLon = new kakao.maps.LatLng(37.496011, 127.063131);
			map.panTo(moveLatLon);
		} else if (choice.value == "강동구") {
			moveLatLon = new kakao.maps.LatLng(37.550776, 127.146769);
			map.panTo(moveLatLon);
		} else if (choice.value == "강북구") {
			moveLatLon = new kakao.maps.LatLng(37.642975, 127.011337);
			map.panTo(moveLatLon);
		} else if (choice.value == "강서구") {
			moveLatLon = new kakao.maps.LatLng(37.561281, 126.823406);
			map.panTo(moveLatLon);
		} else if (choice.value == "관악구") {
			moveLatLon = new kakao.maps.LatLng(37.467340, 126.945619);
			map.panTo(moveLatLon);
		} else if (choice.value == "광진구") {
			moveLatLon = new kakao.maps.LatLng(37.546130, 127.086385);
			map.panTo(moveLatLon);
		} else if (choice.value == "구로구") {
			moveLatLon = new kakao.maps.LatLng(37.494382, 126.855806);
			map.panTo(moveLatLon);
		} else if (choice.value == "금천구") {
			moveLatLon = new kakao.maps.LatLng(37.460393, 126.901265);
			map.panTo(moveLatLon);
		} else if (choice.value == "노원구") {
			moveLatLon = new kakao.maps.LatLng(37.652272, 127.075249);
			map.panTo(moveLatLon);
		} else if (choice.value == "도봉구") {
			moveLatLon = new kakao.maps.LatLng(37.667299, 127.033302);
			map.panTo(moveLatLon);
		} else if (choice.value == "동대문구") {
			moveLatLon = new kakao.maps.LatLng(37.581938, 127.055187);
			map.panTo(moveLatLon);
		} else if (choice.value == "동작구") {
			moveLatLon = new kakao.maps.LatLng(37.499543, 126.951584);
			map.panTo(moveLatLon);
		} else if (choice.value == "마포구") {
			moveLatLon = new kakao.maps.LatLng(37.559943, 126.908982);
			map.panTo(moveLatLon);
		} else if (choice.value == "서대문구") {
			moveLatLon = new kakao.maps.LatLng(37.577850, 126.939089);
			map.panTo(moveLatLon);
		} else if (choice.value == "서초구") {
			moveLatLon = new kakao.maps.LatLng(37.473281, 127.030675);
			map.panTo(moveLatLon);
		} else if (choice.value == "성동구") {
			moveLatLon = new kakao.maps.LatLng(37.550735, 127.040894);
			map.panTo(moveLatLon);
		} else if (choice.value == "성북구") {
			moveLatLon = new kakao.maps.LatLng(37.605845, 127.017335);
			map.panTo(moveLatLon);
		} else if (choice.value == "송파구") {
			moveLatLon = new kakao.maps.LatLng(37.504720, 127.116177);
			map.panTo(moveLatLon);
		} else if (choice.value == "양천구") {
			moveLatLon = new kakao.maps.LatLng(37.525249, 126.855362);
			map.panTo(moveLatLon);
		} else if (choice.value == "영등포구") {
			moveLatLon = new kakao.maps.LatLng(37.523264, 126.911262);
			map.panTo(moveLatLon);
		} else if (choice.value == "용산구") {
			moveLatLon = new kakao.maps.LatLng(37.531605, 126.979593);
			map.panTo(moveLatLon);
		} else if (choice.value == "은평구") {
			moveLatLon = new kakao.maps.LatLng(37.619937, 126.927316);
			map.panTo(moveLatLon);
		} else if (choice.value == "종로구") {
			moveLatLon = new kakao.maps.LatLng(37.594906, 126.976368);
			map.panTo(moveLatLon);
		} else if (choice.value == "중구") {
			moveLatLon = new kakao.maps.LatLng(37.559847, 126.995538);
			map.panTo(moveLatLon);
		} else if (choice.value == "중랑구") {
			moveLatLon = new kakao.maps.LatLng(37.597493, 127.092959);
			map.panTo(moveLatLon);
		} 
	}
		// 부산
	if (city.value == "busan") {
		if (choice.value == "강서구") {
			moveLatLon = new kakao.maps.LatLng(35.109516, 128.875254);
			map.panTo(moveLatLon);
		} else if (choice.value == "금정구") {
			moveLatLon = new kakao.maps.LatLng(35.258828, 129.092447);
			map.panTo(moveLatLon);
		} else if (choice.value == "기장군") {
			moveLatLon = new kakao.maps.LatLng(35.290682, 129.216057);
			map.panTo(moveLatLon);
		} else if (choice.value == "남구") {
			moveLatLon = new kakao.maps.LatLng(35.115709, 129.099995);
			map.panTo(moveLatLon);
		} else if (choice.value == "동구") {
			moveLatLon = new kakao.maps.LatLng(35.126839, 129.046197);
			map.panTo(moveLatLon);
		} else if (choice.value == "동래구") {
			moveLatLon = new kakao.maps.LatLng(35.206340, 129.079393);
			map.panTo(moveLatLon);
		} else if (choice.value == "부산진구") {
			moveLatLon = new kakao.maps.LatLng(35.165197, 129.041999);
			map.panTo(moveLatLon);
		} else if (choice.value == "북구") {
			moveLatLon = new kakao.maps.LatLng(35.230130, 129.024584);
			map.panTo(moveLatLon);
		} else if (choice.value == "사상구") {
			moveLatLon = new kakao.maps.LatLng(35.159210, 128.988289);
			map.panTo(moveLatLon);
		} else if (choice.value == "사하구") {
			moveLatLon = new kakao.maps.LatLng(35.085666, 128.972698);
			map.panTo(moveLatLon);
		} else if (choice.value == "서구") {
			moveLatLon = new kakao.maps.LatLng(35.087264, 129.018586);
			map.panTo(moveLatLon);
		} else if (choice.value == "수영구") {
			moveLatLon = new kakao.maps.LatLng(35.158383, 129.116730);
			map.panTo(moveLatLon);
		} else if (choice.value == "연제구") {
			moveLatLon = new kakao.maps.LatLng(35.182605, 129.083152);
			map.panTo(moveLatLon);
		} else if (choice.value == "영도구") {
			moveLatLon = new kakao.maps.LatLng(35.072316, 129.069928);
			map.panTo(moveLatLon);
		} else if (choice.value == "중구") {
			moveLatLon = new kakao.maps.LatLng(35.105028, 129.036979);
			map.panTo(moveLatLon);
		} else if (choice.value == "해운대구") {
			moveLatLon = new kakao.maps.LatLng(35.181956, 129.161909);
			map.panTo(moveLatLon);
		}
	}
		// 대구
	if (city.value == "daegu") {
		if (choice.value == "남구") {
			moveLatLon = new kakao.maps.LatLng(35.835060, 128.585583);
			map.panTo(moveLatLon);
		} else if (choice.value == "달서구") {
			moveLatLon = new kakao.maps.LatLng(35.828338, 128.529609);
			map.panTo(moveLatLon);
		} else if (choice.value == "달성군") {
			moveLatLon = new kakao.maps.LatLng(35.758702, 128.497895);
			map.panTo(moveLatLon);
		} else if (choice.value == "동구") {
			moveLatLon = new kakao.maps.LatLng(35.934780, 128.686683);
			map.panTo(moveLatLon);
		} else if (choice.value == "북구") {
			moveLatLon = new kakao.maps.LatLng(35.929428, 128.578615);
			map.panTo(moveLatLon);
		} else if (choice.value == "서구") {
			moveLatLon = new kakao.maps.LatLng(35.874749, 128.550391);
			map.panTo(moveLatLon);
		} else if (choice.value == "수성구") {
			moveLatLon = new kakao.maps.LatLng(35.833487, 128.661512);
			map.panTo(moveLatLon);
		} else if (choice.value == "중구") {
			moveLatLon = new kakao.maps.LatLng(35.866349, 128.593481);
			map.panTo(moveLatLon);
		}
	}
	// 인천
	if (city.value == "incheon") {
		if (choice.value == "강화군") {
			moveLatLon = new kakao.maps.LatLng(37.703004, 126.312299);
			map.panTo(moveLatLon);
		} else if (choice.value == "계양구") {
			moveLatLon = new kakao.maps.LatLng(37.557656, 126.734856);
			map.panTo(moveLatLon);
		} else if (choice.value == "남동구") {
			moveLatLon = new kakao.maps.LatLng(37.430863, 126.730589);
			map.panTo(moveLatLon);
		} else if (choice.value == "동구") {
			moveLatLon = new kakao.maps.LatLng(37.486358, 126.628243);
			map.panTo(moveLatLon);
		} else if (choice.value == "미추홀구") {
			moveLatLon = new kakao.maps.LatLng(37.462568, 126.650136);
			map.panTo(moveLatLon);
		} else if (choice.value == "부평구") {
			moveLatLon = new kakao.maps.LatLng(37.496792, 126.720444);
			map.panTo(moveLatLon);
		} else if (choice.value == "서구") {
			moveLatLon = new kakao.maps.LatLng(37.563886, 126.673331);
			map.panTo(moveLatLon);
		} else if (choice.value == "연수구") {
			moveLatLon = new kakao.maps.LatLng(37.386219, 126.644870);
			map.panTo(moveLatLon);
		} else if (choice.value == "옹진군") {
			moveLatLon = new kakao.maps.LatLng(37.660146, 125.697729);
			map.panTo(moveLatLon);
		} else if (choice.value == "중구") {
			moveLatLon = new kakao.maps.LatLng(37.447342, 126.450838);
			map.panTo(moveLatLon);
		}
	}
	// 광주
	if (city.value =="gwangju") {
		
		if (choice.value == "광산구") {
			moveLatLon = new kakao.maps.LatLng(35.163925, 126.753180);
			map.panTo(moveLatLon);
		} else if (choice.value == "남구") {
			moveLatLon = new kakao.maps.LatLng(35.094265, 126.857326);
			map.panTo(moveLatLon);
		} else if (choice.value == "동구") {
			moveLatLon = new kakao.maps.LatLng(35.117098, 126.949514);
			map.panTo(moveLatLon);
		} else if (choice.value == "북구") {
			moveLatLon = new kakao.maps.LatLng(35.194617, 126.927021);
			map.panTo(moveLatLon);
		} else if (choice.value == "서구") {
			moveLatLon = new kakao.maps.LatLng(35.136046, 126.852099);
			map.panTo(moveLatLon);
		} 
		
	}
	// 대전
	if (city.value == "daejeon") {
		if (choice.value == "대덕구") {
			moveLatLon = new kakao.maps.LatLng(36.412654, 127.437335);
			map.panTo(moveLatLon);
		} else if (choice.value == "동구") {
			moveLatLon = new kakao.maps.LatLng(36.324702, 127.473318);
			map.panTo(moveLatLon);
		} else if (choice.value == "서구") {
			moveLatLon = new kakao.maps.LatLng(36.282732, 127.347365);
			map.panTo(moveLatLon);
		} else if (choice.value == "유성구") {
			moveLatLon = new kakao.maps.LatLng(36.376920, 127.334140);
			map.panTo(moveLatLon);
		} else if (choice.value == "중구") {
			moveLatLon = new kakao.maps.LatLng(36.281164, 127.410310);
			map.panTo(moveLatLon);
		}
	}
	// 울산
	if (city.value == "ulsan") {
		if (choice.value == "남구") {
			moveLatLon = new kakao.maps.LatLng(35.512269, 129.333668);
			map.panTo(moveLatLon);
		} else if (choice.value == "동구") {
			moveLatLon = new kakao.maps.LatLng(35.521844, 129.431967);
			map.panTo(moveLatLon);
		} else if (choice.value == "북구") {
			moveLatLon = new kakao.maps.LatLng(35.610503, 129.393159);
			map.panTo(moveLatLon);
		} else if (choice.value == "울주군") {
			moveLatLon = new kakao.maps.LatLng(35.535631, 129.196933);
			map.panTo(moveLatLon);
		} else if (choice.value == "중구") {
			moveLatLon = new kakao.maps.LatLng(35.571015, 129.308424);
			map.panTo(moveLatLon);
		} 
	}
	
	// 경기
	if (city.value == "gyeonggi") {
		if (choice.value == "가평군") {
			moveLatLon = new kakao.maps.LatLng(37.822444, 127.443280);
			map.panTo(moveLatLon);
		} else if (choice.value == "고양시") {
			moveLatLon = new kakao.maps.LatLng(37.665985, 126.836935);
			map.panTo(moveLatLon);
		} else if (choice.value == "과천시") {
			moveLatLon = new kakao.maps.LatLng(37.433881, 127.001916);
			map.panTo(moveLatLon);
		} else if (choice.value == "광명시") {
			moveLatLon = new kakao.maps.LatLng(37.466309, 126.870204);
			map.panTo(moveLatLon);
		} else if (choice.value == "광주시") {
			moveLatLon = new kakao.maps.LatLng(37.403817, 127.299956);
			map.panTo(moveLatLon);
		} else if (choice.value == "구리시") {
			moveLatLon = new kakao.maps.LatLng(37.599879, 127.131699);
			map.panTo(moveLatLon);
		} else if (choice.value == "군포시") {
			moveLatLon = new kakao.maps.LatLng(37.344434, 126.920904);
			map.panTo(moveLatLon);
		} else if (choice.value == "김포시") {
			moveLatLon = new kakao.maps.LatLng(37.718462, 126.607797);
			map.panTo(moveLatLon);
		} else if (choice.value == "남양주시") {
			moveLatLon = new kakao.maps.LatLng(37.659995, 127.251438);
			map.panTo(moveLatLon);
		} else if (choice.value == "동두천시") {
			moveLatLon = new kakao.maps.LatLng(37.915426, 127.077059);
			map.panTo(moveLatLon);
		} else if (choice.value == "부천시") {
			moveLatLon = new kakao.maps.LatLng(37.505332, 126.788429);
			map.panTo(moveLatLon);
		} else if (choice.value == "성남시") {
			moveLatLon = new kakao.maps.LatLng(37.408895, 127.116901);
			map.panTo(moveLatLon);
		} else if (choice.value == "수원시") {
			moveLatLon = new kakao.maps.LatLng(37.280692, 127.009710);
			map.panTo(moveLatLon);
		} else if (choice.value == "시흥시") {
			moveLatLon = new kakao.maps.LatLng(37.380522, 126.775079);
			map.panTo(moveLatLon);
		} else if (choice.value == "안산시") {
			moveLatLon = new kakao.maps.LatLng(37.322041, 126.831915);
			map.panTo(moveLatLon);
		} else if (choice.value == "안성시") {
			moveLatLon = new kakao.maps.LatLng(37.034411, 127.303770);
			map.panTo(moveLatLon);
		} else if (choice.value == "안양시") {
			moveLatLon = new kakao.maps.LatLng(37.402713, 126.927776);
			map.panTo(moveLatLon);
		} else if (choice.value == "양주시") {
			moveLatLon = new kakao.maps.LatLng(37.806902, 126.998408);
			map.panTo(moveLatLon);
		} else if (choice.value == "양평군") {
			moveLatLon = new kakao.maps.LatLng(37.514652, 127.574302);
			map.panTo(moveLatLon);
		} else if (choice.value == "여주시") {
			moveLatLon = new kakao.maps.LatLng(37.305887, 127.617386);
			map.panTo(moveLatLon);
		} else if (choice.value == "연천군") {
			moveLatLon = new kakao.maps.LatLng(38.095413, 126.977901);
			map.panTo(moveLatLon);
		} else if (choice.value == "오산시") {
			moveLatLon = new kakao.maps.LatLng(37.160761, 127.050915);
			map.panTo(moveLatLon);
		} else if (choice.value == "용인시") {
			moveLatLon = new kakao.maps.LatLng(37.221445, 127.216822);
			map.panTo(moveLatLon);
		} else if (choice.value == "의왕시") {
			moveLatLon = new kakao.maps.LatLng(37.362964, 126.990045);
			map.panTo(moveLatLon);
		} else if (choice.value == "의정부시") {
			moveLatLon = new kakao.maps.LatLng(37.736602, 127.068389);
			map.panTo(moveLatLon);
		} else if (choice.value == "이천시") {
			moveLatLon = new kakao.maps.LatLng(37.211960, 127.488211);
			map.panTo(moveLatLon);
		} else if (choice.value == "파주시") {
			moveLatLon = new kakao.maps.LatLng(37.799077, 126.808715);
			map.panTo(moveLatLon);
		} else if (choice.value == "평택시") {
			moveLatLon = new kakao.maps.LatLng(37.008504, 126.987434);
			map.panTo(moveLatLon);
		} else if (choice.value == "포천시") {
			moveLatLon = new kakao.maps.LatLng(37.915890, 127.221742);
			map.panTo(moveLatLon);
		} else if (choice.value == "하남시") {
			moveLatLon = new kakao.maps.LatLng(37.520931, 127.209132);
			map.panTo(moveLatLon);
		} else if (choice.value == "화성시") {
			moveLatLon = new kakao.maps.LatLng(37.173850, 126.809963);
			map.panTo(moveLatLon);
		} else if (choice.value == "동구") {
			moveLatLon = new kakao.maps.LatLng(35.521844, 129.431967);
			map.panTo(moveLatLon);
		}
	}
	
	// 강원
	if (city.value == "gangwon") {
		if (choice.value == "강릉시") {
			moveLatLon = new kakao.maps.LatLng(37.723228, 128.851244);
			map.panTo(moveLatLon);
		} else if (choice.value == "고성군") {
			moveLatLon = new kakao.maps.LatLng(38.379788, 128.419437);
			map.panTo(moveLatLon);
		} else if (choice.value == "동해시") {
			moveLatLon = new kakao.maps.LatLng(37.528749, 129.110930);
			map.panTo(moveLatLon);
		} else if (choice.value == "삼척시") {
			moveLatLon = new kakao.maps.LatLng(37.441092, 129.156696);
			map.panTo(moveLatLon);
		} else if (choice.value == "속초시") {
			moveLatLon = new kakao.maps.LatLng(38.204510, 128.585681);
			map.panTo(moveLatLon);
		} else if (choice.value == "양구군") {
			moveLatLon = new kakao.maps.LatLng(38.208728, 127.993183);
			map.panTo(moveLatLon);
		} else if (choice.value == "양양군") {
			moveLatLon = new kakao.maps.LatLng(38.029129, 128.652460);
			map.panTo(moveLatLon);
		} else if (choice.value == "영월군") {
			moveLatLon = new kakao.maps.LatLng(37.189904, 128.534497);
			map.panTo(moveLatLon);
		} else if (choice.value == "원주시") {
			moveLatLon = new kakao.maps.LatLng(37.343975, 127.943686);
			map.panTo(moveLatLon);
		} else if (choice.value == "인제군") {
			moveLatLon = new kakao.maps.LatLng(38.051585, 128.252952);
			map.panTo(moveLatLon);
		} else if (choice.value == "정선군") {
			moveLatLon = new kakao.maps.LatLng(37.382795, 128.750760);
			map.panTo(moveLatLon);
		} else if (choice.value == "철원군") {
			moveLatLon = new kakao.maps.LatLng(38.258162, 127.426549);
			map.panTo(moveLatLon);
		} else if (choice.value == "춘천시") {
			moveLatLon = new kakao.maps.LatLng(37.875028, 127.735611);
			map.panTo(moveLatLon);
		} else if (choice.value == "태백시") {
			moveLatLon = new kakao.maps.LatLng(37.171736, 128.989150);
			map.panTo(moveLatLon);
		} else if (choice.value == "평창군") {
			moveLatLon = new kakao.maps.LatLng(37.557441, 128.473504);
			map.panTo(moveLatLon);
		} else if (choice.value == "화천군") {
			moveLatLon = new kakao.maps.LatLng(38.138600, 127.677002);
			map.panTo(moveLatLon);
		} else if (choice.value == "홍천군") {
			moveLatLon = new kakao.maps.LatLng(37.753212, 128.073427);
			map.panTo(moveLatLon);
		} else if (choice.value == "횡성군") {
			moveLatLon = new kakao.maps.LatLng(37.515053, 128.073563);
			map.panTo(moveLatLon);
		}
	}
	
	// 충북
	if (city.value == "northcc") {
		if (choice.value == "괴산군") {
			moveLatLon = new kakao.maps.LatLng(36.767894, 127.831152);
			map.panTo(moveLatLon);
		} else if (choice.value == "단양군") {
			moveLatLon = new kakao.maps.LatLng(36.986143, 128.391592);
			map.panTo(moveLatLon);
		} else if (choice.value == "보은군") {
			moveLatLon = new kakao.maps.LatLng(36.494044, 127.713966);
			map.panTo(moveLatLon);
		} else if (choice.value == "영동군") {
			moveLatLon = new kakao.maps.LatLng(36.162458, 127.813076);
			map.panTo(moveLatLon);
		} else if (choice.value == "옥천군") {
			moveLatLon = new kakao.maps.LatLng(36.326782, 127.664088);
			map.panTo(moveLatLon);
		} else if (choice.value == "음성군") {
			moveLatLon = new kakao.maps.LatLng(36.979903, 127.613385);
			map.panTo(moveLatLon);
		} else if (choice.value == "제천시") {
			moveLatLon = new kakao.maps.LatLng(37.141776, 128.205204);
			map.panTo(moveLatLon);
		} else if (choice.value == "증평군") {
			moveLatLon = new kakao.maps.LatLng(36.787057, 127.601263);
			map.panTo(moveLatLon);
		} else if (choice.value == "진천군") {
			moveLatLon = new kakao.maps.LatLng(36.874374, 127.442299);
			map.panTo(moveLatLon);
		} else if (choice.value == "청주시") {
			moveLatLon = new kakao.maps.LatLng(36.627283, 127.497505);
			map.panTo(moveLatLon);
		} else if (choice.value == "충주시") {
			moveLatLon = new kakao.maps.LatLng(36.991264, 127.922368);
			map.panTo(moveLatLon);
		}
	}
	
	// 충남
	if (city.value == "southcc"){
		if (choice.value == "계룡시") {
			moveLatLon = new kakao.maps.LatLng(36.274494, 127.256256);
			map.panTo(moveLatLon);
		} else if (choice.value == "공주시") {
			moveLatLon = new kakao.maps.LatLng(36.461016, 127.128284);
			map.panTo(moveLatLon);
		} else if (choice.value == "금산군") {
			moveLatLon = new kakao.maps.LatLng(36.117448, 127.482600);
			map.panTo(moveLatLon);
		} else if (choice.value == "논산시") {
			moveLatLon = new kakao.maps.LatLng(36.204195, 127.092747);
			map.panTo(moveLatLon);
		} else if (choice.value == "당진시") {
			moveLatLon = new kakao.maps.LatLng(36.909125, 126.648629);
			map.panTo(moveLatLon);
		} else if (choice.value == "보령시") {
			moveLatLon = new kakao.maps.LatLng(36.348418, 126.599366);
			map.panTo(moveLatLon);
		} else if (choice.value == "부여군") {
			moveLatLon = new kakao.maps.LatLng(36.247183, 126.855215);
			map.panTo(moveLatLon);
		} else if (choice.value == "서산시") {
			moveLatLon = new kakao.maps.LatLng(36.784707, 126.450034);
			map.panTo(moveLatLon);
		} else if (choice.value == "서천군") {
			moveLatLon = new kakao.maps.LatLng(36.081464, 126.693153);
			map.panTo(moveLatLon);
		} else if (choice.value == "아산시") {
			moveLatLon = new kakao.maps.LatLng(36.787944, 127.002016);
			map.panTo(moveLatLon);
		} else if (choice.value == "예산군") {
			moveLatLon = new kakao.maps.LatLng(36.673224, 126.781299);
			map.panTo(moveLatLon);
		} else if (choice.value == "천안시") {
			moveLatLon = new kakao.maps.LatLng(36.810925, 127.197201);
			map.panTo(moveLatLon);
		} else if (choice.value == "청양군") {
			moveLatLon = new kakao.maps.LatLng(36.424801, 126.853212);
			map.panTo(moveLatLon);
		} else if (choice.value == "태안군") {
			moveLatLon = new kakao.maps.LatLng(36.747710, 126.303051);
			map.panTo(moveLatLon);
		} else if (choice.value == "홍성군") {
			moveLatLon = new kakao.maps.LatLng(36.569479, 126.607456);
			map.panTo(moveLatLon);
		}
	}
	
	// 전북
	if (city.value == "northjl") {
		if (choice.value == "고창군") {
			moveLatLon = new kakao.maps.LatLng(35.434998, 126.634018);
			map.panTo(moveLatLon);
		} else if (choice.value == "군산시") {
			moveLatLon = new kakao.maps.LatLng(35.968608, 126.736795);
			map.panTo(moveLatLon);
		} else if (choice.value == "김제시") {
			moveLatLon = new kakao.maps.LatLng(35.801389, 126.892036);
			map.panTo(moveLatLon);
		} else if (choice.value == "남원시") {
			moveLatLon = new kakao.maps.LatLng(35.410437, 127.391412);
			map.panTo(moveLatLon);
		} else if (choice.value == "무주군") {
			moveLatLon = new kakao.maps.LatLng(35.937893, 127.714167);
			map.panTo(moveLatLon);
		} else if (choice.value == "부안군") {
			moveLatLon = new kakao.maps.LatLng(35.696216, 126.664291);
			map.panTo(moveLatLon);
		} else if (choice.value == "순창군") {
			moveLatLon = new kakao.maps.LatLng(35.434325, 127.093435);
			map.panTo(moveLatLon);
		} else if (choice.value == "완주군") {
			moveLatLon = new kakao.maps.LatLng(35.929704, 127.204564);
			map.panTo(moveLatLon);
		} else if (choice.value == "익산시") {
			moveLatLon = new kakao.maps.LatLng(35.955300, 126.985279);
			map.panTo(moveLatLon);
		} else if (choice.value == "임실군") {
			moveLatLon = new kakao.maps.LatLng(35.597661, 127.239510);
			map.panTo(moveLatLon);
		} else if (choice.value == "장수군") {
			moveLatLon = new kakao.maps.LatLng(35.660921, 127.547358);
			map.panTo(moveLatLon);
		} else if (choice.value == "전주시") {
			moveLatLon = new kakao.maps.LatLng(35.827150, 127.114773);
			map.panTo(moveLatLon);
		} else if (choice.value == "정읍시") {
			moveLatLon = new kakao.maps.LatLng(35.578827, 126.859549);
			map.panTo(moveLatLon);
		} else if (choice.value == "진안군") {
			moveLatLon = new kakao.maps.LatLng(35.830037, 127.426782);
			map.panTo(moveLatLon);
		}
	}
	
	// 전남
	if (city.value == "southjl") {
		if (choice.value == "강진군") {
			moveLatLon = new kakao.maps.LatLng(34.611670, 126.779448);
			map.panTo(moveLatLon);
		} else if (choice.value == "고흥군") {
			moveLatLon = new kakao.maps.LatLng(34.612963, 127.308651);
			map.panTo(moveLatLon);
		} else if (choice.value == "곡성군") {
			moveLatLon = new kakao.maps.LatLng(35.217748, 127.255685);
			map.panTo(moveLatLon);
		} else if (choice.value == "광양시") {
			moveLatLon = new kakao.maps.LatLng(34.942094, 127.705858);
			map.panTo(moveLatLon);
		} else if (choice.value == "구례군") {
			moveLatLon = new kakao.maps.LatLng(35.231989, 127.497142);
			map.panTo(moveLatLon);
		} else if (choice.value == "나주시") {
			moveLatLon = new kakao.maps.LatLng(34.989701, 126.721806);
			map.panTo(moveLatLon);
		} else if (choice.value == "담양군") {
			moveLatLon = new kakao.maps.LatLng(35.296268, 126.987177);
			map.panTo(moveLatLon);
		} else if (choice.value == "목포시") {
			moveLatLon = new kakao.maps.LatLng(34.810391, 126.412299);
			map.panTo(moveLatLon);
		} else if (choice.value == "무안군") {
			moveLatLon = new kakao.maps.LatLng(34.987446, 126.460716);
			map.panTo(moveLatLon);
		} else if (choice.value == "보성군") {
			moveLatLon = new kakao.maps.LatLng(34.799177, 127.195706);
			map.panTo(moveLatLon);
		} else if (choice.value == "순천시") {
			moveLatLon = new kakao.maps.LatLng(34.951443, 127.521547);
			map.panTo(moveLatLon);
		} else if (choice.value == "신안군") {
			moveLatLon = new kakao.maps.LatLng(34.837396, 126.086770);
			map.panTo(moveLatLon);
		} else if (choice.value == "여수시") {
			moveLatLon = new kakao.maps.LatLng(34.753326, 127.703862);
			map.panTo(moveLatLon);
		} else if (choice.value == "영광군") {
			moveLatLon = new kakao.maps.LatLng(35.274805, 126.488240);
			map.panTo(moveLatLon);
		} else if (choice.value == "영암군") {
			moveLatLon = new kakao.maps.LatLng(34.779779, 126.649790);
			map.panTo(moveLatLon);
		} else if (choice.value == "완도군") {
			moveLatLon = new kakao.maps.LatLng(34.304045, 126.742067);
			map.panTo(moveLatLon);
		} else if (choice.value == "장성군") {
			moveLatLon = new kakao.maps.LatLng(35.331787, 126.777003);
			map.panTo(moveLatLon);
		} else if (choice.value == "장흥군") {
			moveLatLon = new kakao.maps.LatLng(34.687053, 126.909370);
			map.panTo(moveLatLon);
		} else if (choice.value == "진도군") {
			moveLatLon = new kakao.maps.LatLng(34.481802, 126.247397);
			map.panTo(moveLatLon);
		} else if (choice.value == "함평군") {
			moveLatLon = new kakao.maps.LatLng(35.105483, 126.525202);
			map.panTo(moveLatLon);
		} else if (choice.value == "해남군") {
			moveLatLon = new kakao.maps.LatLng(34.572897, 126.596575);
			map.panTo(moveLatLon);
		} else if (choice.value == "화순군") {
			moveLatLon = new kakao.maps.LatLng(35.008463, 127.034806);
			map.panTo(moveLatLon);
		}
	}
	
	// 경북
	if (city.value == "northgs"){
		if (choice.value == "경산시") {
			moveLatLon = new kakao.maps.LatLng(35.825985, 128.768435);
			map.panTo(moveLatLon);
		} else if (choice.value == "경주시") {
			moveLatLon = new kakao.maps.LatLng(35.859780, 129.211899);
			map.panTo(moveLatLon);
		} else if (choice.value == "고령군") {
			moveLatLon = new kakao.maps.LatLng(35.740723, 128.313525);
			map.panTo(moveLatLon);
		} else if (choice.value == "구미시") {
			moveLatLon = new kakao.maps.LatLng(36.120326, 128.378459);
			map.panTo(moveLatLon);
		} else if (choice.value == "군위군") {
			moveLatLon = new kakao.maps.LatLng(36.172568, 128.646990);
			map.panTo(moveLatLon);
		} else if (choice.value == "김천시") {
			moveLatLon = new kakao.maps.LatLng(36.135656, 128.118405);
			map.panTo(moveLatLon);
		} else if (choice.value == "문경시") {
			moveLatLon = new kakao.maps.LatLng(36.595835, 128.198690);
			map.panTo(moveLatLon);
		} else if (choice.value == "봉화군") {
			moveLatLon = new kakao.maps.LatLng(36.931082, 128.914679);
			map.panTo(moveLatLon);
		} else if (choice.value == "상주시") {
			moveLatLon = new kakao.maps.LatLng(36.420832, 128.165655);
			map.panTo(moveLatLon);
		} else if (choice.value == "성주군") {
			moveLatLon = new kakao.maps.LatLng(35.908219, 128.233011);
			map.panTo(moveLatLon);
		} else if (choice.value == "안동시") {
			moveLatLon = new kakao.maps.LatLng(36.567092, 128.726109);
			map.panTo(moveLatLon);
		} else if (choice.value == "영덕군") {
			moveLatLon = new kakao.maps.LatLng(36.465561, 129.383015);
			map.panTo(moveLatLon);
		} else if (choice.value == "영양군") {
			moveLatLon = new kakao.maps.LatLng(36.694024, 129.149158);
			map.panTo(moveLatLon);
		} else if (choice.value == "영주시") {
			moveLatLon = new kakao.maps.LatLng(36.817244, 128.620286);
			map.panTo(moveLatLon);
		} else if (choice.value == "영천시") {
			moveLatLon = new kakao.maps.LatLng(35.975690, 128.942744);
			map.panTo(moveLatLon);
		} else if (choice.value == "예천군") {
			moveLatLon = new kakao.maps.LatLng(36.657120, 128.424283);
			map.panTo(moveLatLon);
		} else if (choice.value == "울릉군") {
			moveLatLon = new kakao.maps.LatLng(37.492135, 130.880012);
			map.panTo(moveLatLon);
		} else if (choice.value == "울진군") {
			moveLatLon = new kakao.maps.LatLng(36.894611, 129.385988);
			map.panTo(moveLatLon);
		} else if (choice.value == "의성군") {
			moveLatLon = new kakao.maps.LatLng(36.366193, 128.618392);
			map.panTo(moveLatLon);
		} else if (choice.value == "청도군") {
			moveLatLon = new kakao.maps.LatLng(35.677063, 128.785977);
			map.panTo(moveLatLon);
		} else if (choice.value == "청송군") {
			moveLatLon = new kakao.maps.LatLng(36.359440, 129.059686);
			map.panTo(moveLatLon);
		} else if (choice.value == "칠곡군") {
			moveLatLon = new kakao.maps.LatLng(36.018045, 128.460070);
			map.panTo(moveLatLon);
		} else if (choice.value == "포항시") {
			moveLatLon = new kakao.maps.LatLng(36.035323, 129.369583);
			map.panTo(moveLatLon);
		}
	}
	
	// 경남
	if (city.value == "southgs") {
		if (choice.value == "거제시") {
		moveLatLon = new kakao.maps.LatLng(34.880916, 128.621037);
		map.panTo(moveLatLon);
	} else if (choice.value == "거창군") {
		moveLatLon = new kakao.maps.LatLng(35.733109, 127.914010);
		map.panTo(moveLatLon);
	} else if (choice.value == "고성군") {
		moveLatLon = new kakao.maps.LatLng(34.995654, 128.294136);
		map.panTo(moveLatLon);
	} else if (choice.value == "김해시") {
		moveLatLon = new kakao.maps.LatLng(35.244846, 128.880216);
		map.panTo(moveLatLon);
	} else if (choice.value == "남해군") {
		moveLatLon = new kakao.maps.LatLng(34.831737, 127.871060);
		map.panTo(moveLatLon);
	} else if (choice.value == "밀양시") {
		moveLatLon = new kakao.maps.LatLng(35.493393, 128.760699);
		map.panTo(moveLatLon);
	} else if (choice.value == "사천시") {
		moveLatLon = new kakao.maps.LatLng(34.989734, 128.087275);
		map.panTo(moveLatLon);
	} else if (choice.value == "산청군") {
		moveLatLon = new kakao.maps.LatLng(35.377824, 127.892500);
		map.panTo(moveLatLon);
	} else if (choice.value == "양산시") {
		moveLatLon = new kakao.maps.LatLng(35.328736, 129.030981);
		map.panTo(moveLatLon);
	} else if (choice.value == "의령군") {
		moveLatLon = new kakao.maps.LatLng(35.410659, 128.282667);
		map.panTo(moveLatLon);
	} else if (choice.value == "진주시") {
		moveLatLon = new kakao.maps.LatLng(35.180827, 128.118685);
		map.panTo(moveLatLon);
	} else if (choice.value == "창녕군") {
		moveLatLon = new kakao.maps.LatLng(35.511939, 128.494088);
		map.panTo(moveLatLon);
	} else if (choice.value == "창원시") {
		moveLatLon = new kakao.maps.LatLng(35.229105, 128.647311);
		map.panTo(moveLatLon);
	} else if (choice.value == "통영시") {
		moveLatLon = new kakao.maps.LatLng(34.854058, 128.430762);
		map.panTo(moveLatLon);
	} else if (choice.value == "하동군") {
		moveLatLon = new kakao.maps.LatLng(35.125948, 127.789669);
		map.panTo(moveLatLon);
	} else if (choice.value == "함안군") {
		moveLatLon = new kakao.maps.LatLng(35.293035, 128.426827);
		map.panTo(moveLatLon);
	} else if (choice.value == "함양군") {
		moveLatLon = new kakao.maps.LatLng(35.546838, 127.721236);
		map.panTo(moveLatLon);
	} else if (choice.value == "합천군") {
		moveLatLon = new kakao.maps.LatLng(35.570334, 128.149017);
		map.panTo(moveLatLon);
	}
		
	}
	
	// 제주
	if (city.value == "jeju") {
		if (choice.value == "제주시") {
			moveLatLon = new kakao.maps.LatLng(33.498105, 126.531026);
			map.panTo(moveLatLon);
		} else if (choice.value == "서귀포시") {
			moveLatLon = new kakao.maps.LatLng(33.254039, 126.560419);
			map.panTo(moveLatLon);
		}
	}
	
	

	}
}
