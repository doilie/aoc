package aoc2019.day1;

public class Mass {
    private final int mass;

    public Mass(int mass) {
        this.mass = mass;
    }

    public int getTotalFuel() {
        int currentFuelConsumer = mass;
        int totalFuel = 0;
        int currentFuel;

        while (currentFuelConsumer > 0) {
            currentFuel = getFuel(currentFuelConsumer);
            currentFuelConsumer = currentFuel;
            if (currentFuel > 0) {
                totalFuel += currentFuel;
            }
        }

        return totalFuel;
    }

    public int getFuelForMassOnly() {
        return getFuel(mass);
    }

    private int getFuel(int fuelConsumer) {
        return (fuelConsumer / 3) - 2;
    }
}
