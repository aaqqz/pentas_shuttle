package com.card.shuttle.common.conf;

import com.card.shuttle.common.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
	CustomInterceptor customInterceptor;
    
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/resources/**") // 예외처리 대상.
        .excludePathPatterns("/api/**") // 예외처리 대상.
        .excludePathPatterns("/static/**");

		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
		registry.addResourceHandler("/publisher/**")
			.addResourceLocations("/publisher/");
		registry.addResourceHandler("/test/**")
			.addResourceLocations("/test/");
		registry.addResourceHandler("/css/**")
			.addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/img/**")
			.addResourceLocations("classpath:/static/img/");
		registry.addResourceHandler("/js/**")
			.addResourceLocations("classpath:/static/js/");
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);
		return sessionLocaleResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
}
