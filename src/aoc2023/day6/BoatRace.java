package aoc2023.day6;

import java.util.ArrayList;
import java.util.List;

public class BoatRace {
    private final long time;
    private final long recordDistance;

    public BoatRace(long time, long recordDistance) {
        this.time = time;
        this.recordDistance = recordDistance;
    }

    public List<Long> getMillisecondsToBeatRecord() {
        List<Long> millisecondsToBeatRecord = new ArrayList<>();

        long holdTime = time / 2;
        long travelTime = time / 2;
        if (time % 2 != 0) {
            travelTime++;
        }

        while (holdTime >= 0) {
            long distanceTravelled = holdTime * travelTime;
            if (distanceTravelled > recordDistance) {
                if (travelTime == holdTime) {
                    millisecondsToBeatRecord.add(holdTime);
                }
                else {
                    millisecondsToBeatRecord.add(holdTime);
                    millisecondsToBeatRecord.add(travelTime);
                }
            }
            else {
                break;
            }
            holdTime--;
            travelTime++;
        }

        return millisecondsToBeatRecord;
    }
}
