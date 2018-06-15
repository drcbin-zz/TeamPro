package cn.edu.dlnu.models;
import java.lang.reflect.Field;


import com.alibaba.fastjson.JSON;


public abstract class ModelManager {
	

	static public Object getValueByFieldName(String fieldName, Object instance) {
		Field field;
		try {
			field = instance.getClass().getDeclaredField(fieldName);
			field.get(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return field;
	}
	

	static public String toJsonString(Model instalce) {
		// TODO Auto-generated method stub
		return JSON.toJSONString(instalce);
	}
}
