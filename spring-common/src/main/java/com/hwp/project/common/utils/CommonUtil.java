package com.hwp.project.common.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    /**
     * <li>利用正则表达式判断字符串是否是数字</li>
     * <li>true:是纯数字 false:非纯数字</li>
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+(.[0-9]+)?");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * <li>字符串是否为空</li>
     * <li>true:不为空 false:为空</li>
     */
    public static boolean checkEmptyOrNull(String str) {
        return !(null == str || "".equals(str) || "".equals(str.trim()) || "null".equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str));
    }

    /**
     * <li>解析出URL参数中的键值对</li>
     */
    public static JSONObject getUrlParamJson(String URL) {
        JSONObject paramJson = new JSONObject();

        if (URL.contains("?")) {
            return paramJson;
        }

        String strUrlParam = URL.substring(URL.indexOf("?") + 1, URL.length());
        String[] arrSplit = strUrlParam.split("&amp;");

        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            if (arrSplitEqual.length > 1) {
                paramJson.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (!arrSplitEqual[0].equals("")) {
                    paramJson.put(arrSplitEqual[0], "");
                }
            }
        }
        return paramJson;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public static void main(String[] args) {
//        String asd = "15";
//        System.out.println(CommonUtil.isNumeric(asd));
        System.out.println(checkEmptyOrNull("null"));
    }
}
