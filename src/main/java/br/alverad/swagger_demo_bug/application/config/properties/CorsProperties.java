package br.alverad.swagger_demo_bug.application.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    /**
     * Allowed origin patterns
     */
    private String[] allowedOriginPatterns;

    public String[] getAllowedOriginPatterns() {
        return allowedOriginPatterns;
    }

    public void setAllowedOriginPatterns(String[] allowedOriginPatterns) {
        this.allowedOriginPatterns = allowedOriginPatterns;
    }
}
