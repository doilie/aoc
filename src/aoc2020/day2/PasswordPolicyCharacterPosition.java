package aoc2020.day2;

public class PasswordPolicyCharacterPosition extends PasswordPolicy {
    public PasswordPolicyCharacterPosition(String policyString) {
       super(policyString);
    }
    @Override
    public boolean isValid(String password) {
        String firstNumChar = Character.toString(password.charAt(firstNum - 1));
        String secondNumChar = Character.toString(password.charAt(secondNum - 1));
        if (firstNumChar.equals(charToCheck) && secondNumChar.equals(charToCheck)) {
            return false;
        }
        return firstNumChar.equals(charToCheck) || secondNumChar.equals(charToCheck);
    }
}
