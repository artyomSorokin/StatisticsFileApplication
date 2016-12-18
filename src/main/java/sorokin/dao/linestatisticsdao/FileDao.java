package sorokin.dao.linestatisticsdao;


import sorokin.entity.FileEntity;
import sorokin.entity.LineEntity;
import java.util.List;

public interface FileDao {

    public Integer insertIntoLineTable(String fileName, String query, List<LineEntity> lineEntityList);

    public Integer insertIntoFileTable(FileEntity fileEntity);

    public void createTableLineTable();

    public void clearDb();

    public void createTableFileTable();

}
