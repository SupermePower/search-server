package com.nb.fly.mapper;

import com.nb.fly.model.Project;
import com.nb.fly.model.ProjectEarlyWarningModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fly
 * @description 项目信息
 * @date 2019/5/7 13:28
 */
@Repository
public interface ProjectMapper {

    /**
     * 获取全部项目信息
     *
     * @return 项目信息
     */
    List<Project> allProjects();

    ProjectEarlyWarningModel findProjectEarlyWarningByProjectId(Long projectId);
}
