package edu.ecit.service;

import edu.ecit.bean.Position;
import edu.ecit.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public int addPos(Position pos) {
        if (positionMapper.getPosByName(pos.getName()) != null) {
            return -1;
        }
        return positionMapper.addPos(pos);
    }

    public List<Position> getAllPos() {
        return positionMapper.getAllPos();
    }

    public boolean deletePosById(String pids) {
        String[] split = pids.split(",");
        return positionMapper.deletePosById(split) == split.length;
    }

    public int updatePosById(Position position) {
        return positionMapper.updatePosById(position);
    }

}
