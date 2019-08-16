
package com.ctevs.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 该注解指定项目为springboot，由此类当作程序入口
 * 自动配置 web 依赖的环境
 */

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
//@EnableScheduling // 这里，启用定时任务
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
//@MapperScan("com.ctevs.dao")
@ComponentScan(basePackages = "com.ctevs.*")
@EnableSwagger2
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
