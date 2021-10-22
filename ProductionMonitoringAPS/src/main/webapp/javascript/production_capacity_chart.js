


var xValues = ${production_capacity_by_weeks_xValue};
var yValues = ${production_capacity_by_weeks_yValue};
var yValuesLine = ${production_available_time_by_week};
var barColors = ${production_capacity_by_weeks_bar_color};

var mixedChart = new Chart("myChart", {
	type : 'bar',
	data : {
		datasets : [ {
			label : 'Requirement time human/hours',
			data : yValues,
			backgroundColor : barColors,
			// this dataset is drawn below
			order : 1
		}, {
			label : 'Available time human/hours',
			data : yValuesLine,
			fill : true,
			borderColor : "red",
			backgroundColor : "orange",
			type : 'line',
			// this dataset is drawn on top
			order : 2
		} ],
		
		labels : xValues
	},
	options : {
		legend : {
			display : true
		},
		title : {
			display : true,
			text : "${production_capacity_by_weeks_year}"
		}
	}
});

