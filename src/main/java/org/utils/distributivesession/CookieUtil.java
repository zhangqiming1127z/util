package org.utils.distributivesession;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    //写cookie
    public static void writeCookie(String key, String value, HttpServletResponse response,String doMain) {
        //给cookie赋值
        Cookie cookie = new Cookie(key, value);
        //设置域名
        cookie.setDomain(doMain);
        //设置路径一般设置为服务器的根目录
        cookie.setPath("/");
        //响应客户端cookie
        response.addCookie(cookie);
    }


    public static String readCookie(String key, HttpServletRequest request) {
        //从请求中拿到所有cookies
        Cookie[] cookies = request.getCookies();
        //如果cookies没有值返回空
        if (cookies != null && cookies.length > 0) {
            //遍历所有cookie 找到自己想要的
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
}
