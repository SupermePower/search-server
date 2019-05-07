package com.nb.fly.repository;

import com.nb.fly.model.Project;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liying.fu
 * @description 项目数据仓库
 * @date 2019/5/6 下午11:23
 */
@Repository
public interface ProjectRepository extends ElasticsearchRepository<Project, Long> {

    /**
     * 根据项目名称获取项目信息
     *
     * @param projectName 项目名称
     * @return 项目信息
     */
    Project findByProjectName(String projectName);
}
