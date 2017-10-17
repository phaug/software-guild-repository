$(document).ready(function(){

  /**
   * Needed to set all fields to original state
   *
   */

  var INITIAL_MONEY = {
    dollars: 0,
    quarters: 0,
    dimes: 0,
    nickels: 0,
    pennies: 0
  };

  /**
   * Setting initial values to currency
   *
   */

  var currentMoney = {
      dollars: 0,
      quarters: 0,
      dimes: 0,
      nickels: 0,
      pennies: 0
  };

  var currentItem = null;
  $('#addDollar').click(onDollarInClick);
  $('#addQuarter').click(onQuarterInClick);
  $('#addDime').click(onDimeInClick);
  $('#addNickel').click(onNickelInClick);
  $('#makePurchase').click(onMakePurchaseClick);
  $('#returnChange').click(onReturnChangeClick);
  displayCurrentMoney();

  function listItems() {
      // Clear the previous content as to not append to it
      //


      var $contentRows = $('#itemRows');
      $contentRows.on("click", "button", onVendingItemClick);
      $.ajax ({
          type: 'GET',
          url: 'http://localhost:8080/items',
          success: function (data, status) {
            var rows = '';
              $.each(data, function (index,item) {
                  var id = item.id;
                  var name = item.name;
                  var price = item.price;
                  var quantity = item.quantity;

                  rows += '<button class="col-md-4">';
                  rows += '<p>' + id + '</p>'
                  rows += '<p>' + name + '</p>';
                  rows += '<p>' + price + '</p>';
                  rows += '<p>' + quantity + '</p>';
                  rows += '</button>';

              });
                $contentRows.append(rows);
          },
          error: function() {
              $('#errorMessages')
                  .append($('<li>')
                  .attr({class: 'list-group-item list-group-item-danger'})
                  .text('Error calling web service.  Please try again later.'));
          }
      });
  }
  listItems();

  function onVendingItemClick(event) {
    var itemId = $(event.currentTarget).text().substring(0, 1);
    currentItem = itemId;
    displayCurrentItem();
  }

  function displayCurrentItem() {
    $('#selectedItemIndex').val(currentItem);
  }

  /**
   * Add dallar to machine
   *
   * @param event description
   */

  function onDollarInClick(event) {
    currentMoney.dollars++;
    displayCurrentMoney();
  }

  /**
   * Add quarter to machine
   *
   * @param event description
   */

  function onQuarterInClick(event) {
    currentMoney.quarters++;
    displayCurrentMoney();
  }

  /**
   * Add dime to machine
   *
   * @param event description
   */

  function onDimeInClick(event) {
    currentMoney.dimes++;
    displayCurrentMoney();
  }

  /**
   * Add nickel to machine
   *
   * @param event description
   */

  function onNickelInClick(event) {
    currentMoney.nickels++;
    displayCurrentMoney();
  }

  /**
   * Calculates the money added to mahine
   *
   * @param
   */

  function totalMoneyAmount(money) {
    var total = 0;
    var dollars = 0;
    if(typeof money.dollars !== 'undefined'){
      dollars = money.dollars;
    }
    total = total + (dollars * 1);
    total = total + (money.quarters * 0.25);
    total = total + (money.dimes * 0.10);
    total = total + (money.nickels * 0.05);
    total = total + (money.pennies * 0.01);

    return total;
  }

  /**
   * Displays total money added to machine
   *
   */

  function displayCurrentMoney() {
    var total = totalMoneyAmount(currentMoney);
    $('#moneyIn').val(total.toPrecision(3));
  }

  function displayMessage(message) {
    $('#displayMessages').val(message);
  }

  /**
   * On purchase attempt, collects money and returns change if success.
   *If invalid funds or item out of stock, displays error message
   * @param event description
   */

  function onMakePurchaseClick(event) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080//money/' + totalMoneyAmount(currentMoney) + '/item/' + currentItem,
        success: function (data, status) {
          var successMessage = 'Thank you!!!';
          displayChange(data);
          displayMessage(successMessage);
          resetState();

        },
        error: function(data) {
          var errorMessage = data.responseJSON.message;
          displayMessage(errorMessage);
        }
    });
  }

  function displayChange(change) {
    $('#change').val(convertMoneyToChange(change));
  }
  /**
   * Handles clicks for returning change
   *
   * @param money description
   */

  function convertMoneyToChange(money) {
    var change = '';
    var quarters = 0;

    // Dollars are never returned, convert to quarters
    if (typeof money.dollars !== 'undefined') {
      quarters = money.dollars * 4;
    }
    quarters = quarters + money.quarters;
    change = change + 'quarters: ' + quarters;
    change = change + ' dimes: ' + money.dimes;
    change = change + ' nickels: ' + money.nickels;
    change = change + ' pennies: ' + money.pennies;

    return change;
  }

  /**
   * Handles clicks for returning change
   *
   * @param event description
   */
  function onReturnChangeClick(event) {
    displayChange(currentMoney);
    resetState();
  }

  function  clearItemTable() {
    $('#itemRows').empty();
  }
  /**
   * Resets all fields to original state
   *
   * @param
   */
  function resetState() {
    currentMoney = INITIAL_MONEY;
    currentItem = null;
    displayCurrentMoney();
    displayCurrentItem();
    clearItemTable();
    listItems();
  }

});
