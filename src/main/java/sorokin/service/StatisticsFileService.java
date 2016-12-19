package sorokin.service;



import sorokin.entity.StatisticsFileEntity;
import sorokin.filter.FilterName;

import java.util.List;

public interface StatisticsFileService {

    public List<StatisticsFileEntity> queryFileEntity(String path);

    public List<FilterName> getAllFilters();


}
