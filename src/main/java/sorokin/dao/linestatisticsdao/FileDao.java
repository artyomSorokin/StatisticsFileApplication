package sorokin.dao.linestatisticsdao;


import sorokin.models.FileEntity;
import sorokin.models.LineEntity;

import java.sql.SQLException;
import java.util.List;

public interface FileDao {

    Integer insertIntoLineTable(String fileName, String query, List<LineEntity> lineEntityList) throws SQLException;

    Integer insertIntoFileTable(FileEntity fileEntity) throws SQLException;

    void createTableLineTable() throws SQLException;

    void clearDb() throws SQLException;

    void createTableFileTable() throws SQLException;

}
