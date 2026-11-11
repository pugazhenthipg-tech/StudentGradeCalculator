# Student Grade Calculator

A Java console application that calculates total marks, average percentage, letter grades, and pass/fail status across subjects.

## Features

- Supports `1` to `20` subjects with custom subject names and validated marks (`0`–`100`)
- Computes per-subject and overall grades (`A+` to `F`) with remarks
- Checks pass/fail using the rule: average `>= 40` and every subject `>= 40`
- Lists any failed subjects in the report card
- Non-interactive `--demo` mode

## Compile and Run

```bash
javac StudentGradeCalculator.java
java StudentGradeCalculator
```

Run demo mode:

```bash
java StudentGradeCalculator --demo
```
