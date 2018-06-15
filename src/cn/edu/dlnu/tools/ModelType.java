package cn.edu.dlnu.tools;

import cn.edu.dlnu.models.TravelMemoary;

public enum ModelType {
	UserModel(1), SpotsModel(2),FriendsTravel(3),TravelMemoaryModel(4), CommentModel(5);
	private int type_code; 
	
	private ModelType(int type_code) {
		this.type_code = type_code;
	}
	
	public int getTypeCode(){
		return this.type_code;
	}
}
