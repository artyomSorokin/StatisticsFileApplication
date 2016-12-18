package sorokin.Service;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sorokin.entity.StatisticsFileEntity;
import sorokin.filter.FilterName;
import sorokin.process.WorkProcess;
import java.util.ArrayList;
import java.util.List;

public class StatisticsFileServiceImpl implements StatisticsFileService{

    private Logger log = Logger.getLogger(StatisticsFileServiceImpl.class);

    @Override
    public List<StatisticsFileEntity> queryFileEntity(String path) {
        log.info("Get statistic for file");
        List<StatisticsFileEntity> fileEntityList = new ArrayList<>();
        new WorkProcess().start(path);
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        Session session = sessions.openSession();

        session.beginTransaction();
        List<StatisticsFileEntity> files = (List<StatisticsFileEntity>)session.createQuery("from StatisticsFileEntity").list();
        for(StatisticsFileEntity statisticsFileEntity : files){
            fileEntityList.add(statisticsFileEntity);
        }
        session.close();
        sessions.close();
        return fileEntityList;
    }

    @Override
    public List<FilterName> getAllFilters() {
        log.info("Create filter for files");
        List<FilterName> filters = new ArrayList<>();
        FilterName allFiles = new FilterName("All files", "/fileStatistics");
        FilterName less5Line = new FilterName("Less then 5 Lines", "/fileStatistics/less5Lines");
        FilterName less10Line = new FilterName("Less then 10 Lines", "/fileStatistics/less10Lines");
        FilterName more10Line = new FilterName("More then 10 lines", "/fileStatistics/more10Lines");
        filters.add(allFiles);
        filters.add(less5Line);
        filters.add(less10Line);
        filters.add(more10Line);
        return filters;
    }


}
