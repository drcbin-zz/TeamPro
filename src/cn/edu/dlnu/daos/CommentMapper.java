package cn.edu.dlnu.daos;

import java.util.List;
import java.util.Map;

import cn.edu.dlnu.models.*;

public interface CommentMapper extends Mapper{
    int delete(Comment record);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Model> selectByAuthId(Map<String, Integer> map);
    
    List<Model> selectAll(Map<String,Integer> map);
    
    List<Model> getComments(Integer id);
}