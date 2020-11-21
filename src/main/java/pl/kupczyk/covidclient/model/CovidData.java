package pl.kupczyk.covidclient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CovidData {
    private List<Data> data;
}