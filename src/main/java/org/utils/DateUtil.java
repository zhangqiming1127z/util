
package org.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	public static final String Y_M_D = "yyyy-MM-dd";
	public static final String Y = "yyyy";
	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	public static String date2String(Date createDate,String layout){
		if (createDate == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(layout);
		String format = sdf.format(createDate);
		return format;
	}

}
