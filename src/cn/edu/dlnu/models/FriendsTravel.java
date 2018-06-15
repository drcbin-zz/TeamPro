package cn.edu.dlnu.models;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import cn.edu.dlnu.tools.ModelType;

public class FriendsTravel extends Model{
	
	
	public FriendsTravel(){
		modelType = ModelType.FriendsTravel.getTypeCode();
	}
	public static String ModelName_STR= "friendstravel";
	

    private Integer id;

    private Integer userId;

    private Integer spotId;

	@JSONField(serialize = false)
    private Integer modelType;


    private String title;

	private Integer status;
	
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@JSONField(name = "fromTime", format="YYYY-dd-mm")
    private Date fromdate;

	@JSONField(name = "toTime", format="YYYY-dd-mm")
    private Date todate;


    private String content;


	@JSONField(format="YYYY-dd-mm")
    private Date createTime;

	@JSONField(name = "destination")
    private String temp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
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

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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