package aoc2020.day2;

import java.util.Hashtable;

public class PasswordChecker {
    private final Hashtable<Password, String> passwordPolicyMap = new Hashtable<>();
    public void addPassword(String passwordWithPolicy) {
        String[] lineParts = passwordWithPolicy.split(":");
        if (lineParts.length == 2) {
            String passwordValue = lineParts[1].trim();
            String policyString = lineParts[0].trim();
            passwordPolicyMap.put(new Password(passwordValue), policyString);
        }
    }

    public void setPasswordPolicyCountChars() {
        passwordPolicyMap.keySet().forEach(password -> password.setPasswordPolicy(new PasswordPolicyCountCharacters(passwordPolicyMap.get(password))));
    }

    public void setPasswordPolicyCharPosition() {
        passwordPolicyMap.keySet().forEach(password -> password.setPasswordPolicy(new PasswordPolicyCharacterPosition(passwordPolicyMap.get(password))));
    }

    public int countValidPasswords() {
        return (int) passwordPolicyMap.keySet().stream().filter(Password::checkPassword).count();
    }
}
