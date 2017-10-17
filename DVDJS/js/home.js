$(document).ready(function(){

  loaddvds();
  $('#edit-form').hide();
  $('#add-form').hide();

  // Add Button onclick handler
  $('#add-button').click(function (event) {

      // check for errors and display any that we have
      // pass the input associated with the add form to the validation function
      var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

      // if we have errors, bail out by returning false
      if (haveValidationErrors) {
          return false;
      }


              // if we made it here, there are no errors so make the ajax call
              $.ajax({
                  type: 'POST',
                  url: 'http://localhost:8080/dvds',
                  data: JSON.stringify({
                      dvdId: $('#add-id').val(),
                      title: $('#add-title').val(),
                      releaseYear: $('#add-release-year').val(),
                      director: $('#add-director').val(),
                      rating: $('#add-rating').val(),
                      notes: $('#add-notes').val()
                  }),
                  headers: {
                      'Accept': 'application/json',
                      'Content-Type': 'application/json'
                  },
                  'dataType': 'json',
                  success: function(data, status) {
                      // clear errorMessages
                      $('#errorMessages').empty();
                     // Clear the form and reload the table
                      $('add-title').val('');
                      $('#add-release-year').val('');
                      $('#add-director').val('');
                      $('#add-rating').val('');
                      loadDvds();
                  },
                  error: function() {
                      $('#errorMessages')
                         .append($('<li>')
                         .attr({class: 'list-group-item list-group-item-danger'})
                         .text('Error calling web service.  Please try again later.'));
                  }
              });
          });
          // Update Button onclick handler
          $('#edit-button').click(function (event) {

              // check for errors and display any that we have
              // pass the input associated with the edit form to the validation function
              var haveValidationErrors = checkAndDisplayValidationErrors($('#edit-form').find('input'));

              // if we have errors, bail out by returning false
              if (haveValidationErrors) {
                  return false;
              }

              // if we get to here, there were no errors, so make the Ajax call
              $.ajax({
                 type: 'PUT',
                 url: 'http://localhost:8080/contact/' + $('#edit-contact-id').val(),
                 data: JSON.stringify({
                   dvdId: $('#edit-title').val(),
                   title: $('#edit-release-year').val(),
                   director: $('#edit-last-name').val(),
                   rating: $('#edit-director').val(),
                   notes: $('#edit-rating').val()
                 }),
                 headers: {
                   'Accept': 'application/json',
                   'Content-Type': 'application/json'
                 },
                 'dataType': 'json',
                  success: function() {
                      // clear errorMessages
                      $('#errorMessages').empty();
                      hideEditForm();
                      loadContacts();
                 },
                 error: function() {
                   $('#errorMessages')
                      .append($('<li>')
                      .attr({class: 'list-group-item list-group-item-danger'})
                      .text('Error calling web service.  Please try again later.'));
                 }
             })
          });
        });

          function loaddvds() {
              // we need to clear the previous content so we don't append to it

              // grab the the tbody element that will hold the rows of contact information
              var contentRows = $('#contentRows');

              $.ajax ({
                  type: 'GET',
                  url: 'http://localhost:8080/dvds',
                  success: function (data, status) {
                      $.each(data, function (index, contact) {
                          var title = data[index].title;
                          var year = data[index].releaseYear;
                          var director = data[index].director;
                          var rating = data[index].rating;


                          var row = '<tr>';
                              row += '<td>' + title + '</td>';
                              row += '<td>' + year + '</td>';
                              row += '<td>' + director + '</td>';
                              row += '<td>' + rating + '</td>';
                              row += '</tr>';
                          contentRows.append(row);
                      });
                  },
                  error: function() {
                      $('#errorMessages')
                          .append($('<li>')
                          .attr({class: 'list-group-item list-group-item-danger'})
                          .text('Error calling web service.  Please try again later.'));
                  }
              });
              $('#dvd-list').show();
              $('#edit-form').hide();
              $('#add-form').hide();
          }
          function clearContactTable() {
              $('#contentRows').empty();
          }

          function showEditForm(dvdId) {
              // clear errorMessages
              $('#errorMessages').empty();
              // get the contact details from the server and then fill and show the
              // form on success
              $.ajax({
                  type: 'GET',
                  url: 'http://localhost:8080/dvd/' + dvdId,
                  success: function(data, status) {
                        $('#edit-title').val(data.title);
                        $('#edit-release-year').val(data.year);
                        $('#edit-director').val(data.director);
                        $('#edit-rating').val(data.rating);
                    },
                    error: function() {
                      $('#errorMessages')
                         .append($('<li>')
                         .attr({class: 'list-group-item list-group-item-danger'})
                         .text('Error calling web service.  Please try again later.'));
                    }
              });
              $('#dvd-list').hide();
              $('#add-form').hide();
              $('#edit-form').show();
          }
          function showAddForm(dvdId) {
              // clear errorMessages
              $('#errorMessages').empty();
              // get the contact details from the server and then fill and show the
              // form on success
              $.ajax({
                  type: 'GET',
                  url: 'http://localhost:8080/dvd/' + dvdId,
                  success: function(data, status) {
                        $('#add-title').val(data.title);
                        $('#add-release-year').val(data.year);
                        $('#add-director').val(data.director);
                        $('#add-rating').val(data.rating);
                    },
                    error: function() {
                      $('#errorMessages')
                         .append($('<li>')
                         .attr({class: 'list-group-item list-group-item-danger'})
                         .text('Error calling web service.  Please try again later.'));
                    }
              });
              $('#dvd-list').hide();
              $('#edit-form').hide();
              $('#add-form').show();
          }

          function hideAddForm() {
              $('#add-form').hide();
              // clear errorMessages
              $('#errorMessages').empty();
              // clear the form and then hide it
              $('#add-title').val('');
              $('#add-release-year').val('');
              $('#add-director').val('');
              $('#add-rating').val('');
          }

          function hideEditForm() {
              $('#edit-form').hide();
              // clear errorMessages
              $('#errorMessages').empty();
              // clear the form and then hide it
              $('#edit-title').val('');
              $('#edit-release-year').val('');
              $('#edit-director').val('');
              $('#edit-rating').val('');
          }

          function deleteContact(dvdId) {
              $.ajax ({
                  type: 'DELETE',
                  url: "http://localhost:8080/dvd/" + dvdId,
                  success: function (status) {
                      loadContacts();
                  }
              });
          }

          // processes validation errors for the given input.  returns true if there
          // are validation errors, false otherwise
          function checkAndDisplayValidationErrors(input) {
              // clear displayed error message if there are any
              $('#errorMessages').empty();
              // check for HTML5 validation errors and process/display appropriately
              // a place to hold error messages
              var errorMessages = [];

              // loop through each input and check for validation errors
              input.each(function() {
                  // Use the HTML5 validation API to find the validation errors
                  if(!this.validity.valid)
                  {
                      var errorField = $('label[for='+this.id+']').text();
                      errorMessages.push(errorField + ' ' + this.validationMessage);
                  }
              });

              // put any error messages in the errorMessages div
              if (errorMessages.length > 0){
                  $.each(errorMessages,function(index,message){
                      $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
                  });
                  // return true, indicating that there were errors
                  return true;
              } else {
                  // return false, indicating that there were no errors
                  return false;
              }
          }
