package com.jd.jr.chapter2.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * User: 吴海旭
 * Date: 2016-11-05
 * Time: 下午6:06
 */
@Configuration
@ComponentScan(basePackages = {"com.jd.jr.chapter2.web.service"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
public class RootConfiguration {
}
