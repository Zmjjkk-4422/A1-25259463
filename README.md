# A1-25259463 — PROG2004 Assessment 1

A simple appointment system for a health service, built with Java to model
health professionals and their appointments for the current day.

- Name: YuHan Wu (Han)
- SCU account: 25259463

## Project structure

```
A1-25259463/
└── src/
    ├── AssignmentOne.java        # main method: scripted demonstration (Parts 3 & 6)
    ├── HealthProfessional.java   # abstract base class (Part 1)
    ├── GeneralPractitioner.java  # subclass: general practitioner (Part 2)
    ├── Specialist.java           # subclass: specialist doctor (Part 2)
    ├── Nurse.java                # subclass: nurse (Part 2)
    ├── Dietitian.java             # subclass: dietitian (Part 2)
    ├── Patient.java               # patient details (Part 4)
    ├── Appointment.java          # patient + professional + time slot (Part 4)
    ├── Schedule.java              # fixed daily timetable and slot helpers (Part 5)
    └── AppointmentManager.java   # collection: add/filter/sort/cancel (Part 5)
```

## How to compile and run

From the project root (`A1-25259463`):

```
javac -encoding UTF-8 -d out src\*.java
java -cp out AssignmentOne
```

Or open the folder in VS Code with the Java extension pack and run
`AssignmentOne` directly.

## Submission checklist

- [x] Java project with all source files (`src/`)
- [x] GitHub repository: https://github.com/Zmjjkk-4422/A1-25259463
- [ ] WakaTime report / screenshots attached as `A1-25259463-WakaTime.png/jpg/pdf`
- [ ] Short demonstration video link (SCU OneDrive share) added to the comments
- [ ] GenAI declaration statement included in the submission comments
