package utils.database;

/**
 * 文件名称: TabDatabaseBackup.java
 * 编写人: yh.zeng
 * 编写时间: 13-11-7
 * 文件描述: tab_database_backup表对应的实体类
 */
public class TabDatabaseBackup
{

    private String id;

    private String filename;  //备份文件名

    private String time;      //备份时间


    public String getFilename()
    {
        return filename;
    }

    public void setFilename( String filename )
    {
        this.filename = filename;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime( String time )
    {
        this.time = time;
    }
}
