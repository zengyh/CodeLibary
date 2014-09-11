package utils.database;

import java.util.List;

/**
 * 文件名称: IDatabaseBackupService.java
 * 编写人: yh.zeng
 * 编写时间: 13-11-7
 * 文件描述: todo
 */
public interface IDatabaseBackupService
{

    /**
     * 备份数据库的记录存在tab_database_backup表
     * @param fileName  备份的文件名
     * @param time      备份时间
     */
    public  void saveDatabaseBackup(String fileName, String time);


    /**
     * 删除备份
     * @param databaseBackups
     */
    public void deleteDatabaseBackups(List<TabDatabaseBackup> databaseBackups);
}
