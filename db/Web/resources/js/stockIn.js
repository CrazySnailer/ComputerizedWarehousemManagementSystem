function retrieveURL(url) {
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }
var req;
var which;
function addStockInDetail() {
    // Construct a CSV string from the entries.  Make sure all fields are
    // filled in first.
    
    var stockInId = document.getElementById("stockInId").value;
    
	var productName = document.getElementById("productName").value;
	
	var shelfName = document.getElementById("shelfName").value;
	
	var qty = document.getElementById("qty").value;
	
	if (stockInId == "" || productName == "" || shelfName == "" || qty == "") {
      alert("Please fill in all fields first");
      return false;
    }
    csv = stockInId + "," + productName + "," + shelfName + "," + qty;
	alert(csv);
	
	
	retrieveURL("addStockInDetail.do?csv=" + escape(csv));
	alert("yun");
}
function processStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			updateStockInDetailList();
			clearInputBoxes();
		} else {
			alert("Error while adding employee.");
		}
	}
}
function clearInputBoxes() {
	document.getElementById("productName").value = "";
	document.getElementById("shelfName").value = "";
	document.getElementById("qty").value = "";
}
function updateStockInDetailList() {
	var row = document.createElement("tr");
	row.setAttribute("align", "center");
	row.setAttribute("bgcolor", "#ffffff");
	row.appendChild(createCellWithText(productName));
	row.appendChild(createCellWithText(shelfName));
	row.appendChild(createCellWithText(qty));
	var deleteButton = document.createElement("input");
	deleteButton.setAttribute("type", "button");
	deleteButton.setAttribute("value", "Delete");
	cell = document.createElement("td");
	cell.appendChild(deleteButton);
	row.appendChild(cell);
	document.getElementById("stockInDetailList").appendChild(row);
}

function createCellWithText(text) {
	var cell = document.createElement("td");
	cell.appendChild(document.createTextNode(text));
	return cell;
}



function check(val){
	if (val<10){
		return("0"+val);
	}else{
		return(val);
	}
}
function ShowTime()
{
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	var timestr=year+"-"+month+"-"+day;
	document.getElementById("stockInDate").setAttribute("value",timestr);
	

}