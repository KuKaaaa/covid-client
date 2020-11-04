package pl.kupczyk.covidclient.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kupczyk.covidclient.model.CovidData;
import pl.kupczyk.covidclient.model.Data;
import pl.kupczyk.covidclient.model.Region;
import pl.kupczyk.covidclient.utils.DataUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CovidService {

    private final DataUtils utils = new DataUtils();
    private static final String URL = "https://covid-api.com/api/reports";

    public List<Data> downloadData(){
        RestTemplate restTemplate = new RestTemplate();
        CovidData list = restTemplate.getForObject(URL, CovidData.class);

        return Objects.requireNonNull(list).getData();
    }

    public List<Integer> getConfirmed(){

        return downloadData().stream().map(Data::getConfirmed)
                .collect(Collectors.toList());
    }

    public List<Integer> getDeaths(){
        return downloadData().stream().map(Data::getDeaths)
                .collect(Collectors.toList());
    }

    public List<Integer> getRecovered(){
        return downloadData().stream().map(Data::getRecovered)
                .collect(Collectors.toList());
    }

    public List<String> getCountryIso(){
        return downloadData().stream().map(Data::getRegion)
                .collect(Collectors.toList())
                .stream()
                .map(Region::getIso)
                .collect(Collectors.toList());
    }

    public List<String> getDateAsAt(){
        return downloadData().stream().map(Data::getLast_update)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getInfectionCityRate(){
        return IntStream.range(0, getCountryIso().size()).boxed()
                .collect(Collectors.toMap(getCountryIso()::get, getConfirmed()::get));
    }

//    public getRaportByCity(){

//    }
}
