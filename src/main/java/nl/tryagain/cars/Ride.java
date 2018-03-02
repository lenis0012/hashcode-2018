package nl.tryagain.cars;

import lombok.Value;

@Value
public class Ride {
    private int id;
    private int startRow;
    private int startCol;
    private int finishRow;
    private int finishCol;
    private int earliestStart;
    private int latestFinish;
}
