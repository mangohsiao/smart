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
		// document.getElementById('div_result').innerHTML="<Button>" + result + "</Button>"
		document.getElementById('div_result').innerHTML = "";
		for (var i = 0;i < result.length;i++) {
			document.getElementById('div_result').innerHTML += ('<a id="btn_'+i+'" data-role="button">' + result[i][1] + '</a>');
			$('#btn_'+i).button();
		};
		$('#page_02_header').html('<h1>'+ '家电控制' +'</h1>' + '<a id="page_02_header_btn" class="ui-btn-left">' + '返回' + '</a>').header();
		// $('#page_02_header_btn').button();
	})
};