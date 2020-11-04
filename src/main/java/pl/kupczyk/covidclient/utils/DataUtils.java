package pl.kupczyk.covidclient.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataUtils {

    public String generateFile(){
        String url = "https://raw.githubusercontent.com/" +
                "CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/";

        LocalDate date = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        url = url + date.format(formatter) + ".csv";

        return url;
    }

    public Integer total(List<Integer> list){
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}
