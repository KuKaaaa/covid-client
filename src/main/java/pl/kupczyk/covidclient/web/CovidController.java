package pl.kupczyk.covidclient.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kupczyk.covidclient.model.Data;
import pl.kupczyk.covidclient.utils.DataUtils;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CovidController {

    public final CovidService service;
    public final DataUtils utils = new DataUtils();



    @RequestMapping("covid")
    public String getData(Model model){
        model.addAttribute("service", service);

        return "index";
    }

    @RequestMapping("total")
    public List<Data> totalSum(){
        return service.downloadData();
    }
}