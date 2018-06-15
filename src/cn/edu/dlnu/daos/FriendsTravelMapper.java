package cn.edu.dlnu.daos;

import java.util.List;
import java.util.Map;

import cn.edu.dlnu.models.FriendsTravel;
import cn.edu.dlnu.models.Model;

public interface FriendsTravelMapper  extends Mapper{


    int insert(FriendsTravel record);

    int insertSelective(FriendsTravel record);

    FriendsTravel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendsTravel record);

    int updateByPrimaryKey(FriendsTravel record);
    
    List<Model> selectByAuthId(Map<String,Integer> map);
    
    List<Model> selectAll(Map<String,Integer> map);

	int delete_status(Model model);
}