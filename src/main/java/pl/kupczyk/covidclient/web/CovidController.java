package pl.kupczyk.covidclient.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kupczyk.covidclient.utils.DataUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CovidController {

    public final CovidService service;
    public final DataUtils utils = new DataUtils();

    @RequestMapping("")
    public List<String> fetchData() {
        return service.getDateAsAt();
    }

    @RequestMapping("covid")
    public String getData(Model model) {
        model.addAttribute("service", service);

        return "index";
    }

    @RequestMapping("confirmed")
    public Map<String, Collection<Integer>> getConfirmedCountryRate() {
        return service.getConfirmedCountryRate();
    }

    @RequestMapping("deaths")
    public Map<String, Collection<Integer>> getDeathsCountryRate() {
        return service.getDeathsCountryRate();
    }

    @RequestMapping("recovered")
    public Map<String, Collection<Integer>> getRecoveredCountryRate() {
        return service.getRecoveredCountryRate();
    }
}