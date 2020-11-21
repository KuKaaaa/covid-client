package pl.kupczyk.covidclient.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kupczyk.covidclient.model.CovidData;
import pl.kupczyk.covidclient.model.Data;
import pl.kupczyk.covidclient.model.Region;
import pl.kupczyk.covidclient.utils.DataUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CovidService {

    private final DataUtils utils = new DataUtils();
    private final String URL = "https://covid-api.com/api/reports";

    public List<Data> fetchData() {
        RestTemplate restTemplate = new RestTemplate();
        CovidData list = restTemplate.getForObject(URL, CovidData.class);

        return Objects.requireNonNull(list).getData();
    }

    public List<Integer> getConfirmed() {

        return fetchData().stream().map(Data::getConfirmed)
                .collect(Collectors.toList());
    }

    public List<Integer> getDeaths() {
        return fetchData().stream().map(Data::getDeaths)
                .collect(Collectors.toList());
    }

    public List<Integer> getRecovered() {
        return fetchData().stream().map(Data::getRecovered)
                .collect(Collectors.toList());
    }

    public List<String> getCountryIso() {
        return fetchData().stream().map(Data::getRegion)
                .collect(Collectors.toList())
                .stream()
                .map(Region::getIso)
                .collect(Collectors.toList());
    }

    public List<String> getDateAsAt() {
        return fetchData().stream().map(Data::getLast_update)
                .collect(Collectors.toList());
    }

    public Map<String, Collection<Integer>> getConfirmedCountryRate() {
        return utils.toMap(getCountryIso(), getConfirmed()).asMap();
    }

    public Map<String, Collection<Integer>> getDeathsCountryRate() {
        return utils.toMap(getCountryIso(), getDeaths()).asMap();
    }

    public Map<String, Collection<Integer>> getRecoveredCountryRate() {
        return utils.toMap(getCountryIso(), getRecovered()).asMap();
    }
}