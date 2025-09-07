package aoc2020.day2;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyCountCharacters extends PasswordPolicy {
    public PasswordPolicyCountCharacters(String policyString) {
        super(policyString);
    }

    @Override
    public boolean isValid(String password) {
        List<String> chars = new ArrayList<>(List.of(password.split("")));
        int charCount = (int) chars.stream().filter(c -> c.equals(charToCheck)).count();
        return charCount >= firstNum && charCount <= secondNum;
    }
}
