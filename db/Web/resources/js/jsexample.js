// JavaScript Document
//用于输出错误消息
function processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
        document.getElementById("theResponse").innerHTML = req.responseText;
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

var xmlHttp;
var name;
var title;
var department;
var deleteID;
var EMP_PREFIX = "emp-";

function createXMLHttpRequest() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}
}

function addEmployee() {
	name = document.getElementById("name").value;
	title = document.getElementById("title").value;
	department = document.getElementById("dept").value;
	action = "add";
	if (name == "" || title == "" || department == "") {
		return;
	}
	var url = "EmployeeList?" + createAddQueryString(name, title, department, "add") + "&ts=" + new Date().getTime();
	createXMLHttpRequest();
	xmlHttp.onreadystatechange = handleAddStateChange;
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function createAddQueryString(name, title, department, action) {
	var queryString = "name=" + name + "&title=" + title + "&department=" + department + "&action=" + action;
	return queryString;
}

function handleAddStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			updateEmployeeList();
			clearInputBoxes();
		} else {
			alert("Error while adding employee.");
		}
	}
}

function clearInputBoxes() {
	document.getElementById("name").value = "";
	document.getElementById("title").value = "";
	document.getElementById("dept").value = "";
}

function deleteEmployee(id) {
	deleteID = id;
	var url = "EmployeeList?" + "action=delete" + "&id=" + id + "&ts=" + new Date().getTime();
	createXMLHttpRequest();
	xmlHttp.onreadystatechange = handleDeleteStateChange;
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function updateEmployeeList() {
	var responseXML = xmlHttp.responseXML;
	var status = responseXML.getElementsByTagName("status").item(0).firstChild.nodeValue;
	status = parseInt(status);
	if (status != 1) {
		return;
	}
	var row = document.createElement("tr");
	var uniqueID = responseXML.getElementsByTagName("uniqueID")[0].firstChild.nodeValue;
	row.setAttribute("id", EMP_PREFIX + uniqueID);
	row.appendChild(createCellWithText(name));
	row.appendChild(createCellWithText(title));
	row.appendChild(createCellWithText(department));
	var deleteButton = document.createElement("input");
	deleteButton.setAttribute("type", "button");
	deleteButton.setAttribute("value", "Delete");
	deleteButton.onclick = function () {
		deleteEmployee(uniqueID);
	};
	cell = document.createElement("td");
	cell.appendChild(deleteButton);
	row.appendChild(cell);
	document.getElementById("employeeList").appendChild(row);
	updateEmployeeListVisibility();
}

function createCellWithText(text) {
	var cell = document.createElement("td");
	cell.appendChild(document.createTextNode(text));
	return cell;
}

function handleDeleteStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			deleteEmployeeFromList();
		} else {
			alert("Error while deleting employee.");
		}
	}
}

function deleteEmployeeFromList() {
	var status = xmlHttp.responseXML.getElementsByTagName("status").item(0).firstChild.nodeValue;
	status = parseInt(status);
	if (status != 1) {
		return;
	}
	var rowToDelete = document.getElementById(EMP_PREFIX + deleteID);
	var employeeList = document.getElementById("employeeList");
	employeeList.removeChild(rowToDelete);
	updateEmployeeListVisibility();
}

function updateEmployeeListVisibility() {
	var employeeList = document.getElementById("employeeList");
	if (employeeList.childNodes.length > 0) {
		document.getElementById("employeeListSpan").style.display = "";
	} else {
		document.getElementById("employeeListSpan").style.display = "none";
	}
}


/*function setCookie(name,value){
	var cookie = name + "=" + encodeURIComponent(value);
	document.cookie = cookie;
}

function addItem(){
	var tr = document.createElement("tr");
		tr.setAttribute("class","separator");
		tr.setAttribute("align","center");
		tr.setAttribute("bgcolor","#ffffff");
	var td = document.createElement("td");
		td.setAttribute("align","left");
		td.setAttribute("style","padding-left: 0px;");
	tr.appendChild(td);
	var td1 = document.createElement("td");
		td1.setAttribute("align","left");
		td1.setAttribute("style","padding-left: 0px;");
	var input1 = document.createElement("input");
		input1.setAttribute("type","text");
		input1.setAttribute("size","52");
	td1.appendChild(input1);
	tr.appendChild(td1);
	var td2 = document.createElement("td");
		td2.setAttribute("align","left");
		td2.setAttribute("style","padding-left: 0px;");
	var input2 = document.createElement("input");
		input2.setAttribute("type","text");
		input2.setAttribute("size","35");
	td2.appendChild(input2);
	tr.appendChild(td2);
	var td3 = document.createElement("td");
		td3.setAttribute("align","left");
		td3.setAttribute("style","padding-left: 0px;");
	var input3 = document.createElement("input");
		input3.setAttribute("type","text");
		input3.setAttribute("size","28");
	td3.appendChild(input3);
	tr.appendChild(td3);
	
	var td4 = document.createElement("td");
		td4.setAttribute("width","5%");
	var a4 = document.createElement("a");
	var text4 = document.createTextNode("修改");
	a4.appendChild(text4);
	td4.appendChild(a4);
	tr.appendChild(td4);

	var td5 = document.createElement("td");
		td5.setAttribute("width","5%");
	var a5 = document.createElement("a");
	var text5 = document.createTextNode("删除");
	a5.appendChild(text5);
	td5.appendChild(a5);
	tr.appendChild(td5);
			
	var table = document.getElementById("table1");
	table.appendChild(tr);
}*/


function addItem() {
	var tr = document.createElement("tr");
	tr.setAttribute("align", "center");
	tr.setAttribute("bgcolor", "#ffffff");
	var td1 = document.createElement("td");
	td1.setAttribute("align", "left");
	var text1 = document.createTextNode(productName);
	td1.appendChild(text1);
	tr.appendChild(td1);
	var td2 = document.createElement("td");
	td2.setAttribute("align", "center");
	var text2 = document.createTextNode(shelfName);
	td2.appendChild(text2);
	tr.appendChild(td2);
	var td3 = document.createElement("td");
	td3.setAttribute("align", "center");
	var text3 = document.createTextNode(qty);
	td3.appendChild(text3);
	tr.appendChild(td3);
	var td4 = document.createElement("td");
	var a4 = document.createElement("a");
	a4.setAttribute("href", "#");
	a4.setAttribute("onclick", "#");
	var text4 = document.createTextNode("\u5220\u9664");
	a4.appendChild(text4);
	td4.appendChild(a4);
	tr.appendChild(td4);
	var table = document.getElementById("table1");
	table.appendChild(tr);
}

<script type="text/javascript">
	<!--
	function addItem(){
	var tr = document.createElement("tr");
		tr.setAttribute("class","separator");
		tr.setAttribute("align","center");
		tr.setAttribute("bgcolor","#ffffff");
	var td = document.createElement("td");
		td.setAttribute("align","left");
		td.setAttribute("style","padding-left: 0px;");
	var input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("size","52");
	td.appendChild(input);
	tr.appendChild(td);
	var td1 = document.createElement("td");
		td1.setAttribute("align","left");
		td1.setAttribute("style","padding-left: 0px;");
	var input1 = document.createElement("input");
		input1.setAttribute("type","text");
		input1.setAttribute("size","52");
	td1.appendChild(input1);
	tr.appendChild(td1);
	var td2 = document.createElement("td");
		td2.setAttribute("align","left");
		td2.setAttribute("style","padding-left: 0px;");
	var input2 = document.createElement("input");
		input2.setAttribute("type","text");
		input2.setAttribute("size","35");
	td2.appendChild(input2);
	tr.appendChild(td2);
	var td3 = document.createElement("td");
		td3.setAttribute("align","left");
		td3.setAttribute("style","padding-left: 0px;");
	var input3 = document.createElement("input");
		input3.setAttribute("type","text");
		input3.setAttribute("size","28");
	td3.appendChild(input3);
	tr.appendChild(td3);
				
	var table = document.getElementById("list");
	table.appendChild(tr);
		}
		//-->
	</script>
