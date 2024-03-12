<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="com.lmc.admin.model.vo.AdminPageInfo" %>
 
<%
	AdminPageInfo api = (AdminPageInfo)request.getAttribute("api");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>

    <!-- 차트 Resources -->
    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>

    <style>

        div,form {
            box-sizing: border-box;
            border: px solid blue;
        }
        
        td { font-family: 'KimjungchulGothic' }

        /* 콘텐츠의 공통속성 */
        #content { 
            width: auto; /*총 너비(1200px)의 80% = 960px */
            margin-left: 5%;
            margin-right: 5%;
            min-height: 800px;
            background-color: rgba(255, 255, 255, 0.829)
        }
        
        #content>div{ float: left;}
        
        /*우측 관리자 메뉴 크기*/
        #content_2 { 

            width: 860px;
            height: auto; 
            margin-top: 30px;
            
        }
        
        #dashboard { height: 155px;}
        
        #dashboard > div { 
            height: 100%;
            width: 33.33333333%;
            float: left;
        }

        /* dashboard 스타일 */
        
        .dashboard_box { display: flex; }   /* 데시보드 내용 중앙 정렬 위해 각 박스에 flex 기준 부여 */
        .dashboard_box>div { width: 250px; height: 100px; border-radius: 11px; display: flex; margin: auto;}
        
        /* dashboard 데쉬보드 테두리 */
        .dashboard_box>div>div { 
            background-color: white; 
            width: 255px; 
            border-radius: 10px; 
            margin-left: 5px; 
            display: flex; 
            
        }
        
        .dashboard_box>div>div>div { height: 45px; width:80%; margin: auto; }
        
        .dashboard_name { font-size: 13px; font-weight: bolder; font-family: 'KimjungchulGothic';}
        .h5 { color: dimgrey;}
        

        /* 차트 스타일 */
        #chart { height: 535px; display: flex; padding: 10px;}
        
        #chart > div {
            height: 100%;
            width: 50%;
            float: left;
            margin: 10px;
            background-color: white;
        }
        

        #chartdiv {
            margin: auto;
            width: 80%;
            height: 80%;
        }

        #chartdiv2 {
            margin: auto;
            width: 80%;
            height: 80%;
        }

        </style>
</head>

<body>
	 
	<%@ include file="../common/menubar.jsp" %>
 
 	<%
		String memberId 	=  loginUser.getMemberId();
		String memberName 	=  loginUser.getMemberName();
		String memberNick 	= (loginUser.getMemberNick() == null) 	? "" : loginUser.getMemberNick();
		String email 		= (loginUser.getEmail() == null) 	? "" : loginUser.getEmail();
		String enrollDate 	= (loginUser.getEnrollDate() == null) 	? "" : loginUser.getEnrollDate().toString();
		String memberImg 	= (loginUser.getMemberImg() == null) ? "/resources/images/pngwing.com.png" : loginUser.getMemberImg();
	%>
    <div class="wrap">
        
        <div id="content">
        
        	<%@ include file="../common/adminMenu.jsp" %>

            <div id="content_2">
                
                <h2 style="font-family: 'KimjungchulGothic'; margin-left: 20px;"> 관리자 페이지</h2>

                <div id="dashboard" >

                    <div class="dashboard_box" id="totalUser" style="display: flex;">
                        <div class="shadow" style="background-color: mediumblue;"  > 
                            
                            <div>
                                <div>
                                    <div class="dashboard_name" style="color: mediumblue;">
                                        총 회원수
                                    </div>
                                    <div class="h5 font-weight-bold" id="totalMember">
                                        100 명
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="dashboard_box" id="todayBoard">
                        <div class="shadow" style="background-color: teal;">
                           
                            <div>
                                
                                <div>
                                    <div class="dashboard_name" style="color: teal;">
                                        금일 작성글 수
                                    </div>
                                    <div class="h5 font-weight-bold" id="todayWrite"> 
                                        100 건
                                    </div>
                                </div>

                            </div>


                        </div>
                    </div>
                    <div class="dashboard_box" id="totalBoard">
                        <div class="shadow" style="background-color: darkorange;">
                            
                            <div>
                                
                                <div>
                                    <div class="dashboard_name" style="color: darkorange;">
                                        누적 작성글 수
                                    </div>
                                    <div class="h5 font-weight-bold" id="totalWrite">
                                        100 건
                                    </div>
                                </div>

                            </div>


                        </div>
                    </div>
                </div>
                

                <div id="chart">
                    <div id="chartwrap1" class="shadow">
                        
                        <div style="width: 100%; padding: 10px;">
                            <table style="text-align: right; width: 100%"";>
                                <tr>
                                    <td>일일 게시글 작성현황</td>
                                </tr>
                                <tr>
                                    <td class="chart1_title" style="font-size: 10px; color: grey;"></td>
                                </tr>
                            </table>
                        </div>
                        <script>
                            
                            var today = new Date();
                            var year = today.getFullYear().toString().substr(2, 4);
                            var month = today.getMonth()+1;
                            var day = today.getDate();
                            var hours = today.getHours();
                            var minutes = today.getMinutes();
                            

                            var now = year + "년 " + month +"월 " + day + "일 " + hours + "시 " + minutes + "분 기준";
                            $(function(){
    
                                $(".chart1_title").append(now);
    
                            })
                            
                        </script>
                            
                        <div id="chartdiv"></div>
                        
                    </div>
                    
                    <div id="chartwrap2" class="shadow" >
                        <div style="width: 100%; padding: 10px;">
                            <table style="text-align: right; width: 100%"";>
                                <tr>
                                    <td>게시글 작성현황 (%)</td>
                                </tr>
                                <tr>
                                    <td class="chart1_title" style="font-size: 10px; color: grey;"></td>
                                </tr>
                            </table>
                        </div>
                        <div id="chartdiv2"></div>
                        
                    </div>
                </div>
                
            </div>
            
        </div>
        
		<%@ include file="../common/footer.jsp" %>
		
    </div>
    
    <!-- 접속량 현황 -->
    <script>
	
 	// Create root element
    // https://www.amcharts.com/docs/v5/getting-started/#Root_element
  
  	var root1 = am5.Root.new("chartdiv"); 
    var root2 = am5.Root.new("chartdiv2");
    
	  $(document).ready(function(){

		  	
	        
	        adminChart();
	        // setInterval(adminChart, 5000);
	        // setTimeout(adminchart.init(), 5000);
	    })
	    
	        
	    function adminChart() {

	    
	    $.ajax({
	        url : "adminPage.ad",
	        type : "post",
	        success : function(result) {
	            $("#totalMember").text(result["totalMember"] + ' 명');
	            $("#todayWrite").text(result["todayBoardCount"] + ' 건');
	            $("#totalWrite").text(result["totalWrite"] + ' 건');
	
	            let todayFreeBoardCount = result["todayFreeBoardCount"];
	            let todayShareBoardCount = result["todayShareBoardCount"];
	            let todayMovieInfoBoardCount = result["todayMovieInfoBoardCount"];
	            let todayNoticeBoardCount = result["todayNoticeBoardCount"];
	            let freeBoard = result["freeBoard"];
	            let shareBoard = result["shareBoard"];
	            let movieInfoBoard = result["movieInfoBoard"];
	            let noticeBoard = result["noticeBoard"];
	
	           // console.log(todayFreeBoardCount);
	
	
	    // 여기서 부터 처번째 차트
	    am5.ready(function() {
	    

			// Create root element
			    // https://www.amcharts.com/docs/v5/getting-started/#Root_element
			    // var root = am5.Root.new("chartdiv");
			    
			    // Set themes
			    // https://www.amcharts.com/docs/v5/concepts/themes/
			    root1.setThemes([
			      am5themes_Animated.new(root1)
			    ]);
			    
			    // Create chart
			    // https://www.amcharts.com/docs/v5/charts/xy-chart/
			    var chart = root1.container.children.push(am5xy.XYChart.new(root1, {
			      panX: true,
			      panY: true,
			      wheelX: "panX",
			      wheelY: "zoomX",
			      pinchZoomX: true }
		    	));
		    
			    // Add cursor
			    // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
			    var cursor = chart.set("cursor", am5xy.XYCursor.new(root1, {}));
			    cursor.lineY.set("visible", false);
		    
		    
			    // Create axes
			    // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
			    var xRenderer = am5xy.AxisRendererX.new(root1, { minGridDistance: 30 });
			    xRenderer.labels.template.setAll({
			      rotation: 0,
			      centerY: am5.p50,
			      centerX: am5.p100,
			      paddingRight: -13
			    });
		    
			    xRenderer.grid.template.setAll({
			      location: 1
			    });
		    
			    var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root1, {
			      maxDeviation: 0.3,
			      categoryField: "column",
			      renderer: xRenderer,
			      tooltip: am5.Tooltip.new(root1, {})
			    }));
		    
			    var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root1, {
			      maxDeviation: 0.3,
			      renderer: am5xy.AxisRendererY.new(root1, {
			        strokeOpacity: 0.1
			      })
			    }));
		    
		    
			    // Create series
			    // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
			    var series = chart.series.push(am5xy.ColumnSeries.new(root1, {
			      name: "Series 1",
			      xAxis: xAxis,
			      yAxis: yAxis,
			      valueYField: "value",
			      sequencedInterpolation: true,
			      categoryXField: "column",
			      tooltip: am5.Tooltip.new(root1, {
			        labelText: "{valueY}"
			      })
			    }));
		    
			    series.columns.template.setAll({ cornerRadiusTL: 5, cornerRadiusTR: 5, strokeOpacity: 0 });
			    series.columns.template.adapters.add("fill", function(fill, target) {
			      return chart.get("colors").getIndex(series.columns.indexOf(target));
			    });
		    
			    series.columns.template.adapters.add("stroke", function(stroke, target) {
			      return chart.get("colors").getIndex(series.columns.indexOf(target));
			    });
			  
	    
		    ////////////////////////
		    // 차트1 데이터 입력 부분
		    ////////////////////////
		    
		    var data = [{
		        column: ("자유"),
		        value: todayFreeBoardCount
		    }, {
		        
		        column: "나눔",
		      value: todayShareBoardCount
		    }, {
		        column: "정보",
		      value: todayMovieInfoBoardCount
		    }, {
		        column: "공지",
		      value: todayNoticeBoardCount
		    }];
	    
		    xAxis.data.setAll(data);
		    series.data.setAll(data);
	    
		    // Make stuff animate on load
		    // https://www.amcharts.com/docs/v5/concepts/animations/
		    series.appear(1000);
		    chart.appear(1000, 100);
	    
	    }); // end am5.ready()
	   
	    
	    // 아래서 부터 두번째 차트
	    am5.ready(function() {
	    
		    // Create root element
		    // https://www.amcharts.com/docs/v5/getting-started/#Root_element
		    // var root = am5.Root.new("chartdiv2");
		    
		    
		    // Set themes
		    // https://www.amcharts.com/docs/v5/concepts/themes/
		    root2.setThemes([
		        am5themes_Animated.new(root2)
		    ]);
		    
		    
		    // Create chart
		    // https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
		    var chart = root2.container.children.push(am5percent.PieChart.new(root2, {
		        layout: root2.verticalLayout,
		        innerRadius: am5.percent(50)
		    }));
		    
		    
		    // Create series
		    // https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
		    var series = chart.series.push(am5percent.PieSeries.new(root2, {
		        valueField: "value",
		        categoryField: "category",
		        alignLabels: false
		    }));
		    
		    series.labels.template.setAll({
		        textType: "circular",
		        centerX: 0,
		        centerY: 0
		    });
		    
		    
		    // Set data
		    // https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
		    series.data.setAll([
		        { value: freeBoard, category: "자유" },
		        { value: shareBoard, category: "나눔" },
		        { value: movieInfoBoard, category: "정보" },
		        { value: noticeBoard, category: "공지" },
		
		    ]);
		    
		    
		    // Create legend
		    // https://www.amcharts.com/docs/v5/charts/percent-charts/legend-percent-series/
		    var legend = chart.children.push(am5.Legend.new(root2, {
		        centerX: am5.percent(50),
		        x: am5.percent(50),
		        marginTop: 15,
		        marginBottom: 15,
		    }));
		    
		    legend.data.setAll(series.dataItems);
		    
		    
		    // Play initial series animation
		    // https://www.amcharts.com/docs/v5/concepts/animations/#Animation_of_series
		    series.appear(1000, 100);
	    
	    }); // end am5.ready()
	
	        },
	        error : function(){
	        	console.log("ajax 통신 실패!");
	            }
	        }); // ajax 끝
	    }    
    </script>
    
  
</body>
</html>