package com.paypal.util;

public class ExceptionUtil {

	public static String getStackTrace(Exception e) {
		
		StringBuilder ex = new StringBuilder(); 
		
		ex.append(e.toString());
		ex.append("\n\r");
		
		for (StackTraceElement element : e.getStackTrace()) {
			ex.append(element);
			ex.append("\n\r");
		}
		
		return ex.toString();
	}
	
}
