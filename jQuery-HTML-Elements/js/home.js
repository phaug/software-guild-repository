$(document).ready(function () {
    $('h1').addClass('text-center');
    $('h2').addClass('text-center');
    $('.myBannerHeading').addClass('page-header').removeClass('myBannerHeading');
    $("#yellowHeading").html("Yellow Team");
    $("#orangeHeading").css("background-color","orange");
    $("#blueHeading").css("background-color","blue");
    $("#yellowHeading").css("background-color","yellow");
    $("#redHeading").css("background-color", "red");
    $("#yellowTeamList").append("<li>Joseph Banks</li>");
    $("#yellowTeamList").append("<li>Simon Jones</li>");
    $('#oops').hide();
    $('#footerPlaceholder').remove();
    $('#footer').append('<p>Patrick Haug</p>').css('font-size', '24px').css('font-family', 'Courier');
    $('#footer').append('<p>patrick.haug5@gmail.com</p>').css('font-size', '24px').css('font-family', 'Courier');
});
