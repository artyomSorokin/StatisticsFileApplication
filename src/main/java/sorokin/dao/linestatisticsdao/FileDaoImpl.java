package sorokin.dao.linestatisticsdao;


import org.apache.log4j.Logger;
import sorokin.dao.connection.ConnectionFactory;
import sorokin.dao.connection.ConnectionFactoryC3P0;
import sorokin.entity.FileEntity;
import sorokin.entity.LineEntity;
import java.sql.*;
import java.util.List;

public class FileDaoImpl implements FileDao{

    private static final ConnectionFactory CONNECTION_FACTORY_C3P0 = new ConnectionFactoryC3P0();
    private Logger log = Logger.getLogger(FileDaoImpl.class);

    private static final String INSERT_LINE_SQL = "INSERT INTO lineStatistics(fileName, query, line, lineLength, longestWord, shortestWord, averageWordLength) values(?,?,?,?,?,?,?)";
    private static final String ClEAR_FILE_DB_SQL = "DELETE FROM FileStatistics";
    private static final String ClEAR_LINE_DB_SQL = "DELETE FROM lineStatistics";
    private static final String INSERT_FILE_SQL = "INSERT INTO FileStatistics(fileName, query, fileLength, longestWord, shortestWord, averageWordLength, countLine) values(?,?,?,?,?,?,?)";
    private static final String CREATE_LINE_TABLE = "CREATE table lineStatistics(id integer(15) AUTO_INCREMENT," +
            " fileName Text," +
            " query Text," +
            " line Text," +
            " lineLength integer," +
            " longestWord Text," +
            " shortestWord Text,"+
            " averageWordLength integer,"+
            " PRIMARY KEY(id))";

    private static final String CREATE_FILE_TABLE = "CREATE table fileStatistics(id integer(15) AUTO_INCREMENT," +
            " fileName Text," +
            " query Text," +
            " fileLength integer," +
            " longestWord Text," +
            " shortestWord Text,"+
            " averageWordLength integer,"+
            " countLine integer,"+
            " PRIMARY KEY(id))";

    public Integer insertIntoLineTable(String fileName, String query, List<LineEntity> lineEntityList) {
        try {
            log.info("Start insert into table linestatistics");
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(INSERT_LINE_SQL);
            for(LineEntity lineEntity : lineEntityList) {
                preparedStatement.setString(1, fileName);
                preparedStatement.setString(2, query);
                preparedStatement.setString(3, lineEntity.getLine());
                preparedStatement.setInt(4, lineEntity.getLineLength());
                preparedStatement.setString(5, lineEntity.getLongestWord());
                preparedStatement.setString(6, lineEntity.getShortestWord());
                preparedStatement.setInt(7, lineEntity.getAverageWordLength());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();

            preparedStatement.close();
            connection.close();
            log.info("End insert into table linestatistics");
        }
        catch (Exception e){
            log.error("Insert into table linestatistics didn't work");
            log.error(e.getMessage());
            return 0;
        }
        return 1;
    }

    public Integer insertIntoFileTable(FileEntity fileEntity){
        try {
            log.info("Start insert into table filestatistics");
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(INSERT_FILE_SQL);
            preparedStatement.setString(1, fileEntity.getAbsolutePath());
            preparedStatement.setString(2, fileEntity.getQuery());
            preparedStatement.setInt(3, fileEntity.getFileLength());
            preparedStatement.setString(4, fileEntity.getFileLongestWord());
            preparedStatement.setString(5, fileEntity.getFileShortestWordFile());
            preparedStatement.setInt(6, fileEntity.getFileAverageWordLength());
            preparedStatement.setInt(7, fileEntity.getCountLine());
            preparedStatement.executeUpdate();
            connection.commit();

            preparedStatement.close();
            connection.close();
            log.info("End insert into table filestatistics");
        }
        catch (Exception e){
            log.error("Insert into table filestatistics don't work");
            log.error(e.getMessage());
            return 0;
        }
        return 1;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            log.info("Get a connection");
            connection = CONNECTION_FACTORY_C3P0.newConnection();
        }
        catch (Exception e){
            log.error("Prodlem with database connection");
            log.error(e.getMessage());
        }
        return connection;
    }

    public void createTableLineTable() {
        try {
            log.info("Check or create table lineStatistics");
            Connection connection = getConnection();
            if(connection == null){
                log.error("connection is null");
            }
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, "LINESTATISTICS", null);
            if (res.next()) {
                log.info("Table lineStatistics in dataBase test existing");
            } else {
                log.info("Create table lineStatistics in dataBase test");
                Statement statement = connection.createStatement();
                statement.execute(CREATE_LINE_TABLE);
                statement.close();
            }
            connection.close();
        }catch (Exception e){
            log.error("Problem with creation table linestatistics");
            log.error(e.getMessage());
        }
    }

    @Override
    public void createTableFileTable(){
        try {
            log.info("Check or create table fileStatistics");
            Connection connection = getConnection();
            if(connection == null){
                log.error("connection is null");
            }
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, "FILESTATISTICS", null);
            if (res.next()) {
                log.info("Table fileStatistics in dataBase test existing");
            } else {
                log.info("Create table fileStatistics in dataBase test");
                Statement statement = connection.createStatement();
                statement.execute(CREATE_FILE_TABLE);
                statement.close();
            }
            connection.close();
        }catch (Exception e){
            log.error("Problem with creation table filestatistics");
            log.error(e.getMessage());
        }
    }

    public void clearDb(){
        try{
            log.info("Clear database test");
            Connection connection = getConnection();
            if(connection == null){
                log.error("connection is null");
            }
            Statement statement = connection.createStatement();
            statement.execute(ClEAR_FILE_DB_SQL);
            statement.execute(ClEAR_LINE_DB_SQL);
            connection.commit();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            log.error("Problem with clear database test");
            log.error(e.getMessage());
        }
    }
}
