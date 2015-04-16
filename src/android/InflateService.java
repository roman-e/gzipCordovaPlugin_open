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
		
			InputStream stream = new ByteArrayInputStream(args.getString(0).getBytes());
			try {
				InputStream gzip = new GZIPInputStream(stream);
				InputStreamReader reader = new InputStreamReader(gzip);
				BufferedReader in = new BufferedReader(reader);
				StringBuilder content = new StringBuilder();
				
				String readLine;
				while ((readLine = in.readLine()) != null)
				{
					content.append(readLine);
					content.append("\n");
				}
				
				callbackContext.success(content.toString());
				
				return true;
				
			} catch (IOException e) {
				callbackContext.error("Error!");
			}
		}

		return false;
	}
	
}
