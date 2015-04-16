package ch.eswitch.cordova.inflateplugin;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InflateService	extends	CordovaPlugin
{
	
	public	static	final	String	ACTION_INFLATE = "inflate";
	
	@Override
	public boolean execute(	String			action,
							JSONArray		args,
							CallbackContext	callbackContext) throws JSONException
	{
		
		if (action.equals(ACTION_INFLATE))
		{
			int count = 0;
			JSONObject arg_object = args.getJSONObject(0);
			count++;
			InputStream stream = new ByteArrayInputStream(arg_object.getString("content").getBytes());
			count++;
			try {
				InputStream gzip = new GZIPInputStream(stream);
				count++;
				InputStreamReader reader = new InputStreamReader(gzip);
				count++;
				BufferedReader in = new BufferedReader(reader);
				count++;
				StringBuilder content = new StringBuilder();
				count++;
				
				String readLine;
				while ((readLine = in.readLine()) != null)
				{
					count++;
					content.append(readLine);
					content.append("\n");
				}
				
				callbackContext.success(content.toString());
				
				return true;
				
			} catch (IOException e) {
				callbackContext.error("Error! Count: " + count + " Message: " + e.getMessage());
			}
		}

		return false;
	}
	
}
