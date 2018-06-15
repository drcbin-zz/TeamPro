package cn.edu.dlnu.tools;

public enum SpotCity {
	
	Default(-1);
	private int city_code;
	private SpotCity(int type_code) {
		// TODO Auto-generated constructor stub
		this.city_code = type_code;
	}
	
	public int getTypeCode(){
		return this.city_code;
	}
}
