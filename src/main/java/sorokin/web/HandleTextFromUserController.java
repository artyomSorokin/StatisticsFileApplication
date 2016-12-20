package sorokin.web;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sorokin.service.StatisticsTextFromUserService;
import sorokin.entity.StatisticsFileEntity;
import java.util.List;

@Controller
@RequestMapping("/userTextStatistics")
public class HandleTextFromUserController {

    private StatisticsTextFromUserService statisticsTextFromUserService;
    private Logger log = Logger.getLogger(HandleTextFromUserController.class);

    @Autowired
    public HandleTextFromUserController(StatisticsTextFromUserService statisticsTextFromUserService) {
        this.statisticsTextFromUserService = statisticsTextFromUserService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void setupForm() {

    }
    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@RequestParam("text") String text, Model model) {
        try {
            log.info("Handle text from user");
            List<StatisticsFileEntity> fileEntities = java.util.Collections.emptyList();
            if (text != null) {
                fileEntities = statisticsTextFromUserService.queryStatisticsTextFromUser(text);
            }
            model.addAttribute("fileEntities", fileEntities);

        }
        catch (Throwable e){
            model.addAttribute("error", e.getMessage());
            return "redirect:error.jsp";
        }
        return "userTextStatistics";
    }
}
