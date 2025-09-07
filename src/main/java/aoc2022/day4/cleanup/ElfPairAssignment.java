package aoc2022.day4.cleanup;

public class ElfPairAssignment {
    private final int elf1Start;
    private final int elf1End;
    private final int elf2Start;
    private final int elf2End;

    public ElfPairAssignment(int elf1Start, int elf1End, int elf2Start, int elf2End) {
        this.elf1Start = elf1Start;
        this.elf1End = elf1End;
        this.elf2Start = elf2Start;
        this.elf2End = elf2End;
    }

    public boolean doesAssignmentFullyOverlap() {
        return (elf1Start <= elf2Start && elf1End >= elf2End) || (elf2Start <= elf1Start && elf2End >= elf1End);
    }

    public boolean doesAssignmentOverlap() {
        return (elf2Start >= elf1Start && elf2Start <= elf1End) || (elf1Start >= elf2Start && elf1Start <= elf2End);
    }
}
