




function submit_service() {
var name = document.getElementById("name").value;
var date = document.getElementById("date").value;
var time = document.getElementById("time").value;
var destination = $("#destination").val();

if($("input[type='radio'].ourservices").is(':checked')) {
    var service_type = $("input[type='radio'].ourservices:checked").val();
    }

if (validation()) // Calling validation function
{

alert(" Name : " + name + " \n Date : " + date + "\n Service Requested : " +service_type+ "\n Time : "+ time + "\n Destination : "+ destination +" \n Form Id : " + document.getElementById("service").getAttribute("id") + "\n\n Form Submitted Successfully......");
document.getElementById("service").submit(); //form submission
}
}

function validation() {
var name = document.getElementById("name").value;
var date = document.getElementById("date").value;
var time = document.getElementById("time").value;
var alpha = /[*|\":<>[\]{}`\\()';@&$]/;
var regex = /[0-9]|\./;
var destination = $("#destination").val();
if (name === '' || date === '' || time == '') {
alert("Please complete all fields under the services tab...!!!!!!");
return false;
}
else if(alpha.test(name)){
alert("Please insert a valid name....") ;
return false;   
}
else if(regex.test(name) ){
 alert("Please insert a valid name....") ;
return false;
}
else if(!check_service())
{
  return false
}
else {
return true;
}
}

function check_service(){
if($("input[type='radio'].ourservices").is(':checked')) {
    
return true;
    
    }
else{
    alert("Please choose a service type!");
    return false;
}
}


function submit_feedback() {
var name1 = document.getElementById("name1").value;
var date1 = document.getElementById("date1").value;
var time1 = document.getElementById("time1").value;
var email = document.getElementById("email").value;
var comments = $("#comments").val();
var destination1 = $("#destination1").val();

if (validation1()) // Calling validation function
{

alert(" Name : " + name1 + " \n Date : " + date1 + "\n Time : "+ time1 + "\n Destination : "+ destination1 +"\n Email : " + email + "\n Form Id : " + document.getElementById("feedback").getAttribute("id") + "\n\n Form Submitted Successfully......");
document.getElementById("feedback").submit(); //form submission
}
}

function validation1() {
var name1 = document.getElementById("name1").value;
var date1 = document.getElementById("date1").value;

var destination1 = $("#destination").val();
var email = document.getElementById("email").value;
var comments = $("#comments").val();
var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
var alpha = /[*|\":<>[\]{}`\\()';@&$]/;
var regex = /[0-9]|\./;

if (name1 === '' || date1 == '' || comments == '') {
alert("Please complete all fields in the feedbak form...!!!!!!");
return false;
}
else if(!(email).match(emailReg)) {
alert("Invalid Email...!!!!!!");
return false;
}
else if(alpha.test(name1)){
alert("Please insert a valid name....") ;
return false;   
}
else if(regex.test(name1) ){
 alert("Please insert a valid name....") ;
return false;
}
 else {
return true;
}
}









