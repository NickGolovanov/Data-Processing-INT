package com.example.nefix.backup.backups;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Service //why service ?
public class DatabaseBackupService
{
    private final BackupFileProperties backupFileProperties;

    public DatabaseBackupService(BackupFileProperties backupFileProperties)
    {
        this.backupFileProperties = backupFileProperties;
    }

    public void performBackup() {
        String backupDir = this.backupFileProperties.getBackupFilePath();
        String backupFileName = this.backupFileProperties.getBackupFileName();
        String dbHost = this.backupFileProperties.getDbHost();
        String dbPort = this.backupFileProperties.getDbPort();
        String dbName = this.backupFileProperties.getDbName();
        String dbUser = this.backupFileProperties.getDbUser();
        String dbPassword = this.backupFileProperties.getDbPassword();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fullPath = backupDir + backupFileName + "_" + timestamp + ".sql";

        String command = String.format(
                "%s pg_dump -h %s -p %s -U %s -d %s -F c -b -v -f %s",
                dbPassword, dbHost, dbPort, dbUser, dbName, fullPath
        );

        try {
            // Ensure backup directory exists
            Files.createDirectories(Paths.get(backupDir));

            // Run the backup command
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup successful! File: " + fullPath);
            } else {
                System.err.println("Backup failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during backup: " + e.getMessage());
        }
    }
}
