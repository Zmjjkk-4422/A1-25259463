import java.util.ArrayList;
import java.util.List;

/**
 * Holds the clinic's fixed daily timetable.
 *
 * <p>The clinic runs from 08:00 to 16:00 in 30-minute intervals. Keeping the
 * schedule in one place means the start time, end time and interval can be
 * changed here without touching the rest of the booking system. Time slots
 * are stored as minutes from midnight (for example 08:00 = 480) so that
 * ordering by time can be compared numerically and correctly.</p>
 */
public final class Schedule {

    /** Opening hour (24-hour clock), inclusive. */
    public static final int START_HOUR = 8;

    /** Closing hour (24-hour clock), inclusive. */
    public static final int END_HOUR = 16;

    /** Length of each booking slot in minutes. */
    public static final int SLOT_INTERVAL_MINUTES = 30;

    /** Private constructor: this is a utility class that is never instantiated. */
    private Schedule() {
    }

    /**
     * Checks whether the given minutes-from-midnight fall on a valid clinic slot.
     *
     * @param slotMinutes time expressed as minutes from midnight
     * @return {@code true} if the slot is within opening hours and on a 30-minute boundary
     */
    public static boolean isValidSlot(int slotMinutes) {
        int startMinutes = START_HOUR * 60;
        int endMinutes = END_HOUR * 60;
        if (slotMinutes < startMinutes || slotMinutes > endMinutes) {
            return false;
        }
        return slotMinutes % SLOT_INTERVAL_MINUTES == 0;
    }

    /**
     * Returns all valid slots for the day in chronological order.
     *
     * @return list of slot times as minutes from midnight
     */
    public static List<Integer> allSlots() {
        List<Integer> slots = new ArrayList<>();
        for (int minutes = START_HOUR * 60;
             minutes <= END_HOUR * 60;
             minutes += SLOT_INTERVAL_MINUTES) {
            slots.add(minutes);
        }
        return slots;
    }

    /**
     * Formats a slot in minutes-from-midnight as a "HH:mm" string.
     *
     * @param slotMinutes time as minutes from midnight
     * @return formatted time, for example "08:30"
     */
    public static String format(int slotMinutes) {
        int hour = slotMinutes / 60;
        int minute = slotMinutes % 60;
        return String.format("%02d:%02d", hour, minute);
    }
}
