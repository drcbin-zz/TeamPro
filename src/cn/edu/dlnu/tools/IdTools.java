package cn.edu.dlnu.tools;

public enum IdTools {

	
	Default(-1);
	private int Id_code;
	private IdTools(int Id_code) {
		// TODO Auto-generated constructor stub
		this.Id_code = Id_code;
	}
	
	public int getTypeCode(){
		return this.Id_code;
	}
}
