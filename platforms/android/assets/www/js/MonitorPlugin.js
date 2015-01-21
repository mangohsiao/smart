//Filename: MonitorPlugin.js

var MonitorPlugin = {
	open:function (ip,port,usrname,pswd,callback) {
		// body...
		cordova.exec(callback,this.failCallback,"MonitorPlugin","open",[ip,port,usrname,pswd]);
	},

	failCallback:function (argument) {
		// body...
		alert('call open() failed');
	}
}

function testMonitor () {
	// body...
	MonitorPlugin.open('125.216.243.105',8000,'admin','12345',
		function success (param) {
			// body...
		})
}