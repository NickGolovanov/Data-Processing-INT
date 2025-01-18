package com.example.nefix.backup;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Service
public class DatabaseBackupService
{
    private final BackupFileProperties backupFileProperties;

    public DatabaseBackupService(BackupFileProperties backupFileProperties)
    {
        this.backupFileProperties = backupFileProperties;
    }

    public void performBackup()
    {
        String backupDir = this.backupFileProperties.getBackupFilePath();
        String backupFileName = this.backupFileProperties.getBackupFileName();
        String dbHost = this.backupFileProperties.getDbHost();
        String dbPort = this.backupFileProperties.getDbPort();
        String dbName = this.backupFileProperties.getDbName();
        String dbUser = this.backupFileProperties.getDbUser();
        String dbPassword = this.backupFileProperties.getDbPassword();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fullPath = backupDir + backupFileName + "_" + timestamp + ".sql";

        ProcessBuilder processBuilder = new ProcessBuilder(
                "pg_dump",
                "-h", dbHost,
                "-p", dbPort,
                "-U", dbPassword,
                "-d", dbName,
                "-f", fullPath
        );

        processBuilder.environment().put("PGPASSWORD", dbPassword);

        try
        {

            Path backupFodlder = Paths.get(backupDir);

            if (!Files.exists(backupFodlder))
            {
                Files.createDirectories(Paths.get(backupDir));
            }

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0)
            {
                System.out.println("Backup successful! File: " + fullPath);
            } else
            {
                System.err.println("Backup failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e)
        {
            System.err.println("Error during backup: " + e.getMessage());
        }
    }
}
