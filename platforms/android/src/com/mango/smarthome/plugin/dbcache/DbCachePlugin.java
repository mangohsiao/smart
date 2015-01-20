package com.mango.smarthome.plugin.dbcache;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class DbCachePlugin extends CordovaPlugin {

	public final static String ACT_QUERY = "query";
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		
		if(action.equals(ACT_QUERY)){
			query(args, callbackContext);
		}
		
		return super.execute(action, args, callbackContext);
	}
	
	private void query(JSONArray args, CallbackContext callbackContext) throws JSONException{

		//get select SQL
		String sql = args.getString(0);

		SQLiteDatabase db = SmartDbHelper.openSQLite(cordova.getActivity());
		if(db==null){
			Log.i("db", "db is null");
		}else{
			try {
				Cursor c = db.rawQuery(sql,new String[]{});
				Log.i("db", "db query done");
				JSONArray jarary = new JSONArray();
				while(c.moveToNext()){
					JSONArray jObj = new JSONArray();
					int count = c.getColumnCount();
					Log.d("mango", "count = " + count);
					for(int i=0;i<count;i++){
						int type = c.getType(i);
						switch (type) {
						case Cursor.FIELD_TYPE_STRING:
							jObj.put(c.getString(i));
							break;
						case Cursor.FIELD_TYPE_INTEGER:
							jObj.put(c.getInt(i));
							break;
						case Cursor.FIELD_TYPE_NULL:
							jObj.put(null);
							break;
						case Cursor.FIELD_TYPE_FLOAT:
							jObj.put(c.getFloat(i));
							break;

						default:
							break;
						}
					}
					jarary.put(jObj);
				}
				//return the result to JS.
				Log.v("mango", jarary.toString());
				callbackContext.success(jarary);
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
				callbackContext.error("query Error.");
			} finally{
				db.close();
				db = null;
			}
		}
	}
}
