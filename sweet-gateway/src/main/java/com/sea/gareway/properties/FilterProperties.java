package com.sea.gareway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "sea.filter")
public class FilterProperties {
    public List<String> allowPaths;
}
