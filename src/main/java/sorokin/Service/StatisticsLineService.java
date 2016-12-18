package sorokin.Service;


import sorokin.entity.StatisticsLineEntity;

import java.util.List;

public interface StatisticsLineService {

    public List<StatisticsLineEntity> queryLineEntity(String name);
}
