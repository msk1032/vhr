package org.studyhub.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyhub.vhr.mapper.PositionMapper;
import org.studyhub.vhr.model.Position;

import java.util.Date;
import java.util.List;

/**
 * @author haoren
 * @create 2023-02-25 13:24
 */
@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPosition() {
        List<Position> list = positionMapper.getAllPosition();

        return list;
    }

    public Integer addPosition(Position position) {

        position.setEnabled(true);
        position.setCreateDate(new Date());
        return  positionMapper.insertSelective(position);
    }

    public Integer updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePositionById(Integer id) {

        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionsByIds(Integer[] ids) {
        return positionMapper.deletePositionsByIds(ids);
    }
}
