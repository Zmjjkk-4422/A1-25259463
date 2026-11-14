/**
 * A single appointment linking a {@link Patient} to a chosen
 * {@link HealthProfessional} at one fixed time slot of the current day.
 *
 * <p>The appointment references the professional through the common
 * {@link HealthProfessional} type, so it can be created for any professional
 * subclass. It implements {@link Comparable} so a whole collection of
 * appointments can be sorted from earliest to latest.</p>
 */
public class Appointment implements Comparable<Appointment> {

    /** Patient attending the appointment. */
    private final Patient patient;

    /** Professional the patient is seeing (any subclass of HealthProfessional). */
    private final HealthProfessional professional;

    /** Appointment time as minutes from midnight, for example 510 = 08:30. */
    private final int slotMinutes;

    /**
     * Creates an appointment after ensuring all required details are present
     * and the time slot is valid.
     *
     * @param patient      patient attending; must not be {@code null}
     * @param professional chosen professional; must not be {@code null}
     * @param slotMinutes  time as minutes from midnight; must be a valid clinic slot
     * @throws IllegalArgumentException if any detail is missing or the slot is invalid
     */
    public Appointment(Patient patient, HealthProfessional professional, int slotMinutes) {
        if (patient == null) {
            throw new IllegalArgumentException("Appointment must have a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("Appointment must have a health professional.");
        }
        if (!Schedule.isValidSlot(slotMinutes)) {
            throw new IllegalArgumentException("Invalid appointment time slot: "
                    + Schedule.format(slotMinutes));
        }
        this.patient = patient;
        this.professional = professional;
        this.slotMinutes = slotMinutes;
    }

    /**
     * Returns the patient of this appointment.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the professional of this appointment.
     *
     * @return the professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the appointment time as minutes from midnight.
     *
     * @return slot minutes from midnight
     */
    public int getSlotMinutes() {
        return slotMinutes;
    }

    /**
     * Orders appointments by time, earliest first. Appointments at the same
     * time may appear in any order.
     *
     * @param other the appointment to compare against
     * @return negative, zero or positive as this appointment is earlier,
     *         at the same time, or later than the other
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.slotMinutes, other.slotMinutes);
    }

    /**
     * {@inheritDoc}
     *
     * @return full appointment details, including the chosen professional
     */
    @Override
    public String toString() {
        return "Time: " + Schedule.format(slotMinutes)
                + " | " + patient
                + " | " + professional.getSpecialty()
                + " " + professional.getName()
                + " (ID " + professional.getId() + ")";
    }
}
