var baseurl = "http://localhost:8080/gatepass/v1/users/allUsers";
function loadPersons(){
var xmlhttp = new XMLHttpRequest();
xmlhttp.open("GET",baseurl,true);
xmlhttp.onreadystatechange = function() {
  if(xmlhttp.readyState ===4 && xmlhttp.status ===200){
    var users = JSON.parse(xmlhttp.responseText);
    var tbltop = `<table id="datatable">
        <tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>`;
    //main table content we fill from data from the rest call
    var main ="";
    for (i = 0; i < users.length; i++){
      main += "<tr><td>"+users[i].userId+"</td><td>"+users[i].firstName+"</td><td>"+users[i].lastName+"</td><td>"+users[i].email+"</td></tr>";
    }
    var tblbottom = "</table>";
    var tbl = tbltop + main + tblbottom;
    document.getElementById("datatable").innerHTML = tbl;
  }
};
xmlhttp.send();
}
window.onload = function(){
loadPersons();
}