package io.yigitucun.eventflow;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "io.yigitucun.eventflow.exception",
        "io.yigitucun.eventflow.message",
        "io.yigitucun.eventflow.utils",
        "io.yigitucun.eventflow.security"
})
public class CoreLibConfiguration {
}
