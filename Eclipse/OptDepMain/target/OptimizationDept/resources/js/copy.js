/**
 * 
 */
function validateForm() {
  var x = document.forms["simCardSelection"]["msisdn"].value;
  if (x == "") {
    alert("Name must be filled out");
    return false;
  }
}