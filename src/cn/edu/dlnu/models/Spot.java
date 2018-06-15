package cn.edu.dlnu.models;

import com.alibaba.fastjson.annotation.JSONField;

import cn.edu.dlnu.tools.ModelType;

public class Spot extends Model{
	
	public static String ModelName_STR = "spot";
	
	public Spot(){
		modelType = ModelType.SpotsModel.getTypeCode();
	}
    private Integer id;


	@JSONField(serialize = false)
    private Integer modelType;

	@JSONField(name = "content")
    private String content;

	@JSONField(name = "spot_name")
    private String spotName;

	@JSONField(name = "spot_type")
    private Integer type;

	@JSONField(name = "city")
    private Integer city;

	@JSONField(name = "suit_people")
    private String suitPople;

	@JSONField(name = "suit_time")
    private String suitTime;

	@JSONField(name = "need_things")
    private String needThing;

	@JSONField(name = "attentions")
    private String attentions;

	@JSONField(name = "detail")
    private String detail;


    private Integer likeCount;

    @JSONField(name="image_path")
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName == null ? null : spotName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getSuitPople() {
        return suitPople;
    }

    public void setSuitPople(String suitPople) {
        this.suitPople = suitPople == null ? null : suitPople.trim();
    }

    public String getSuitTime() {
        return suitTime;
    }

    public void setSuitTime(String suitTime) {
        this.suitTime = suitTime == null ? null : suitTime.trim();
    }

    public String getNeedThing() {
        return needThing;
    }

    public void setNeedThing(String needThing) {
        this.needThing = needThing == null ? null : needThing.trim();
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions == null ? null : attentions.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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