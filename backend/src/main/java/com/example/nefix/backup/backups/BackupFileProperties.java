package com.example.nefix.backup.backups;

import org.springframework.beans.factory.annotation.Value;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "backup.file")
public class BackupFileProperties
{
    @Value("${backup.file.path}")
    private String backupFilePath;

    @Value("${backup.file.name}")
    private String backupFileName;

    @Value("${spring.datasource.url}")
    private String dbHost;

    @Value("${spring.datasource.port}")
    private String dbPort;

    @Value("${spring.application.name}")
    private String dbName;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

}
