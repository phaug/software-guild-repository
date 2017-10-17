$(document).ready(function () {
  const API='&APPID=02a9a2bc74d1b3b371b6347ed714cc58';
$.ajax({
  type: "GET",
url: "http://api.openweathermap.org/data/2.5/weather?zip=55423,us&APPID=02a9a2bc74d1b3b371b6347ed714cc58",
success: function (forecasterArray) {

    var forecast = $("#currentWeather");

    $.each(forecastArray, function (index, forecast) {
        var forecastInfo = "<p>";
        //forecastInfo += "Haze:" + forecast.haze + "<br />";
        forecastInfo += "temp:" + forecast.temperature + "<br />";
        forecastInfo += "humidity:" + forecast.humidity + "<br />";
        forecastInfo += "wind:" + forecast.wind + "<br />";
        forecastInfo += "</p>";
        forecastInfo += "<hr />";

        forecastDiv.append(forecastInfo);
    });
},
error: function () {
    alert("FAILURE!");
}
});

$('#weatherButton').on('click', function() {
    $.ajax({
        type: 'GET',
        url: 'http://api.openweathermap.org/data/2.5/weather?zip=55423,us&APPID=02a9a2bc74d1b3b371b6347ed714cc58',

        success: function(contact) {
            var currentTempData = $('#tempData');

            var forecastInfo = "<p>";
            //forecastInfo += "Haze:" + forecast.haze + "<br />";
            forecastInfo += "temp:" + forecast.temperature + "<br />";
            forecastInfo += "humidity:" + forecast.humidity + "<br />";
            forecastInfo += "wind:" + forecast.wind + "<br />";
            forecastInfo += "</p>";
            forecastInfo += "<hr />";

            forecastDiv.append(forecastInfo);

                        },
                        error: function() {
                            alert('FAILURE');
                        }
                    });

                });

            })
