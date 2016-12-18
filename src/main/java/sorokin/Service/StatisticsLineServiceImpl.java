package sorokin.Service;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sorokin.entity.StatisticsLineEntity;
import java.util.ArrayList;
import java.util.List;

public class StatisticsLineServiceImpl implements StatisticsLineService {

    private Logger log = Logger.getLogger(StatisticsFileServiceImpl.class);

    public List<StatisticsLineEntity> queryLineEntity(String name){
        log.info("Get statistics for line");
        List<StatisticsLineEntity> lineEntityList = new ArrayList<>();
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        Session session = sessions.openSession();

        session.beginTransaction();
        Query query = session.createQuery("from StatisticsLineEntity where query = :name ");
        query.setParameter("name", name);
        List<StatisticsLineEntity> lines = (List<StatisticsLineEntity>)query.list();
        for(StatisticsLineEntity statisticsLineEntity : lines){
            lineEntityList.add(statisticsLineEntity);
        }
        session.close();
        sessions.close();
        return lineEntityList;
    }
}
