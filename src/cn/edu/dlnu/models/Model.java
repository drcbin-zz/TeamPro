package cn.edu.dlnu.models;

import java.lang.reflect.Field;

import cn.edu.dlnu.tools.Status;


abstract  public class Model {
	public static String TABLE_NAME_STR = null;

	abstract public boolean isAvailable();

} 