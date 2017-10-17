$(document).ready(function () {
    $('#firstDiv').append('<h1> Good Morning! </h1>');
    $('#displayDay').on('click', function(){
    $('#today').toggle('slow');
  });
    $('#firstDiv').hover(function(){
      $(this).css('background-color', 'CornflowerBlue');
    },
    function(){
      $(this).css('background-color', '');
    }
  );
});
