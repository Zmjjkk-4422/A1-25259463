import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores and manages all appointments for the current day.
 *
 * <p>An {@link ArrayList} is used to hold the appointments because the
 * collection grows and shrinks over the day, appointments are searched and
 * filtered, and the list can be sorted by time. The manager supports:</p>
 * <ul>
 *     <li>adding an appointment, rejecting invalid details or a double booking;</li>
 *     <li>viewing appointments filtered by professional ID or patient phone;</li>
 *     <li>sorting appointments by time, for the whole list or a filtered subset;</li>
 *     <li>cancelling an appointment by professional ID and time, or by patient phone;</li>
 *     <li>returning a warning message whenever an operation cannot be performed.</li>
 * </ul>
 */
public class AppointmentManager {

    /** All appointments booked for the current day. */
    private final List<Appointment> appointments = new ArrayList<>();

    /**
     * Attempts to add a new appointment.
     *
     * @param patient      patient to book
     * @param professional professional to see
     * @param slotMinutes  requested time slot as minutes from midnight
     * @return a success message, or a warning explaining why the booking was rejected
     */
    public String addAppointment(Patient patient, HealthProfessional professional, int slotMinutes) {
        if (patient == null) {
            return "WARNING: Booking rejected - patient details are missing.";
        }
        if (professional == null) {
            return "WARNING: Booking rejected - a health professional must be chosen.";
        }
        if (!Schedule.isValidSlot(slotMinutes)) {
            return "WARNING: Booking rejected - " + Schedule.format(slotMinutes)
                    + " is not a valid clinic time slot.";
        }
        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(professional)
                    && existing.getSlotMinutes() == slotMinutes) {
                return "WARNING: Booking rejected - " + professional.getName()
                        + " is already booked at " + Schedule.format(slotMinutes) + ".";
            }
        }
        appointments.add(new Appointment(patient, professional, slotMinutes));
        return "SUCCESS: Booked " + patient.getName() + " with "
                + professional.getName() + " at " + Schedule.format(slotMinutes) + ".";
    }

    /**
     * Returns all appointments booked so far, in booking order.
     *
     * @return unmodifiable copy of the full appointment list
     */
    public List<Appointment> getAllAppointments() {
        return Collections.unmodifiableList(appointments);
    }

    /**
     * Returns appointments for the professional with the given ID.
     *
     * @param professionalId numeric ID to match
     * @return matching appointments (empty if none match)
     */
    public List<Appointment> filterByProfessionalId(String professionalId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Returns appointments for the patient with the given mobile number.
     *
     * @param phone mobile number to match
     * @return matching appointments (empty if none match)
     */
    public List<Appointment> filterByPatientPhone(String phone) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobilePhone().equals(phone)) {
                result.add(appointment);
            }
        }
        return result;
    }

    /**
     * Returns all appointments sorted from earliest to latest.
     *
     * @return time-sorted copy of the full list
     */
    public List<Appointment> getSortedAppointments() {
        List<Appointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Sorts a given subset of appointments from earliest to latest.
     *
     * @param subset appointments to sort (for example a filtered list)
     * @return time-sorted copy of the subset
     */
    public List<Appointment> sortSubset(List<Appointment> subset) {
        List<Appointment> sorted = new ArrayList<>(subset);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Cancels the appointment matching the given professional ID and time.
     *
     * @param professionalId ID of the professional
     * @param slotMinutes    time slot to cancel, as minutes from midnight
     * @return a success message, or a warning if no such appointment exists
     */
    public String cancelByProfessionalAndTime(String professionalId, int slotMinutes) {
        Appointment toRemove = null;
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)
                    && appointment.getSlotMinutes() == slotMinutes) {
                toRemove = appointment;
                break;
            }
        }
        if (toRemove == null) {
            return "WARNING: Cancellation failed - no appointment for professional ID "
                    + professionalId + " at " + Schedule.format(slotMinutes) + ".";
        }
        appointments.remove(toRemove);
        return "SUCCESS: Cancelled appointment for professional ID " + professionalId
                + " at " + Schedule.format(slotMinutes) + ".";
    }

    /**
     * Cancels all appointments for the patient with the given mobile number.
     *
     * @param phone patient mobile number
     * @return a success message with the number cancelled, or a warning if none match
     */
    public String cancelByPatientPhone(String phone) {
        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobilePhone().equals(phone)) {
                matches.add(appointment);
            }
        }
        if (matches.isEmpty()) {
            return "WARNING: Cancellation failed - no appointment found for phone " + phone + ".";
        }
        appointments.removeAll(matches);
        return "SUCCESS: Cancelled " + matches.size()
                + " appointment(s) for patient phone " + phone + ".";
    }
}
