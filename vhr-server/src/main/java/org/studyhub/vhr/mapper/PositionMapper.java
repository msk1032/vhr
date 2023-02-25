package org.studyhub.vhr.mapper;


import org.apache.ibatis.annotations.Param;
import org.studyhub.vhr.model.Position;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPosition();

    Integer addPosition(Position position);

    Integer deletePositionsByIds(@Param("ids") Integer[] ids);
}