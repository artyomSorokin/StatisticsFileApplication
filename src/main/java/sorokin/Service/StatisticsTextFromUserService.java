package sorokin.Service;


import sorokin.entity.StatisticsFileEntity;

import java.util.List;

public interface StatisticsTextFromUserService {

    public List<StatisticsFileEntity> queryStatisticsTextFromUser(String text);
}
