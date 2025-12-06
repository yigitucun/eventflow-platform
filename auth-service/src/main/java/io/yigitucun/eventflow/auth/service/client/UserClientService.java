package io.yigitucun.eventflow.auth.service.client;

import io.yigitucun.eventflow.auth.service.dto.client.responses.UserAuth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClientService {

    @GetMapping("/api/users/username/{username}")
    UserAuth findUserByUsername(@PathVariable("username") String username);
}
