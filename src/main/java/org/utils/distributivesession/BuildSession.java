package org.utils.distributivesession;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.utils.redis.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class BuildSession {
    public static void distributiveSession(HttpServletRequest request, HttpServletResponse response,String scode,String codeKey,String doMain,Integer codeTime) {
        String user_code = CookieUtil.readCookie(codeKey, request);
        if (StringUtils.isEmpty(user_code)){
            //如果没有我手动设置一个
            user_code=UUID.randomUUID().toString().replace("-","");
            CookieUtil.writeCookie(codeKey,user_code,response,doMain);
        }
        RedisUtil.setEx(user_code,scode,codeTime);
    }

    public static String getSessionId(HttpServletRequest request, HttpServletResponse response,String codeKey,String doMain) {
        String user_code = CookieUtil.readCookie(codeKey, request);
        if (StringUtils.isEmpty(user_code)){
            //如果没有我手动设置一个
            user_code=UUID.randomUUID().toString().replace("-","");
            CookieUtil.writeCookie(codeKey,user_code,response,doMain);
        }
       return user_code;
    }
    public static void distributiveSession(HttpServletRequest request, HttpServletResponse response,Object user,String userKey,String doMain,Integer loginTime) {
        String user_code = CookieUtil.readCookie(userKey, request);
        if (StringUtils.isEmpty(user_code)){
            //如果没有我手动设置一个
            user_code=UUID.randomUUID().toString().replace("-","");
            CookieUtil.writeCookie(userKey,user_code,response,doMain);
        }
        String s = JSONObject.toJSONString(user);
        RedisUtil.setEx(user_code,s ,loginTime);
    }

}
