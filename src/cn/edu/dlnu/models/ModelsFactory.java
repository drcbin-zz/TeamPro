package cn.edu.dlnu.models;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.ibatis.session.*;
import cn.edu.dlnu.daos.*;
import cn.edu.dlnu.tools.*;

/**
 * 说明：封装增删改查的功能，以及其他辅助的功能
 * @author Administrator
 * 
 */



public class ModelsFactory {

	/**
	 * 说明：可以更新的字段
	 */
	static public Map<String, String[]> ModelName_EditFields_Map = new HashMap<>();
	static private String[] UserEditFields = {
		
							"status", "password", "sex", "birthday", "email", "qq", 
							"tel", "address", "temp"};
	
	static private String[] SpotEditFields = {
							"status", "content", "spot_name", "type", "city", "suit_people", "suit_time", 
							"need_thing", "attentions","detail","temp"};
	
	static private String[] FriendsTravelEditFields = {
							"status", "spot_id", "title", "frmdate", "todate", "content"};
	
	static private String[] TravelMemoaryEditFields = {
							"status", "title", "content", "image_path", "like_count", "temp"};
	
	
	
	static {
		ModelName_EditFields_Map.put(User.class.getName(), UserEditFields);
		ModelName_EditFields_Map.put(Spot.class.getName(), SpotEditFields);
		ModelName_EditFields_Map.put(FriendsTravel.class.getName(), FriendsTravelEditFields);
		ModelName_EditFields_Map.put(TravelMemoary.class.getName(), TravelMemoaryEditFields);
	}
	
	
	/**
	 * 说明：通过status判断信息是否被删除。
	 * 		ACTIVE时，返回true。
	 * @param model 
	 * @return boolean
	 */
	static public boolean isActive(Model model){
		try {
			Field status = model.getClass().getDeclaredField("status");
			status.setAccessible(true);
			if((int)status.get(model) == Status.getStatusCode(Status.ACTIVE)){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	static public List<Model> getComments(Integer id){
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(ModelsFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		List<Model> models = null;
		CommentMapper mapper = session.getMapper(CommentMapper.class);
		models = mapper.getComments(id);
		session.close();
		return models;
	}
	
	
	// 分类
	static public Map<String, List<Model>> categoryByType(List<Model> models){
		Map<String, List<Model>> map = new HashMap<String, List<Model>>();
		List<Model> list = null;
		try {
			Field type = TravelMemoary.class.getDeclaredField("temp");
			type.setAccessible(true);
			String typeString = null;
			Model model = null;
			for(int i = 0; i < models.size(); i++){
				model = models.get(i);
				typeString = (String) type.get(model);
				list = map.get(typeString);
				if(list == null){
					list = new ArrayList<Model>();
					list.add(model);
					map.put(typeString, list);
				}else{
					list.add(model);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			map = null;
		}
		return map;
	}  // end function categoarByType
	
	/**
	 * 说明：把将要更新的内容加入到更新前的内容，整合成一个新的Model
	 * @param oldModel 更新前的信息
	 * @param newModel 将要更新的内容
	 * @return Model
	 */
	static public Model updateWithModel(Model oldModel, Model newModel){
		String[] fields = ModelName_EditFields_Map.get(oldModel.getClass().getName());
		
		for (String fieldName : fields) {
			try {
				Field field = oldModel.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				String value = (String) field.get(newModel);
				field.set(oldModel, value);
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return oldModel;
	}
	
	/**
	 * 说明：
	 * @param modelName 从url中获取到的Model的类型（TravelMemoary/FriendsTravel/Comment）
	 * @param auth_id   作者的ID
	 * @param map		分页的page和count
	 * @return			Model列表
	 */

	static public List<Model> selectByAuthId(String modelName, Integer auth_id,Map<String, Integer> map){
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(ModelsFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		Map<String, Integer> map2 = map;
		List<Model> models = null;
		if(modelName == TravelMemoary.ModelName_STR){
			TravelMemoaryMapper mapper = session.getMapper(TravelMemoaryMapper.class);
			map2.put("auth_id", auth_id);
			models = mapper.selectByAuthId(map2);
		}else if(modelName == FriendsTravel.ModelName_STR){
			FriendsTravelMapper mapper = session.getMapper(FriendsTravelMapper.class);
			map2.put("auth_id", auth_id);
			models = mapper.selectByAuthId(map2);
		}else if(modelName == Comment.ModelName_STR){
			CommentMapper mapper = session.getMapper(CommentMapper.class);
			map2.put("auth_id",auth_id);
			models = mapper.selectByAuthId(map2);
		}
		session.close();
		return models;
	}
	
	
	

	/**
	 * 说明：通过ID查找信息
	 * @param modelName 从url中获取到的Model的类型（User/Spot/TravelMemoary/FriendsTravel/Friend/Comment）
	 * @param id
	 * @return Model
	 */
	static public Model selectByPrimaryKey(String modelName, Integer id){
//		System.out.println("model name is:" + modelName + ", id is:" + id);

		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(ModelsFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		Model model = null;
		if (modelName.equals(User.ModelName_STR)) {
			UserMapper mapper = session.getMapper(UserMapper.class);
			model  = mapper.selectByPrimaryKey(id);
			
		}else if(modelName.equals(Spot.ModelName_STR)){
			SpotMapper mapper = session.getMapper(SpotMapper.class);
			model = mapper.selectByPrimaryKey(id);
			System.out.println("in spot");
			
		}else if(modelName.equals(TravelMemoary.ModelName_STR)){
			TravelMemoaryMapper mapper = session.getMapper(TravelMemoaryMapper.class);
			model = mapper.selectByPrimaryKey(id);
			
		}else if(modelName.equals(FriendsTravel.ModelName_STR)){
			FriendsTravelMapper mapper = session.getMapper(FriendsTravelMapper.class);
			model = mapper.selectByPrimaryKey(id);
			
		}else if(modelName.equals(Friend.ModelName_STR)){
			FriendMapper mapper = session.getMapper(FriendMapper.class);
			model = mapper.selectByPrimaryKey(id);
			
		}else if(modelName.equals(Comment.ModelName_STR)){
			CommentMapper mapper = session.getMapper(CommentMapper.class);
			model = mapper.selectByPrimaryKey(id);
		}
		session.close();
		return model;
	}
	
	/**
	 * 说明：遍历所有信息
	 * @param modelName 从url中获取到的Model的类型（User/Spot/TravelMemoary/FriendsTravel/）
	 * @param map 分页的page和count
	 * @return Model列表
	 */
	static public List<Model> selectAll(String modelName,Map<String, Integer>map){
		
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(ModelsFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		List<Model> models = null;
		if(modelName == User.ModelName_STR){		
			UserMapper mapper = session.getMapper(UserMapper.class);
			models = mapper.selectAll(map);
			
		}else if(modelName ==TravelMemoary.ModelName_STR ){
			TravelMemoaryMapper mapper = session.getMapper(TravelMemoaryMapper.class);
			models = mapper.selectAll(map);
		}else if(modelName == Spot.ModelName_STR){
			SpotMapper mapper = session.getMapper(SpotMapper.class);
			models = mapper.selectAll(map);
		}else if(modelName == FriendsTravel.ModelName_STR){
			FriendsTravelMapper mapper = session.getMapper(FriendsTravelMapper.class);
			models = mapper.selectAll(map);
		}
		session.close();
		return models;
		
	}
		
	/**
	 * 说明：新建一个Model
	 * @param modelName 从url中获取到的Model的类型（User/Spot/TravelMemoary/FriendsTravel/Friend/Comment）
	 * @param model 
	 * @return int 判断是否新建成功
	 */

	static public int insert(String modelName,Model model){
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(ModelsFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		int flag = 0;
		
		
		if(modelName == User.ModelName_STR){
			UserMapper mapper = session.getMapper(UserMapper.class);
			flag = mapper.insertSelective((User)model);
		}else if(modelName == TravelMemoary.ModelName_STR){
			TravelMemoaryMapper mapper = session.getMapper(TravelMemoaryMapper.class);
			flag = mapper.insertSelective((TravelMemoary)model);
		}else if(modelName == Spot.ModelName_STR){
			SpotMapper mapper = session.getMapper(SpotMapper.class);
			flag = mapper.insertSelective((Spot)model);
		}else if(modelName == FriendsTravel.ModelName_STR){
			FriendsTravelMapper mapper = session.getMapper(FriendsTravelMapper.class);
			flag = mapper.insert((FriendsTravel)model);
		}else if(modelName == Friend.ModelName_STR){
			FriendMapper mapper = session.getMapper(FriendMapper.class);
			flag = mapper.insertSelective((Friend)model);
		}else if(modelName == Comment.ModelName_STR){
			CommentMapper mapper = session.getMapper(CommentMapper.class);
			flag = mapper.insertSelective((Comment)model);
		}
		
		session.commit();
		session.close();
		return flag;
	}
	
	/**
	 * 说明：更新信息
	 * @param modelName 从url中获取到的Model的类型（User/Spot/TravelMemoary/FriendsTravel/Friend/Comment）
	 * @param model
	 * @return int 判断更新是否成功
	 */
	static public int updateByPrimaryKey(String modelName, Model model){
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(ModelsFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		int flag = 0;
		
		if(modelName.equals(User.ModelName_STR)){
			
			UserMapper mapper = session.getMapper(UserMapper.class);
			flag = mapper.updateByPrimaryKeySelective((User)model);	
		}else if(modelName.equals(TravelMemoary.ModelName_STR)){
			TravelMemoaryMapper mapper = session.getMapper(TravelMemoaryMapper.class);
			flag = mapper.updateByPrimaryKeySelective((TravelMemoary)model);
			
		}else if(modelName.equals(Spot.ModelName_STR)){		
			SpotMapper mapper = session.getMapper(SpotMapper.class);
			flag = mapper.updateByPrimaryKeySelective((Spot)model);
			
		}else if(modelName.equals(FriendsTravel.ModelName_STR)){	
			FriendsTravelMapper mapper = session.getMapper(FriendsTravelMapper.class);
			flag = mapper.updateByPrimaryKeySelective((FriendsTravel)model);
			
		}else if(modelName.equals(Friend.ModelName_STR)){	
			FriendMapper mapper = session.getMapper(FriendMapper.class);
			flag = mapper.updateByPrimaryKeySelective((Friend)model);
			
		}else if(modelName.equals(Comment.ModelName_STR)){
			CommentMapper mapper = session.getMapper(CommentMapper.class);
			flag = mapper.updateByPrimaryKeySelective((Comment)model);
		}
		session.commit();
		Field status;
		try {
			status = model.getClass().getDeclaredField("status");
			status.setAccessible(true);
			System.out.println( "====================" + status.get(model) + flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("update commited");
		session.close();
		return flag;
	}
	
	/**
	 * 说明：将map中的内容，整合成一个Model
	 * @param modelName 从url中获取到的Model的类型（User/Spot/TravelMemoary/FriendsTravel/Friend/Comment）
	 * @param map 从网页中获取到的信息
	 * @return Model
	 * @throws ParseException
	 */
	
	static public Model create(String modelName, Map<String , String[]> map) throws ParseException{
		Model model = null;
		String idString = StringTool.getIndexValueOrDefault(map.get("id"), 0, null);
		Integer id  = StringTool.changeToIntegerOrDefault(idString, null);
		if(modelName == Spot.ModelName_STR){
			Spot spotModel = new Spot();
			// id
			spotModel.setId(id);
			
			// status 
			spotModel.setStatus(Status.getStatusCode(Status.ACTIVE));
			
			// content
			spotModel.setContent(StringTool.getIndexValueOrDefault(map.get("content"), 0, null));
			
			// name
			spotModel.setSpotName(StringTool.getIndexValueOrDefault(map.get("spot_name"), 0, null));
			
			// type
			String objectTypeString = StringTool.getIndexValueOrDefault(map.get("object_type"), 0, null);
			spotModel.setType(StringTool.changeToIntegerOrDefault(objectTypeString, SpotType.Default.getTypeCode()));
			
			//city
			String cityString = StringTool.getIndexValueOrDefault(map.get("city"), 0, null);
			spotModel.setCity(StringTool.changeToIntegerOrDefault(cityString, SpotCity.Default.getTypeCode()));
			
			
			//SuitPeople
			spotModel.setSuitPople(StringTool.getIndexValueOrDefault(map.get("suit_pople"), 0, null));
			
			//SuitTime
			spotModel.setSuitTime(StringTool.getIndexValueOrDefault(map.get("suit_time"), 0, null));
		
		    //NeedThing
			spotModel.setNeedThing(StringTool.getIndexValueOrDefault(map.get("need_thing"), 0, null));
			
		    //Attentions
			spotModel.setAttentions(StringTool.getIndexValueOrDefault(map.get("attentions"), 0, null));
		    
		    //Detail
			spotModel.setDetail(StringTool.getIndexValueOrDefault(map.get("detail"), 0, null));
		    
		    //LikeCount
			String likeCountString = StringTool.getIndexValueOrDefault(map.get("likeCount"), 0, null);
			spotModel.setLikeCount(StringTool.changeToIntegerOrDefault(likeCountString, 0));
		   
		    //Temp
			spotModel.setTemp(StringTool.getIndexValueOrDefault(map.get("image_path"), 0, null));	
			
			model = spotModel;
		} else if(modelName == TravelMemoary.ModelName_STR){
			TravelMemoary travleMemoaryModel = new TravelMemoary();
				
			    // Status
				travleMemoaryModel.setStatus(Status.getStatusCode(Status.ACTIVE));
				
			    // id
				travleMemoaryModel.setId(id);
				
			    //Title
				String titleString = StringTool.getIndexValueOrDefault(map.get("title"), 0, null);
				travleMemoaryModel.setTitle(titleString);
			    
			    //AuthId
				String authIdString = StringTool.getIndexValueOrDefault(map.get("authId"), 0, null);
			    travleMemoaryModel.setAuthId(StringTool.changeToIntegerOrDefault(authIdString, IdTools.Default.getTypeCode()));
			    
			    //Content
			    String content = StringTool.getIndexValueOrDefault(map.get("content"), 0, null);
			    travleMemoaryModel.setContent(content);
			    
			    //CreateDate
			    travleMemoaryModel.setCdate(new Date(System.currentTimeMillis()));
			    
			    //ImagePath
			    String path = StringTool.getIndexValueOrDefault(map.get("imagePath"), 0, null);
			    travleMemoaryModel.setImagePath(path);
				
			    //LikeCount
			    String likeCountString = StringTool.getIndexValueOrDefault(map.get("likeCount"), 0, null);
			    travleMemoaryModel.setLikeCount(StringTool.changeToIntegerOrDefault(likeCountString, 0));
			    
			    //Temp
			    String temp = StringTool.getIndexValueOrDefault(map.get("type_name"), 0, null);
			    travleMemoaryModel.setTemp(temp);
			    
			    model = travleMemoaryModel;
		}else if(modelName == FriendsTravel.ModelName_STR){
			
			FriendsTravel friendsTravelmodel = new FriendsTravel();
				
			// id
			friendsTravelmodel.setId(id);
			
			// status 
			friendsTravelmodel.setStatus(Status.getStatusCode(Status.ACTIVE));
			
			//UserId
			String authIdString = StringTool.getIndexValueOrDefault(map.get("userId"), 0, null);
			friendsTravelmodel.setUserId(StringTool.changeToIntegerOrDefault(authIdString,IdTools.Default.getTypeCode()));


			//SpotId
			String spotIdString = StringTool.getIndexValueOrDefault(map.get("spoId"), 0, null);
			friendsTravelmodel.setSpotId(StringTool.changeToIntegerOrDefault(spotIdString, IdTools.Default.getTypeCode()));

			
			// Title
			String titleString = StringTool.getIndexValueOrDefault(map.get("title"), 0, null);
			friendsTravelmodel.setTitle(titleString);
		    
			
			// Createdate
			friendsTravelmodel.setCreateTime(new Date(System.currentTimeMillis()));
			
			
			// From date

			String dateString = StringTool.getIndexValueOrDefault(map.get("fromTime"), 0, null);
			if(dateString != null){
				friendsTravelmodel.setFromdate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
			}else{
				friendsTravelmodel.setFromdate(new Date(System.currentTimeMillis()));
			}

			
			
			// Todate

			dateString = StringTool.getIndexValueOrDefault(map.get("toTime"), 0, null);
			if(dateString != null){
				friendsTravelmodel.setTodate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
			}else{
				friendsTravelmodel.setTodate(new Date(System.currentTimeMillis()));
			}	


		    
			// Content
			String contentString = StringTool.getIndexValueOrDefault(map.get("content"), 0, null);
			friendsTravelmodel.setContent(contentString);
			
			// Distination
			String distination = StringTool.getIndexValueOrDefault(map.get("distination"), 0, null);
			friendsTravelmodel.setTemp(distination);
			
			model = friendsTravelmodel;
		}else if(modelName == Comment.ModelName_STR){
			Comment commentmodel = new Comment();
			
			// id
			commentmodel.setId(id);
		
		  	// status 
			commentmodel.setStatus(Status.getStatusCode(Status.ACTIVE));

			//Auth_id
			String authIdString = StringTool.getIndexValueOrDefault(map.get("authId"), 0, null);
			commentmodel.setAuthId(StringTool.changeToIntegerOrDefault(authIdString, IdTools.Default.getTypeCode()));

			//ObjectType
			String objectTypeString = StringTool.getIndexValueOrDefault(map.get("objectType"), 0, null);
			commentmodel.setObjectType(StringTool.changeToIntegerOrDefault(objectTypeString, IdTools.Default.getTypeCode()));
			
			//ObjectId
			String objectIdString = StringTool.getIndexValueOrDefault(map.get("objectId"), 0, null);
			commentmodel.setObjectId(StringTool.changeToIntegerOrDefault(objectIdString, SpotType.Default.getTypeCode()));


		    //Title
			commentmodel.setTitle(StringTool.getIndexValueOrDefault(map.get("title"), 0, null));
		    
		    //Comment
			String commentString = StringTool.getIndexValueOrDefault(map.get("comment"), 0, null);
			commentmodel.setComment(commentString);

			//CreateTime
			commentmodel.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
		    
			//Temp
			commentmodel.setTemp(StringTool.getIndexValueOrDefault(map.get("temp"), 0, null));
		    
			model = commentmodel;
		}else if(modelName == Friend.ModelName_STR){
			Friend friendmodel = new Friend();
			
			// id
			friendmodel.setId(id);
			
			//From
			String fromIdString = StringTool.getIndexValueOrDefault(map.get("from"), 0, null);
			friendmodel.setFrom(StringTool.changeToIntegerOrDefault(fromIdString, IdTools.Default.getTypeCode()));
			

			//To
			String toIdString = StringTool.getIndexValueOrDefault(map.get("to"), 0, null);
			friendmodel.setTo(StringTool.changeToIntegerOrDefault(toIdString, IdTools.Default.getTypeCode()));

			
			//CreateTime
			friendmodel.setCreateTime(new Date(System.currentTimeMillis()));
			
			model = friendmodel;
		}else if(modelName == User.ModelName_STR){
			User user = new User();
			
			user.setId(id);
			
		    user.setStatus(Status.getStatusCode(Status.ACTIVE));


			String name = StringTool.getIndexValueOrDefault(map.get("name"), 0, null);
			user.setNickname(name);
			
			String password = StringTool.getIndexValueOrDefault(map.get("password"), 0, null);
			user.setPassword(password);


		    String email = StringTool.getIndexValueOrDefault(map.get("email"), 0, null);
		    user.setEmail(email);
		    

		    String qq = StringTool.getIndexValueOrDefault(map.get("qq"), 0, null);
		    user.setQq(qq);


		    String address = StringTool.getIndexValueOrDefault(map.get("address"), 0, null);
		    user.setAddress(address);
		    
		    
		    String tel = StringTool.getIndexValueOrDefault(map.get("tel"), 0, null);
		    user.setTel(tel);
		    
		    SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
		    String dateString = StringTool.getIndexValueOrDefault(map.get("birthday"), 0, null);
		    if (dateString != null) {
		    	System.out.println(dateString);
		    	Date birthday = stf.parse(dateString);
		    	user.setBirthday(birthday);
		    }else{
		    	user.setBirthday(null);
		    }
		    
		    

		    String imagepath = StringTool.getIndexValueOrDefault(map.get("imagepath"), 0, null);
		    user.setImagepath(imagepath);
		   

		    user.setCdate(new Date(System.currentTimeMillis()));

		    String sex = StringTool.getIndexValueOrDefault(map.get("gender"), 0, null);
		    user.setSex(StringTool.changeToIntegerOrDefault(sex, UserSex.getSexCode(UserSex.UNKNOW)));
		    
		    
		    user.setLdate(new Date(System.currentTimeMillis()));
		    
			String temp = StringTool.getIndexValueOrDefault(map.get("temp"), 0, null);
			user.setTemp(temp);
			
			model = user;
		}
		return model;
	}
	
	/**
	 * 说明：通过ID 遍历好友列表
	 * @param id
	 * @return 好友列表
	 */
	static public List<Friend> friendsList(Integer id){
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(ModelsFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		FriendMapper mapper = session.getMapper(FriendMapper.class);
		List<Friend> friends = mapper.friendsList(id);

		return friends;
	}
}
