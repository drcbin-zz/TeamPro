package cn.edu.dlnu.models;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import cn.edu.dlnu.tools.ModelType;

public class TravelMemoary extends Model{
	public TravelMemoary() {
		// TODO Auto-generated constructor stub
		modelType = ModelType.TravelMemoaryModel.getTypeCode();
	}
	public static String ModelName_STR= "travelmemoary";

    private Integer id;


	@JSONField(serialize = false)
    private Integer modelType;


    private String title;


    private Integer authId;


    private String content;


    @JSONField(name = "createTime", format="YYYY-dd-mm")
    private Date cdate;

    private String imagePath;

    private Integer likeCount;

    @JSONField(name="type_name")
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
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