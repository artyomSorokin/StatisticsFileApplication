package sorokin.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sorokin.Service.StatisticsFileService;
import sorokin.entity.StatisticsFileEntity;
import sorokin.filter.FilterName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fileStatistics")
public class StatisticsFileController {

    private StatisticsFileService statisticsFile;
    private Logger log = Logger.getLogger(StatisticsFileController .class);

    @Autowired
    public StatisticsFileController(StatisticsFileService statisticsFile) {
        this.statisticsFile = statisticsFile;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void setupForm() {

    }
    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@RequestParam("path") String path, HttpServletRequest request) {
        log.info("Get path directory and show statistics for files");
        List<StatisticsFileEntity> fileEntities = java.util.Collections.emptyList();
        if (path != null) {
            fileEntities = statisticsFile.queryFileEntity(path);
        }
        List<FilterName> filters = statisticsFile.getAllFilters();
        HttpSession session = request.getSession();
        session.setAttribute("fileEntities", fileEntities);
        session.setAttribute("filters", filters);
        session.setAttribute("path", path);
        return "fileStatistics";
    }

    @RequestMapping(value = "/less5Lines", method = RequestMethod.GET)
    public String filterLess5Lines(@SessionAttribute("fileEntities") List<StatisticsFileEntity> fileList,
                                    @SessionAttribute("filters") List<FilterName> filter, Model model){
        log.info("Filter files with less then 5 lines");
        List<StatisticsFileEntity> fileFilters = new ArrayList<>();
        for(StatisticsFileEntity statisticsFileEntity : fileList){
            if(statisticsFileEntity.getCountLine() <= 5){
                fileFilters.add(statisticsFileEntity);
            }
        }
        model.addAttribute("filterList", fileFilters);
        model.addAttribute("filters", filter);
        return "less5Lines";
    }

    @RequestMapping(value = "/less10Lines", method = RequestMethod.GET)
    public String filterLess10Lines(@SessionAttribute("fileEntities") List<StatisticsFileEntity> fileList,
                                    @SessionAttribute("filters") List<FilterName> filter, Model model){
        log.info("Filter files with less then 10 lines");
        List<StatisticsFileEntity> fileFilters = new ArrayList<>();
        for(StatisticsFileEntity statisticsFileEntity : fileList){
            if(statisticsFileEntity.getCountLine() < 10 && statisticsFileEntity.getCountLine()>5){
                fileFilters.add(statisticsFileEntity);
            }
        }
        model.addAttribute("filterList", fileFilters);
        model.addAttribute("filters", filter);
        return "less10Lines";
    }

    @RequestMapping(value = "/more10Lines", method = RequestMethod.GET)
    public String filterMore10Lines(@SessionAttribute("fileEntities") List<StatisticsFileEntity> fileList,
                                    @SessionAttribute("filters") List<FilterName> filter, Model model){
        log.info("Filter files with more then 10 lines");
        List<StatisticsFileEntity> fileFilters = new ArrayList<>();
        for(StatisticsFileEntity statisticsFileEntity : fileList){
            if(statisticsFileEntity.getCountLine() > 10){
                fileFilters.add(statisticsFileEntity);
            }
        }
        model.addAttribute("filterList", fileFilters);
        model.addAttribute("filters", filter);
        return "more10Lines";
    }

    @RequestMapping(value = "/allFiles", method = RequestMethod.GET)
    public String getAllFiles(@SessionAttribute("fileEntities") List<StatisticsFileEntity> fileList,
                                    @SessionAttribute("filters") List<FilterName> filter, Model model){
        log.info("show all files");
        model.addAttribute("filterList", fileList);
        model.addAttribute("filters", filter);
        return "allFiles";
    }

}
