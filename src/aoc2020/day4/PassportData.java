package aoc2020.day4;

import java.util.List;

public class PassportData
{
    public static final String[] FIELDS = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
    public static final List<String> VALID_EYE_COLORS = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    private final String scannedValue;
    private int birthYear;
    private int issueYear;
    private int expirationYear;
    private String height;
    private String hairColor;
    private String eyeColor;
    private String passportNumber;

    public PassportData(String scannedValue)
    {
        this.scannedValue = scannedValue;
        if (isValid())
        {
            birthYear = Integer.parseInt(extractFieldValue("byr"));
            issueYear = Integer.parseInt(extractFieldValue("iyr"));
            expirationYear = Integer.parseInt(extractFieldValue("eyr"));
            height = extractFieldValue("hgt");
            hairColor = extractFieldValue("hcl");
            eyeColor = extractFieldValue("ecl");
            passportNumber = extractFieldValue("pid");
        }
    }

    public boolean isValid()
    {
        for (String field : FIELDS)
        {
            if (!field.equals("cid") && !scannedValue.contains(field + ":"))
            {
                return false;
            }
        }
        return true;
    }

    public boolean isValid_v2()
    {
        return hasValidBirthYear() && hasValidIssueYear() && hasValidExpirationYear()
                && hasValidHeight() && hasValidHairColor() && hasValidEyeColor() && hasValidPassportId();
    }

    private boolean hasValidBirthYear()
    {
        return birthYear >= 1920 && birthYear <= 2002;
    }

    private boolean hasValidIssueYear()
    {
        return issueYear >= 2010 && issueYear <= 2020;
    }

    private boolean hasValidExpirationYear()
    {
        return expirationYear >= 2020 && expirationYear <= 2030;
    }

    private boolean hasValidHeight()
    {
        if (height.contains("cm"))
        {
            int heightValue = Integer.parseInt(height.substring(0, height.indexOf("cm")));
            return heightValue >= 150 && heightValue <= 193;
        }
        else if (height.contains("in"))
        {
            int heightValue = Integer.parseInt(height.substring(0, height.indexOf("in")));
            return heightValue >= 59 && heightValue <= 76;
        }
        else
        {
            return false;
        }
    }

    private boolean hasValidHairColor()
    {
        return hairColor.length() == 7 && hairColor.matches("#[0-9a-f]{6}");
    }

    private boolean hasValidEyeColor()
    {
        return VALID_EYE_COLORS.contains(eyeColor);
    }

    private boolean hasValidPassportId()
    {
        return passportNumber.length() == 9 && passportNumber.matches("[0-9]{9}");
    }

    private String extractFieldValue(String field)
    {
        int fldIndex = scannedValue.indexOf(field);
        int fieldStart = fldIndex + field.length() + 1;
        int nextSpace = scannedValue.indexOf(" ", fieldStart);
        int nextNewLine = scannedValue.indexOf("\n", fieldStart);
        int fieldEnd = scannedValue.length();
        if (nextSpace != -1)
        {
            fieldEnd = nextSpace;
        }
        if (nextNewLine != -1)
        {
            fieldEnd = Math.min(fieldEnd, nextNewLine);
        }
        return scannedValue.substring(fieldStart, fieldEnd);
    }
}
