//home.js
$(document).ready(function() {
  $('#toggleNumbers').on('click', function(){
    $('h2').toggle('slow');
  });

  $('#centerUp').on("click", function(){
    $('h1').addClass('text-center');
    $('h2').addClass('text-center');
    $('#buttonDiv').addClass('text-center');
  })

  $('#headingsBlue').on('click', function(){
    $('h1').css("color", 'blue');
  })

  $('div').hover(
    //in function
    function(){
      $(this).css('background-color', 'CornflowerBlue');
    },
    //out funtion
    function(){
      $(this).css('background-color', '');
    }
  );

  $('h2').hover(
    function(){
      $(this).css('color', 'DarkOrange');
    },
    function(){
      $(this).css('color', '');
    }
  );

  $('#mainHeading').hover(
    function(){
      $(this).css('color', 'red');
    },
    function(){
      $(this).css('color', 'pink');
    }
  );
});
