package cn.edu.dlnu.daos;

import java.util.List;
import java.util.Map;

import cn.edu.dlnu.models.Friend;
import cn.edu.dlnu.models.Model;

public interface FriendMapper  extends Mapper{
    int delete(Friend record);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
    
    List<Model> selectAll(Map<String,Integer> map);
    
    List<Friend> friendsList(Integer id);
    
}