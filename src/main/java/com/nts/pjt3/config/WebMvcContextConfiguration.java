package com.nts.pjt3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nts.pjt3.interceptor.LogInterceptor;
import com.nts.pjt3.util.ImageUtil;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.nts.pjt3.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/img_map/**").addResourceLocations("file:///C:/reservation/img_map/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/img_review/**").addResourceLocations("file:///C:/reservation/img_review/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/font/**").addResourceLocations("/font/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/")
			.setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor());
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(ImageUtil.MAX_IMAGE_SIZE);
		return multipartResolver;
	}
	
}