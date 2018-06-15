package cn.edu.dlnu.daos;



import java.util.List;
import java.util.Map;

import cn.edu.dlnu.models.Model;
import cn.edu.dlnu.models.TravelMemoary;

public interface TravelMemoaryMapper extends Mapper{
    int delete(TravelMemoary record);

    int insert(TravelMemoary record);

    int insertSelective(TravelMemoary record);

    TravelMemoary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TravelMemoary record);

    int updateByPrimaryKey(TravelMemoary record);
    
    List<Model> selectByAuthId(Map<String, Integer> map);

    List<Model> selectAll(Map<String,Integer> map);
}