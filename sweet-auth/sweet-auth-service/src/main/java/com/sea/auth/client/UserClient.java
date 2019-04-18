package com.sea.auth.client;

import api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *sys/user/
 */

    @FeignClient(value="upms-service")
    public interface UserClient extends UserApi {
  }



