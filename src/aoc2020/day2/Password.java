package aoc2020.day2;

public class Password {
    private final String value;
    private PasswordPolicy passwordPolicy;

    public Password(String value) {
        this.value = value;
    }

    public void setPasswordPolicy(PasswordPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    public boolean checkPassword() {
        return this.passwordPolicy.isValid(this.value);
    }
}
