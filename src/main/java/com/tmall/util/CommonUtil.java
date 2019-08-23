package com.tmall.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 公共工具类 Created by Mr.Li on 2019/08/12
 */
public class CommonUtil {

	/**
	 * 去掉value为空的键值对
	 * 
	 * @param obj
	 * @return
	 */
	public static JSONArray removeKeyWithNullValue(Object obj) {
		if (obj == null) {
			return null;
		}
		JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(obj));
		for (int i = 0; i < array.size(); i++) {
			JSONObject json = array.getJSONObject(i);
			// 去掉value为空的键值对
			Iterator<Map.Entry<String, Object>> it = json.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> temp = it.next();
				if (temp.getValue().toString().length() < 1) {
					it.remove();
				}
			}
		}
		return array;
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request HttpServletRequest
	 * @return 当前IP地址
	 */
	public static String getIP(HttpServletRequest request) {
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
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	/**
	 * 通过ip地址获取地址详情
	 * 
	 * @param ip
	 * @return
	 */
	public static String getAddressByIp(String ip) {
		String result = doGet("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip, null);
		if (result != null && !"".equals(result)) {
			JSONObject obj = JSONObject.parseObject(result);
			String code = obj.getString("code");
			if ("0".equals(code)) {
				JSONObject data = obj.getJSONObject("data");
				return data.getString("country") + " | " + data.getString("region") + " | " + data.getString("city") + " | " + data.getString("isp");
			}else {
				return "IP地址有误";
			}
		}else {
			return "";
		}
	}

	public static String doGet(String urlStr, Map<String, Object> params) {
		String jsonStr = "";
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0) {
					param.append("?");
				} else {
					param.append("&");
				}
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			urlStr += param;
		}
		try {
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				InputStream in = httpConn.getInputStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				// 将输入流转移到内存输出流中
				while ((len = in.read(buffer, 0, buffer.length)) != -1) {
					out.write(buffer, 0, len);
				}
				// 将内存流转换为字符串
				jsonStr = new String(out.toByteArray());
				out.close();
				in.close();
			}
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return jsonStr;
	}

}
