package com.sea.auth.client;

import api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 */

    @FeignClient(value = "upms-service")
    public interface UserClient extends UserApi {
    }

