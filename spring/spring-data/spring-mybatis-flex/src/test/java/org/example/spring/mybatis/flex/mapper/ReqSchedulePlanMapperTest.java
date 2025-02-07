package org.example.spring.mybatis.flex.mapper;

import com.mybatisflex.core.query.QueryWrapper;
import org.example.spring.mybatis.flex.SpringMybatisMainApplication;
import org.example.spring.mybatis.flex.entity.ReqSchedulePlan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.example.spring.mybatis.flex.entity.table.ReqSchedulePlanTableDef.REQ_SCHEDULE_PLAN;

/**
 * @author anyuan
 * @date 2023-07-12 18:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMybatisMainApplication.class)
public class ReqSchedulePlanMapperTest {

    @Resource
    private ReqSchedulePlanMapper reqSchedulePlanMapper;

    @Test
    public void testQuery() {
        final ReqSchedulePlan reqSchedulePlan = reqSchedulePlanMapper.selectOneByQuery(QueryWrapper.create()
                .where(REQ_SCHEDULE_PLAN.ID.ge(0L))
                .limit(1));
        System.out.println(reqSchedulePlan);
    }
}