package aoc2020.day2;

public abstract class PasswordPolicy {
    protected String charToCheck;
    protected int firstNum;
    protected int secondNum;

    public PasswordPolicy(String policyString) {
        String[] passwordPolicy = policyString.split(" ");
        if (passwordPolicy.length == 2) {
            this.charToCheck = passwordPolicy[1];
            String[] minMax = passwordPolicy[0].split("-");
            if (minMax.length == 2) {
                this.firstNum = Integer.parseInt(minMax[0]);
                this.secondNum = Integer.parseInt(minMax[1]);
            }
        }
    }

    public abstract boolean isValid(String password);
}
