package sorokin.service;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sorokin.entity.StatisticsFileEntity;
import sorokin.process.WorkProcess;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsTextFromUserServiceImpl implements StatisticsTextFromUserService{

    private Logger log = Logger.getLogger(StatisticsFileServiceImpl.class);

    @Override
    public List<StatisticsFileEntity> queryStatisticsTextFromUser(String text) throws IOException, PropertyVetoException, SQLException {
        log.info("Get statistics for user text");
        WorkProcess workProcess = new WorkProcess();
        String path = workProcess.handleTextFromUser(text);
        List<StatisticsFileEntity> fileEntityList = new ArrayList<>();
        workProcess.start(path);
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
}
