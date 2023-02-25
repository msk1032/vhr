package org.studyhub.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.studyhub.vhr.model.Position;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.PositionService;

import java.util.List;

/**
 * @author haoren
 * @create 2023-02-25 13:19
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public RespBean getPositionList() {
        List<Position> list = positionService.getAllPosition();
        return RespBean.ok("", list);
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        int res = positionService.addPosition(position);
        if (res == 1) {
            return RespBean.ok("add position success");
        }
        return RespBean.error("add position fail");
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        int res = positionService.updatePosition(position);
        if (res == 1) {
            return RespBean.ok("update position success");
        }
        return RespBean.error("update position fail");
    }
    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id){
        int res = positionService.deletePositionById(id);
        if (res == 1) {
            return RespBean.ok("delete position success");
        }
        return RespBean.error("delete position fail");

    }

    @DeleteMapping("/")
    public RespBean deletePositionsByIds(@RequestBody Integer[] ids) {

        if(positionService.deletePositionsByIds(ids)==ids.length){
            return RespBean.ok("delete success");
        }
        return RespBean.error("delete fail");
    }

}


