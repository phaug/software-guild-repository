$(document).ready(function () {
    $("#akronInfoDiv").hide();
    $("#minneapolisInfoDiv").hide();
    $("#louisvilleInfoDiv").hide();
    $("#akronWeather").hide();
    $("#minneapolisWeather").hide();
    $("#louisvilleWeather").hide();
    $("#minneapolisButton").on('click', function(){
      $("#minneapolisInfoDiv").show();
    })
    $("#minneapolisWeatherButton").click(function(){
      $("#minneapolisWeather").toggle();
    })
    $("#akronButton").on('click', function(){
      $("#akronInfoDiv").show();
    })
    $("#akronWeatherButton").click(function(){
      $("#akronWeather").toggle();
    })
    $("#louisvilleButton").on('click', function(){
      $("#louisvilleInfoDiv").show();
    })
    $("#louisvilleWeatherButton").click(function(){
      $("#louisvilleWeather").toggle();
    });
    $("tr:not(:first)").hover(
    function () {
        $(this).css("background-color", "WhiteSmoke");
    },
    function () {
        $(this).css("background-color", "");
    }
);
});
