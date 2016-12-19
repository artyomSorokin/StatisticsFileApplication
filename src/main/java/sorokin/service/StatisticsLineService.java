package sorokin.service;


import sorokin.entity.StatisticsLineEntity;

import java.util.List;

public interface StatisticsLineService {

    List<StatisticsLineEntity> queryLineEntity(String name);
}
