import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration class for the PROG2004 Assessment 1 appointment system.
 *
 * <p>The {@code main} method acts as a short, scripted simulation standing in
 * for real users: it creates health professionals (Part 3), then builds and
 * exercises the appointment manager end to end (Part 6), printing every result
 * to the console. Both normal cases and error cases are shown.</p>
 *
 * <h2>Submission details</h2>
 * <ul>
 *     <li>Name: YuHan Wu (Han)</li>
 *     <li>SCU account: 25259463</li>
 *     <li>GitHub repository link: https://github.com/Zmjjkk-4422/A1-25259463</li>
 *     <li>Demonstration video link (SCU OneDrive share): https://scuonline-my.sharepoint.com/:v:/g/personal/y_wu_80_student_scu_edu_au/IQAn0YiQ_LMzTqFJODs2fbcRAa_62HZERVMYNHWr69usYzE?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=hCRaXH</li>
 * </ul>
 *
 * <p>GenAI declaration: I acknowledge that I have used Generative AI tools
 * to complete this assessment in accordance with the conditions outlined in
 * the Assessment Brief. I used Doubao to assist with organising the class
 * structure, checking Java syntax, and guiding the OOP design.</p>
 */
public class AssignmentOne {

    public static void main(String[] args) {
        demonstrateProfessionals();
        demonstrateAppointments();
    }

    /**
     * Part 3: create several professionals of different types, store them
     * together through the common {@link HealthProfessional} type, and print
     * their full details and role descriptions.
     */
    private static void demonstrateProfessionals() {
        printHeader("PART 3 - Health professionals on the roster");

        List<HealthProfessional> roster = new ArrayList<>();
        roster.add(new GeneralPractitioner("101", "Dr Alice Nguyen"));
        roster.add(new GeneralPractitioner("102", "Dr Ben Carter"));
        roster.add(new GeneralPractitioner("103", "Dr Chloe Smith"));
        roster.add(new Specialist("201", "Dr David Lee", "Cardiology", true));
        roster.add(new Nurse("301", "Emma Wilson", "Emergency"));
        roster.add(new Dietitian("401", "Fiona Brown", "Bachelor of Dietetics"));

        for (HealthProfessional professional : roster) {
            System.out.println(professional);
            // Polymorphic call: each subclass answers with its own role description.
            System.out.println("    Role: " + professional.getRoleDescription());
        }
        System.out.println();
    }

    /**
     * Part 6: demonstrate the appointment system end to end - adding,
     * viewing, filtering, sorting, cancelling, and the rejected operations.
     */
    private static void demonstrateAppointments() {
        printHeader("PART 6 - Appointment system demonstration");

        AppointmentManager manager = new AppointmentManager();

        // The roster reused for booking.
        HealthProfessional gp101 = new GeneralPractitioner("101", "Dr Alice Nguyen");
        HealthProfessional specialist201 = new Specialist("201", "Dr David Lee", "Cardiology", true);
        HealthProfessional nurse301 = new Nurse("301", "Emma Wilson", "Emergency");
        HealthProfessional gp102 = new GeneralPractitioner("102", "Dr Ben Carter");
        HealthProfessional dietitian401 = new Dietitian("401", "Fiona Brown", "Bachelor of Dietetics");

        // Patients attending the clinic.
        Patient john = new Patient("John Doe", "0412345678");
        Patient jane = new Patient("Jane Smith", "0423456789");
        Patient mike = new Patient("Mike Chen", "0434567890");
        Patient sarah = new Patient("Sarah Liu", "0445678901");
        Patient tom = new Patient("Tom Wang", "0456789012");

        // --- Adding appointments (normal cases) ---
        System.out.println("--- Adding appointments ---");
        System.out.println(manager.addAppointment(john, gp101, 480));   // 08:00
        System.out.println(manager.addAppointment(jane, specialist201, 510)); // 08:30
        System.out.println(manager.addAppointment(mike, nurse301, 540));      // 09:00
        System.out.println(manager.addAppointment(sarah, gp102, 570));        // 09:30
        System.out.println(manager.addAppointment(tom, dietitian401, 600)); // 10:00
        System.out.println();

        // --- Viewing all appointments ---
        printHeader("All current appointments");
        printList(manager.getAllAppointments());

        // --- Filtering by professional ID ---
        printHeader("Filter: professional ID 101");
        printList(manager.filterByProfessionalId("101"));

        // --- Filtering by patient phone number ---
        printHeader("Filter: patient phone 0423456789");
        printList(manager.filterByPatientPhone("0423456789"));

        // --- Sorting the whole collection by time ---
        printHeader("All appointments sorted by time");
        printList(manager.getSortedAppointments());

        // --- Sorting a filtered subset by time ---
        printHeader("Filtered subset (GPs) sorted by time");
        printList(manager.sortSubset(manager.filterByProfessionalId("101")));

        // --- Error cases: rejected operations ---
        printHeader("Rejected operations (error cases)");
        // Double booking: GP 101 is already booked at 08:00.
        Patient latecomer = new Patient("Late Person", "0466777888");
        System.out.println(manager.addAppointment(latecomer, gp101, 480));
        // Invalid slot: 17:00 is outside opening hours.
        System.out.println(manager.addAppointment(john, gp102, 1020));
        // Cancelling an appointment that does not exist.
        System.out.println(manager.cancelByProfessionalAndTime("301", 840)); // 14:00
        System.out.println(manager.cancelByPatientPhone("0499999999"));
        System.out.println();

        // --- Cancelling a real appointment by professional ID and time ---
        printHeader("After cancelling GP 101 at 08:00");
        System.out.println(manager.cancelByProfessionalAndTime("101", 480));
        System.out.println();

        // --- Cancelling a real appointment by patient phone number ---
        printHeader("After cancelling patient phone 0423456789");
        System.out.println(manager.cancelByPatientPhone("0423456789"));
        System.out.println();

        printHeader("Appointments remaining after cancellations");
        printList(manager.getAllAppointments());
    }

    /**
     * Prints a section header line for readable console output.
     *
     * @param title header text
     */
    private static void printHeader(String title) {
        System.out.println("=== " + title + " ===");
    }

    /**
     * Prints each appointment in a list, or a note if the list is empty.
     *
     * @param appointments list to display
     */
    private static void printList(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("(no appointments)");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
        System.out.println();
    }
}
