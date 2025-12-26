package io.yigitucun.eventflow;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "io.yigitucun.eventflow.handler",
        "io.yigitucun.eventflow.aop",
        "io.yigitucun.eventflow.aspect"
})
public class MvcLibConfig {
}
