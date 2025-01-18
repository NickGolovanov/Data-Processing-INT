package com.example.nefix.backup;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("{backup.file.db-host}")
    private String dbHost;

    @Value("${spring.datasource.port}")
    private String dbPort;

    @Value("${backup.file.db-name}")
    private String dbName;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

}
