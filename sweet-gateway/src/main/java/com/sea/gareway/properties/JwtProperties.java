package com.sea.gareway.properties;

import com.sea.auth.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author bystander
 * @date 2018/10/1
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "sea.jwt")
public class JwtProperties {


    private String pubKeyPath; //公钥地址
    private String cookieName;
    private PublicKey publicKey; // 公钥key

    @PostConstruct
    public void init() {
        try {
            //获取公钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            log.error("获取公钥私钥失败",e);
            throw new RuntimeException();
        }
    }
}
