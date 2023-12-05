package aoc2023.day5;

import lib.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5_Fertilizer extends Challenge {
    public static void main(String[] args)  {
        Day5_Fertilizer day5 = new Day5_Fertilizer();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_Fertilizer() {
        super("2023/day5-input.txt");
        this.parseFile();
    }

    private final ElementMapper seedToSoilMap = new ElementMapper();
    private final ElementMapper soilToFertilizerMap = new ElementMapper();
    private final ElementMapper fertilizerToWaterMap = new ElementMapper();
    private final ElementMapper waterToLightMap = new ElementMapper();
    private final ElementMapper lightToTemperatureMap = new ElementMapper();
    private final ElementMapper temperatureToHumidityMap = new ElementMapper();
    private final ElementMapper humidityToLocationMap = new ElementMapper();
    private final List<ElementMapper> elementMappers = List.of(seedToSoilMap, soilToFertilizerMap, fertilizerToWaterMap, waterToLightMap, lightToTemperatureMap, temperatureToHumidityMap, humidityToLocationMap);
    private final List<Long> seeds = new ArrayList<>();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        int elementMapperIdx = -1;
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            if (line.startsWith("seeds:")) {
                String[] lineParts = line.split(":");
                if (lineParts.length == 2) {
                    seeds.addAll(Arrays.stream(lineParts[1].trim().split("\\s+")).map(Long::parseLong).toList());
                }
            }
            else if (line.startsWith("seed-") || line.startsWith("soil-") || line.startsWith("fertilizer-") || line.startsWith("water-") ||
                    line.startsWith("light-") || line.startsWith("temperature-") || line.startsWith("humidity-")) {
                elementMapperIdx++;
            }
            else {
                elementMappers.get(elementMapperIdx).addElementMap(line);
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        long lowestLocation = Long.MAX_VALUE;
        for (long seed : seeds) {
            long res = seed;
            for (ElementMapper mapper : elementMappers) {
                res = mapper.getDestination(res);
            }
            if (res < lowestLocation) {
                lowestLocation = res;
            }
        }
        System.out.println("Lowest location number: " + lowestLocation);
    }

    @Override
    public void doTwoStarSolution() {
        long lowestLocation = Long.MAX_VALUE;
        long magicNumber = 23;
        for (int i = 0; i < seeds.size(); i += 2) {
            long start = seeds.get(i);
            long range = seeds.get(i + 1);
            for (long seedIdx = start; seedIdx <= start + range; seedIdx += magicNumber) {
                long res = seedIdx;
                for (ElementMapper mapper : elementMappers) {
                    res = mapper.getDestination(res);
                }
                if (res < lowestLocation) {
                    lowestLocation = res;
                }
            }
        }
        System.out.println("Lowest location number: " + lowestLocation);
    }

}
