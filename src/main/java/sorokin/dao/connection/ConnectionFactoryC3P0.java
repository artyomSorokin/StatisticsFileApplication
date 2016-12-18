package sorokin.dao.connection;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import sorokin.entity.DbConnectionEntity;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryC3P0 implements ConnectionFactory{

    private ComboPooledDataSource dataSource;
    private static final String DB_DRIVER = "org.h2.Driver";
    private DbConnectionEntity dbConnection;
    private Logger log = Logger.getLogger(ConnectionFactoryC3P0.class);

    protected DbConnectionEntity readConfig() {
        log.info("Read file database properties and create DbConnectionEntity");
        Properties properties = new Properties();
        try(InputStream fis = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(fis);
            dbConnection = new DbConnectionEntity();
            dbConnection.setNameDb(properties.getProperty("name_database"));
            dbConnection.setLogin(properties.getProperty("login_database"));
            dbConnection.setPassword(properties.getProperty("password_database"));
        }
        catch (Exception e){
            log.error("Error load file");
            log.error(e.getMessage());
        }
        return dbConnection;
    }

    public ConnectionFactoryC3P0() {
        createConnectionFactory();
    }


    public Connection newConnection(){
        Connection connection = null;
        try {
            log.info("Get connection");
            connection = dataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
        }
        catch (Exception e){
            log.error("Problem with database connection");
            log.error(e.getMessage());
        }
        return connection;
    }

    public void createConnectionFactory(){
        try{
            log.info("Create pool connection for database");
            this.dataSource = new ComboPooledDataSource();
            DbConnectionEntity dbConnectionEntry = readConfig();
            dataSource.setDriverClass(DB_DRIVER);
            dataSource.setJdbcUrl(dbConnectionEntry.getNameDb());
            dataSource.setUser(dbConnectionEntry.getLogin());
            dataSource.setPassword(dbConnectionEntry.getPassword());

            dataSource.setMinPoolSize(1);
            dataSource.setAcquireIncrement(1);
            dataSource.setMaxPoolSize(20);

            dataSource.setMaxStatements(180);
            dataSource.setMaxStatementsPerConnection(10);
            log.info("Creating pool connection is successful");
        } catch (Exception e) {
            log.error("Problem with database connection");
            log.error(e.getMessage());
        }
    }

    public void close() throws SQLException {
        //NOP
    }
}
