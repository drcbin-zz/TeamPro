package cn.edu.dlnu.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sun.org.apache.xpath.internal.operations.String;

import cn.edu.dlnu.daos.CommentMapper;
import cn.edu.dlnu.daos.FriendsTravelMapper;
import cn.edu.dlnu.daos.TravelMemoaryMapper;
import cn.edu.dlnu.daos.UserMapper;
import cn.edu.dlnu.models.Comment;
import cn.edu.dlnu.models.Friend;
import cn.edu.dlnu.models.FriendsTravel;
import cn.edu.dlnu.models.Model;
import cn.edu.dlnu.models.ModelsFactory;
import cn.edu.dlnu.models.TravelMemoary;
import cn.edu.dlnu.models.User;
import cn.edu.dlnu.tools.Status;



public class MainTest {
	
	public static void main(String[] args) {
<<<<<<< .mine

		Model model = ModelsFactory.selectByPrimaryKey(FriendsTravel.ModelName_STR, 30001);
		int flag = ModelsFactory.delete_status(FriendsTravel.ModelName_STR,model);
=======

		Comment oldComment = new Comment();
		Comment newComment = new Comment();
		newComment.setTitle("Hello");
		oldComment = (Comment) ModelsFactory.updateWithModel(oldComment, newComment);
		

		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(MainTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		
		user = mapper.selectByPrimaryKey(10200);
		System.out.println(user.getStatus());
		user.setStatus(Status.getStatusCode(Status.DELETE));
		System.out.println(user.getStatus());
		int flag = mapper.delete(user);
		session.commit();
		System.out.println(flag);
		session.close();	


>>>>>>> .r185
	}
}

