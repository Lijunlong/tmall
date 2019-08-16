package com.tmall.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 公共工具类 Created by Mr.Li on 2019/08/12
 */
public class CommonUtil {
	
	/**
	 * 去掉value为空的键值对
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

}
