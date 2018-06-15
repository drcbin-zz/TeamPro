package cn.edu.dlnu.models;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import cn.edu.dlnu.tools.ModelType;

public class Comment extends Model{
	
	
	public static String ModelName_STR= "comment";
	

	public Comment(){
		modelType = ModelType.CommentModel.getTypeCode();
	}
    private Integer id;


	@JSONField(serialize = false)
    private Integer modelType = ModelType.CommentModel.getTypeCode();


    private Integer authId;


    private Integer objectType;


    private Integer objectId;


    private String title;


    private String comment;

	@JSONField(format="YYYY-dd-mm")
    private Date createTime;

    private String temp;

	private Integer status;
	
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}
}