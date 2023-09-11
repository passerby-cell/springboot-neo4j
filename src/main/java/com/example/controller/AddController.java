package com.example.controller;

import com.example.entity.MovieEntity;
import com.example.entity.PersonEntity;
import com.example.entity.Roles;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.query.QueryFragmentsAndParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/test")
public class AddController {
    @Autowired
    private Neo4jTemplate neo4jTemplate;

    @GetMapping("/add")
    public Result TestNoRepository(@RequestParam("number") int number) {
        // 删除所有节点和关系（删除节点会相应删除关联关系），避免后续创建节点重复影响
        neo4jTemplate.deleteAll(MovieEntity.class);
        neo4jTemplate.deleteAll(PersonEntity.class);

        // 创建节点实体
        MovieEntity movie = new MovieEntity("压力测试", "压力测试--节点数");

        // new Roles 参数1：Person实体，演员的出生年和姓名；参数2：演员名字列表（考虑到一个演员可能参演多个角色）
        // 参数1是目标关系实体节点 参数2是关系属性
        for (int i = 0; i < number; i++) {
            Roles roles1 = new Roles(new PersonEntity(i, String.valueOf(i)), Collections.singletonList("child node"));
            // 添加movie的演员实体，加入（参演）关系
            movie.getActorsAndRoles().add(roles1);
            // 存入图数据库持久化
        }
        neo4jTemplate.save(movie);
        return new Result(true, 200, "添加成功", null);
    }


    @GetMapping("/delete")
    public Result deleteNoRepository() {
        // 删除所有节点和关系（删除节点会相应删除关联关系），避免后续创建节点重复影响
        neo4jTemplate.deleteAll(MovieEntity.class);
        neo4jTemplate.deleteAll(PersonEntity.class);
        return new Result(true, 200, "删除成功", null);
    }
}