package cn.edu.dlnu.models;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Friend  extends Model{
	
	public static String ModelName_STR= "Friend";
	

    private Integer id;

	@JSONField(serialize = false)
    private Integer from;

	@JSONField(name = "name")
    private Integer to;

	@JSONField( format="YYYY-dd-mm")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}
}