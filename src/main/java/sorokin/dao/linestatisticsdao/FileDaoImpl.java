package sorokin.dao.linestatisticsdao;


import org.apache.log4j.Logger;
import sorokin.dao.connection.ConnectionFactory;
import sorokin.dao.connection.ConnectionFactoryC3P0;
import sorokin.models.FileEntity;
import sorokin.models.LineEntity;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class FileDaoImpl implements FileDao{

    private ConnectionFactory connectionFactory;
    private static final Logger log = Logger.getLogger(FileDaoImpl.class);

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

    public FileDaoImpl() throws IOException, PropertyVetoException {
        connectionFactory = new ConnectionFactoryC3P0();
    }

    public Integer insertIntoLineTable(String fileName, String query, List<LineEntity> lineEntityList) throws SQLException {
        try( Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINE_SQL)) {
            log.info("Start insert into table linestatistics");
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
            log.info("End insert into table linestatistics");
        }
        catch (SQLException e){
            log.error("Insert into table linestatistics didn't work", e);
            throw e;
        }
        return 1;
    }

    public Integer insertIntoFileTable(FileEntity fileEntity) throws SQLException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE_SQL)) {
            log.info("Start insert into table filestatistics");
            preparedStatement.setString(1, fileEntity.getAbsolutePath());
            preparedStatement.setString(2, fileEntity.getQuery());
            preparedStatement.setInt(3, fileEntity.getFileLength());
            preparedStatement.setString(4, fileEntity.getFileLongestWord());
            preparedStatement.setString(5, fileEntity.getFileShortestWordFile());
            preparedStatement.setInt(6, fileEntity.getFileAverageWordLength());
            preparedStatement.setInt(7, fileEntity.getCountLine());
            preparedStatement.executeUpdate();
            connection.commit();
            log.info("End insert into table filestatistics");
        }
        catch (SQLException e){
            log.error("Insert into table filestatistics don't work", e);
            throw e;
        }
        return 1;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            log.info("Get a connection");
            connection = connectionFactory.newConnection();
        }
        catch (SQLException e){
            log.error("Prodlem with database connection", e);
            throw e;
        }
        return connection;
    }

    public void createTableLineTable() throws SQLException {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            log.info("Check or create table lineStatistics");

            if(connection == null){
                log.error("connection is null");
            }
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, "LINESTATISTICS", null);
            if (res.next()) {
                log.info("Table lineStatistics in dataBase test existing");
            } else {
                log.info("Create table lineStatistics in dataBase test");
                statement.execute(CREATE_LINE_TABLE);
                connection.commit();
            }
        }catch (SQLException e){
            log.error("Problem with creation table linestatistics", e);
            throw e;
        }
    }

    @Override
    public void createTableFileTable() throws SQLException {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();) {
            log.info("Check or create table fileStatistics");
            if(connection == null){
                log.error("connection is null");
            }
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, "FILESTATISTICS", null);
            if (res.next()) {
                log.info("Table fileStatistics in dataBase test existing");
            } else {
                log.info("Create table fileStatistics in dataBase test");
                statement.execute(CREATE_FILE_TABLE);
                connection.commit();
            }
        }catch (Exception e){
            log.error("Problem with creation table filestatistics", e);
            throw e;
        }
    }

    public void clearDb() throws SQLException {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();){
            log.info("Clear database test");

            if(connection == null){
                log.error("connection is null");
            }
            statement.execute(ClEAR_FILE_DB_SQL);
            statement.execute(ClEAR_LINE_DB_SQL);
            connection.commit();
        }
        catch(SQLException e){
            log.error("Problem with clear database test", e);
            throw e;
        }
    }
}
