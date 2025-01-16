package com.example.nefix.backup;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Data
@Component
public class BackupScheduler
{
    private final DatabaseBackupService databaseBackupService;

    public BackupScheduler(DatabaseBackupService databaseBackupService)
    {
        this.databaseBackupService = databaseBackupService;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void setDatabaseBackupService()
    {
        System.out.println("Starting backup... ");
        this.databaseBackupService.performBackup();
    }
}
