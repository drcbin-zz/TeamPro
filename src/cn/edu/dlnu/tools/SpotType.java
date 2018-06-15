package cn.edu.dlnu.tools;

public enum SpotType {
	Default(-1);
	private int type_code;
	private SpotType(int type_code){
		this.type_code = type_code;
	}
	
	public int getTypeCode(){
		return this.type_code;
	}
}
