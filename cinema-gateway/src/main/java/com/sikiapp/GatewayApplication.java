package com.sikiapp;

import com.sikiapp.cinema.user.api.UserService;
import com.sikiapp.gateway.properties.RestProperties;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@EnableDubboConfig
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class GatewayApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayApplication.class)
				.run(args);
	}

	@Component
	public class UserRpcService implements CommandLineRunner {

		@Reference
		private UserService userService;

		@Override
		public void run(String... args) throws Exception {
			System.out.println("发起一次 Dubbo RPC 请求...");
			userService.login("admin", "admin123");
		}
	}

    @Component
    public class PropertiesTest implements CommandLineRunner {

        @Autowired
        private RestProperties properties;

        @Override
        public void run(String... args) throws Exception {
            Field[] fields = properties.getClass().getFields();
            for (Field field : fields) {
                System.out.println(String.format("字段：%s", field.getName()));
            }

            Method[] methods = properties.getClass().getMethods();
            for (Method method : methods) {
                System.out.println("方法：" + method);
            }
        }
    }




}
