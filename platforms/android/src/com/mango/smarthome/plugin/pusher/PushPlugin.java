package com.mango.smarthome.plugin.pusher;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class PushPlugin extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		// TODO Auto-generated method stub

		Log.d("mango", "args.length() = "+args.length());
		if(args.length() < 4){
			callbackContext.error("args < 4 \n ip,port,username,password");
			return false;
		}

		String ip = args.getString(0);
		int port = args.getInt(1);
		String username = args.getString(2);
		String password = args.getString(3);
		
		Log.d("mango", ip+port+username+password);
		callbackContext.success();
		
		return true;
	}
}
