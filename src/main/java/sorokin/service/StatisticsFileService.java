package sorokin.service;



import sorokin.entity.StatisticsFileEntity;
import sorokin.filter.FilterName;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StatisticsFileService {

    List<StatisticsFileEntity> queryFileEntity(String path) throws IOException, PropertyVetoException, SQLException;

    List<FilterName> getAllFilters();

}
