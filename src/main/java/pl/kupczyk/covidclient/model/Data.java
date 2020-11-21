package pl.kupczyk.covidclient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Data {
    private String date;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int confirmed_diff;
    private int deaths_diff;
    private int recovered_diff;
    private String last_update;
    private int active;
    private int active_diff;
    private double fatality_rate;
    private Region region;
}