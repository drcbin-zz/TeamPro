package cn.edu.dlnu.tools;

import java.util.HashMap;
import java.util.Map;

public enum UserSex {
	MAN(1), WOMEN(2), UNKNOW(3);
	static private Map<Integer, UserSex> CODE_SEX = new HashMap<Integer, UserSex>();
	private int sexCode;
	
	private UserSex(int sexCode) {
		this.sexCode = sexCode;
	}
	// 执行初始化函数
	static {
		CODE_SEX.put(1, UserSex.MAN);
		CODE_SEX.put(2, UserSex.WOMEN);
		CODE_SEX.put(3, UserSex.UNKNOW);
	}
	
	public static UserSex getSex(int sexCode)  {
		try {
			return UserSex.CODE_SEX.get(sexCode);
		} catch (Exception e) {
			// TODO: handle exception
			return UserSex.UNKNOW;
		}
	}
	
	public static int getSexCode(UserSex sex) {
		return sex.sexCode;
	}
}
