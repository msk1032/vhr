package org.studyhub.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyhub.vhr.mapper.JobLevelMapper;
import org.studyhub.vhr.model.JobLevel;

import java.util.Date;
import java.util.List;

/**
 * @author haoren
 * @create 2023-02-26 13:02
 */

@Service
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    public Integer addJobLevel(JobLevel jobLevel) {

        jobLevel.setEnabled(true);
        jobLevel.setCreateDate(new Date());
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public Integer updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deleteJobLevelById(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteJobLevelsByIds(Integer[] ids) {
        return jobLevelMapper.deleteJobLevelByIds(ids);
    }
}
