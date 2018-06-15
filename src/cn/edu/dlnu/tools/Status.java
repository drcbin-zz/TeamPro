package cn.edu.dlnu.tools;
import java.util.*;


public enum Status {
	ACTIVE(1), DELETE(2), BAN(3);
	static private Map<Integer, Status> CODE_STATUS = new HashMap<Integer, Status>();
	private int statucCode;
	
	private Status(int statusCode) {
		this.statucCode = statusCode;
	}
	// 执行初始化函数
	static {
		CODE_STATUS.put(1, Status.ACTIVE);
		CODE_STATUS.put(2, Status.DELETE);
		CODE_STATUS.put(3, Status.BAN);
	}
	
	public static Status getStatus(int statusCode) {
		try {
			return Status.CODE_STATUS.get(statusCode);
		} catch (Exception e) {
			// TODO: handle exception
			return Status.BAN;
		}
	}
	
	public static int getStatusCode(Status status) {
		return status.statucCode;
	}
}
