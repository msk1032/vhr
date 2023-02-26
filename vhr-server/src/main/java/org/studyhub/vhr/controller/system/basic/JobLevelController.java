package org.studyhub.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.studyhub.vhr.model.JobLevel;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.JobLevelService;

import java.util.List;

/**
 * @author haoren
 * @create 2023-02-26 13:00
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public RespBean getAllJobLevels() {
        List<JobLevel> list =  jobLevelService.getAllJobLevels();
        return RespBean.ok("", list);
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
        if(jobLevelService.addJobLevel(jobLevel) == 1) {
            return RespBean.ok("add jobLevel success");
        }
        return RespBean.error("add jobLevel fail");
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        int res = jobLevelService.updateJobLevel(jobLevel);
        if (res == 1) {
            return RespBean.ok("update jobLevel success");
        }
        return RespBean.error("update jobLevel fail");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id){
        int res = jobLevelService.deleteJobLevelById(id);
        if (res == 1) {
            return RespBean.ok("delete jobLevel success");
        }
        return RespBean.error("delete jobLevel fail");

    }

    @PostMapping("/many")
    public RespBean deleteJobLevelsByIds(@RequestBody Integer[] ids) {

        if(jobLevelService.deleteJobLevelsByIds(ids)==ids.length){
            return RespBean.ok("delete success");
        }
        return RespBean.error("delete fail");
    }


}
