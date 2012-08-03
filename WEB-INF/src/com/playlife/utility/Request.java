package com.playlife.utility;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.playlife.utility.exceptions.PresentationLayerException;

public class Request {
	public static Long getLong(HttpServletRequest request, String paraName){
		Long l_return = -1L;
		try {
			l_return = Long.parseLong(request.getParameter(paraName).toString());
		} catch (Exception ex){
			throw new PresentationLayerException(-40000, ex, paraName);
		}
		return l_return;
	}
	public static Long getLongAllowNull(HttpServletRequest request, String paraName){
		Long l_return = -1L;
		try {
			if (request.getParameter(paraName) == null || request.getParameter(paraName).toString().equalsIgnoreCase("null"))
				return null;
			l_return = Long.parseLong(request.getParameter(paraName).toString());
		} catch (Exception ex){
			throw new PresentationLayerException(-40001, ex, paraName);
		}
		return l_return;
	}
	public static String getString(HttpServletRequest request, String paraName){
		String s_return = "";
		try {
			s_return = request.getParameter(paraName).toString();
		} catch (Exception ex){
			throw new PresentationLayerException(-40002, ex, paraName);
		}
		return s_return;
	}
	public static String getStringAllowNull(HttpServletRequest request, String paraName){
		String s_return = "";
		try {
			if (request.getParameter(paraName) == null)
				return null;
			else s_return = request.getParameter(paraName).toString();
		} catch (Exception ex){
			throw new PresentationLayerException(-40003, ex, paraName);
		}
		return s_return;
	}
	public static int getInt(HttpServletRequest request, String paraName){
		int i_return = -1;
		try {
			i_return = Integer.parseInt(request.getParameter(paraName).toString());
		} catch (Exception ex){
			throw new PresentationLayerException(-40004, ex, paraName);
		}
		return i_return;
	}
	public static int getIntAllowNull(HttpServletRequest request, String paraName){
		int i_return = -1;
		try {
			if (request.getParameter(paraName) == null || request.getParameter(paraName).toString().equalsIgnoreCase("null"))
				return -1;
			i_return = Integer.parseInt(request.getParameter(paraName).toString());
		} catch (Exception ex){
			throw new PresentationLayerException(-40005, ex, paraName);
		}
		return i_return;
	}
	public static boolean getBoolean(HttpServletRequest request, String paraName){
		boolean b_return = false;
		try {
			b_return = Boolean.parseBoolean(request.getParameter(paraName).toString());
		} catch (Exception ex){
			throw new PresentationLayerException(-40012, ex, paraName);
		}
		return b_return;
	}
	public static ArrayList<Long> getLongArrayAllowNull(HttpServletRequest request, String paraName){
		ArrayList<Long> arr_return = new ArrayList<Long>();
		try {
			String[] arr_string;
			if (request.getParameter(paraName) == null)
				return null;
			else arr_string = request.getParameterValues(paraName);
			
			for (String string : arr_string)
				arr_return.add(Long.parseLong(string));
			
		} catch (Exception ex){
			throw new PresentationLayerException(-40006, ex, paraName);
		}
		return arr_return;
	}
	public static ArrayList<String> getStringArrayAllowNull(HttpServletRequest request, String paraName){
		ArrayList<String> arr_return = new ArrayList<String>();
		try {
			String[] arr_string;
			if (request.getParameter(paraName) == null)
				return null;
			else arr_string = request.getParameterValues(paraName);
			
			for (String string : arr_string)
				arr_return.add(string);
			
		} catch (Exception ex){
			throw new PresentationLayerException(-40006, ex, paraName);
		}
		return arr_return;
	}
	public static Long getSessionLong(HttpServletRequest request, String paraName){
		Long l_return = -1L;
		try {
			HttpSession session = request.getSession(true); 
			l_return = Long.parseLong(session.getAttribute(paraName).toString());
		} catch (Exception ex){
			throw new PresentationLayerException(-40007, ex, paraName);
		}
		return l_return;
	}
	
	public static Long getSessionLongAllowNull(HttpServletRequest request, String paraName){
		Long l_return = -1L;
		try {
			HttpSession session = request.getSession(true); 
			if (session.getAttribute(paraName) == null || session.getAttribute(paraName).toString().equalsIgnoreCase("null"))
				return null;
			l_return = Long.parseLong(session.getAttribute(paraName).toString());
		} catch (Exception ex){
			throw new PresentationLayerException(-40008, ex, paraName);
		}
		return l_return;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSessionObject(HttpServletRequest request, String paraName, Class<T> classOf) throws PresentationLayerException{
		try {
			HttpSession session = request.getSession(true); 
			
			return (T)session.getAttribute(paraName);
		} catch (Exception ex){
			throw new PresentationLayerException(-40011, ex, paraName);
		}
		
	}
	/************************
	 * 						*
	 * 		  Object		* 
	 * 						*
	 ************************/
	public static <T> T getObject(HttpServletRequest request, String paraName, Class<T> classOf) throws PresentationLayerException{
		try {
			if (request.getParameter(paraName) == null)
				throw new PresentationLayerException(-40011, paraName);
			
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm").create();;
			return gson.fromJson(request.getParameter(paraName), classOf);
		} catch (Exception ex){
			throw new PresentationLayerException(-40011, ex, paraName);
		}
		
	}
	public static <T> T getObject(HttpServletRequest request, String paraName, Class<T> classOf, String dateFormat) throws PresentationLayerException{
		try {
			if (request.getParameter(paraName) == null)
				throw new PresentationLayerException(-40011, paraName);
			
			Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();;
			return gson.fromJson(request.getParameter(paraName), classOf);
		} catch (Exception ex){
			throw new PresentationLayerException(-40011, ex, paraName);
		}
		
	}
	public static <T> List<T> getArrayObject(HttpServletRequest request, String paraName, Class<T> classOf) throws PresentationLayerException{
		try {
			if (request.getParameter(paraName) == null)
				throw new PresentationLayerException(-40011, paraName);
			
			List<T> arr_return = new ArrayList<T>();
			String s_array = request.getParameter(paraName);
			JSONArray arr_json = (JSONArray)JSONSerializer.toJSON(s_array);
			Gson gson = new Gson();
			for (int i = 0; i < arr_json.size(); i++){
				JSONObject obj = arr_json.getJSONObject(i);				
				arr_return.add(gson.fromJson(obj.toString(), classOf));	
			}
			
			return arr_return;			
		} catch (Exception ex){
			throw new PresentationLayerException(-40011, ex, paraName);
		}
	}
}
