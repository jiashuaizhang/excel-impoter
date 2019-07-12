package com.unicom.eos.codebuysync.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "import.writeErrorFile")
public class ErrorFileConfig {

    private String analysisErrorFile;
    private String saveErrorFile;
    private String noIndexFile;
    private String duplicateKeyFile;
    private boolean enabled;

    public static String analysisErrorFile_;
    public static String saveErrorFile_;
    public static String noIndexFile_;
    public static String duplicateKeyFile_;
    public static boolean enabled_;

    @PostConstruct
    public void afterInit(){
        ErrorFileConfig.analysisErrorFile_ = this.analysisErrorFile;
        ErrorFileConfig.saveErrorFile_ = this.saveErrorFile;
        ErrorFileConfig.noIndexFile_ = this.noIndexFile;
        ErrorFileConfig.duplicateKeyFile_ = this.duplicateKeyFile;
        ErrorFileConfig.enabled_ = this.enabled;
    }

}
