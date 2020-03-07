/**
 * projectName: dubbo-cinema
 * fileName: MybatisPlusConfig.java
 * packageName: com.sikiapp.user.config
 * date: 2020-03-06 下午5:29
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.user.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: MybatisPlusConfig
 * @packageName: com.sikiapp.user.config
 * @description:
 * @author: Robert
 * @data: 2020-03-06 下午5:29
 * @version: V1.0
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.sikiapp.user.mapper")
public class MybatisPlusConfig {

    /**
     *@description: 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setDialectType("mysql");
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链（阻止恶意的全表更新删除）
        sqlParserList.add(new BlockAttackSqlParser());
        return interceptor;
    }

    /**
     * @description: SQL执行效率插件
     * @profile 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

}