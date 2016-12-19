package sorokin.service;


import sorokin.entity.StatisticsFileEntity;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StatisticsTextFromUserService {

    List<StatisticsFileEntity> queryStatisticsTextFromUser(String text) throws IOException, PropertyVetoException, SQLException;
}
