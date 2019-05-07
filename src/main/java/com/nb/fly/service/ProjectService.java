package com.nb.fly.service;

import com.nb.fly.model.Project;

import java.util.List;

/**
 * @author fly
 * @description 项目业务
 * @date 2019/5/7 10:20
 */
public interface ProjectService {

    /**
     * 保存项目信息
     *
     * @param project 项目信息
     */
    void save(Project project);

    /**
     * 根据项目名称获取项目信息
     *
     * @param projectName 项目名称
     * @return 项目集合
     */
    List<Project> findByName(String projectName);
}
