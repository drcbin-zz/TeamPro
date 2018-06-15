package cn.edu.dlnu.daos;

import java.util.List;
import java.util.Map;

import cn.edu.dlnu.models.Model;
import cn.edu.dlnu.models.Spot;

public interface SpotMapper {
	
    int delete(Spot record);

    int insert(Spot record);

    int insertSelective(Spot record);

    Spot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Spot record);

    int updateByPrimaryKey(Spot record);
    
    List<Model> selectAll(Map<String,Integer> map);
}