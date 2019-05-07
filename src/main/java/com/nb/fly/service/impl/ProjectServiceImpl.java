package com.nb.fly.service.impl;

import com.nb.fly.model.Project;
import com.nb.fly.repository.ProjectRepository;
import com.nb.fly.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fly
 * @description 项目业务
 * @date 2019/5/7 10:21
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * 保存项目信息
     *
     * @param project 项目信息
     */
    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    /**
     * 获取项目信息
     *
     * @param projectName 项目名称
     * @return 项目信息
     */
    @Override
    public List<Project> findByName(String projectName) {
        return null;
    }
}