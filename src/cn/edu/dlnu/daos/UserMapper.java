package cn.edu.dlnu.daos;

import java.util.List;
import java.util.Map;

import cn.edu.dlnu.models.Model;
import cn.edu.dlnu.models.User;



public interface UserMapper  extends Mapper {
    int delete(User record);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User  selectByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<Model> selectAll(Map<String,Integer> map);
    
}