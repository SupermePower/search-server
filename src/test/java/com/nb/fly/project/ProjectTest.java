package com.nb.fly.project;

import com.nb.fly.mapper.ProjectMapper;
import com.nb.fly.model.Project;
import com.nb.fly.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author fly
 * @description 项目测试
 * @date 2019/5/7 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void saveTest() {
        List<Project> allProjects = projectMapper.allProjects();
        for (Project project : allProjects) {
            projectService.save(project);
        }
    }
}
