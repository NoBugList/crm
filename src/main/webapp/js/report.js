// 客户贡献查询
function searchKhgxfx(){
    $("#dg").datagrid('load',{
        "customerName":$("#s_customerName").val()
    });
}

// 客户流程
function searchCustomerLoss(){
    $("#dg").datagrid('load',{
        "cusName":$("#s_cusName").val(),
        "cusManager":$("#s_cusManager").val()
    });
}

function findCustomerGc() {
	var chart = new Highcharts.Chart({
		chart: {
			renderTo: 'container',
			type: 'column',
			events:{
                load: function(event) {
                    // ajax请求后台加载数据
                    $.post("khgcfx", {}, function(result) {
                        var xArr = new Array();
                        var yArr = new Array();
                        for(var i = 0;i< result.length; i++) {
                            xArr.push(result[i].level);
                            yArr.push(result[i].amount);
                            chart.xAxis[0].categories=xArr;
                            chart.series[0].setData(yArr);
                        }
                    });
                }
            }
		}, 
		title: {
			text: '客户构成分析'
		},
		xAxis: {
			categories: [
			             '合作伙伴',
			             '大客户',
			             '战略合作伙伴',
			             '普通客户',
			             '重点开发客户'
			             ],
			             crosshair: true
		},
		yAxis: {
			min: 0,
			title: {
				text: '客户数量'
			}
		},
		tooltip: {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">数量: </td>' +
			'<td style="padding:0"><b>{point.y:.1f} 个</b></td></tr>',
			footerFormat: '</table>',
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
		},
		series: [{
			name: '客户',
			data: [49.9, 71.5, 106.4, 129.2, 144.0]

		}]
	});
}

// 客户服务分析
function findCustomerFw() {
    var chart=new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            events:{
                load:function(event) {
                    var series=this.series[0];
                    // ajax请求后台加载数据
                    $.post("khfwfx", {}, function(result) {
                        var xArr = new Array();
                        for(var i=0; i < result.length; i++) {
                            xArr[i] = new Array();
                            xArr[i][0] = result[i].serveType;
                            xArr[i][1] = result[i].amount;
                        }
                        console.log(JSON.stringify(xArr));
                        series.setData(xArr);
                    },"json");
                }
            }
        },
        title: {
            text: '客服服务分析'
        },
        tooltip: {
            formatter:function(){
                return '<b>'+this.point.name+'</b>:'+Highcharts.numberFormat(this.percentage,1)+'% ('+this.y+'个)'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data: [
                   
            ]
        }]
    });
}