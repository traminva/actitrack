package com.actitrack;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.actitrack.config.WebConfig;
import com.actitrack.service.impl.ActiTrackService;

@Configuration
@ComponentScan({ "com.actitrack" })
public class App {
	
	public static void main(String[] args) 
    {
    	/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
    	new WebConfig(ctx.getBean(MiniTwitService.class));
        ctx.registerShutdownHook();*/
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    	new WebConfig(context.getBean(ActiTrackService.class));
    }
    
    
}
