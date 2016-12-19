package sorokin.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sorokin.service.StatisticsFileServiceImpl;
import sorokin.service.StatisticsLineService;
import sorokin.entity.StatisticsLineEntity;

import java.util.List;

@Controller
@RequestMapping("/lineStatistics")
public class StatisticsLineController {

    private StatisticsLineService statisticsLine;
    private Logger log = Logger.getLogger(StatisticsLineController.class);

    @Autowired
    public StatisticsLineController(StatisticsLineService statisticsLine) {
        this.statisticsLine = statisticsLine;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = true, value = "filename") String fileName, Model model) {
        log.info("Get statistics for line");
        List<StatisticsLineEntity> lineEntities = statisticsLine.queryLineEntity(fileName);
        model.addAttribute("lineEntities", lineEntities);
        model.addAttribute("fileName", fileName);
        return "lineStatistics";
    }
}
