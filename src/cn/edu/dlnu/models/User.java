package cn.edu.dlnu.models;

import java.util.Date;

import cn.edu.dlnu.tools.ModelType;

import com.alibaba.fastjson.annotation.JSONField;


public class User extends Model{
	public static String ModelName_STR = "user";

	public User(){
		modelType = ModelType.UserModel.getTypeCode();
	}

    private Integer id;
	

	@JSONField(serialize = false)
    private Integer modelType;
	
	@JSONField(name = "name")
    private String nickname;

	@JSONField(serialize = false)
    private String password;


    private String email;

    private String qq;


    private String address;

    @JSONField(name = "phone")
    private String tel;

    @JSONField(format="YYYY-dd-mm")
    private Date birthday;

    private String imagepath;

    @JSONField(name = "gender")
    private Integer sex;

    @JSONField(name = "createTime",format="YYYY-dd-mm")
    private Date cdate;

    @JSONField(name = "lastTime",format="YYYY-dd-mm")
    private Date ldate;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
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