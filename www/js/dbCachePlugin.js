/*



*/

var dbcache = {

	//query sqlStr, return in callback param,
	// callback(paramStr[])
	query: function (sqlStr, callback) {
		// body...
		cordova.exec(callback,this.failCallback,"DbCachePlugin","query",[sqlStr]);
	},

	failCallback: function (msg) {
		// body...
		alert(msg);
	},

	success: function (str) {
		alert(str[0]);
	}
}

function callQuery () {
	var sqlStr = "SELECT * FROM Room;"
	// body...
	dbcache.query(sqlStr,function (result) {
		// body...
		// alert("success" + result)
		document.getElementById('div_result').innerHTML="<Button>" + result + "</Button>"
	})
};