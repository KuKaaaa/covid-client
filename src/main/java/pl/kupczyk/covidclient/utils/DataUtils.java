package pl.kupczyk.covidclient.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataUtils {

    public String generateFile() {
        String url = "https://raw.githubusercontent.com/" +
                "CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/";

        LocalDate date = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        url = url + date.format(formatter) + ".csv";

        return url;
    }

    public Integer total(List<Integer> list) {
        Integer sum = 0;
        if (list.size() > 2) {
            for (Integer i: list) {
                sum += i;
            }

            return sum;
        }

        return list.get(0);
    }

    public Multimap<String, Integer> toMap(List<String> keys, List<Integer> values) {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();

        for (int i = 0; i < keys.size(); i++) {
            multimap.put(keys.get(i), values.get(i));
        }

        return multimap;
    }

}