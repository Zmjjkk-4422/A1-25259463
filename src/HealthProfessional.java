import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a generic health professional working at the health service.
 *
 * <p>This abstract base class captures the information and behaviour that are
 * common to every kind of health professional, regardless of their specialty:
 * a numeric ID, a name, and the fixed set of weekdays they work. Specific
 * professional types (for example general practitioners, specialists, nurses
 * or dietitians) extend this class and add their own details.</p>
 *
 * <p>The class is declared {@code abstract} so that a plain, unspecialised
 * health professional cannot be instantiated directly; every concrete object
 * must belong to one of the specialised subclasses.</p>
 */
public abstract class HealthProfessional {

    /** Numeric-only identifier of the professional, for example "101". */
    private final String id;

    /** Full name of the professional, for example "Dr Alice Nguyen". */
    private String name;

    /** Fixed weekdays the professional works, for example "Monday", "Wednesday". */
    private final List<String> availableDays;

    /**
     * Creates a health professional with all common details supplied.
     *
     * @param id            numeric-only identifier; must contain digits only
     * @param name          full name; must not be blank
     * @param availableDays fixed weekdays the professional works; may be {@code null}
     *                      (the default day set is then used)
     * @throws IllegalArgumentException if the ID is not digits-only or the name is blank
     */
    public HealthProfessional(String id, String name, List<String> availableDays) {
        if (id == null || !isDigitsOnly(id)) {
            throw new IllegalArgumentException("Professional ID must contain digits only.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professional name must not be blank.");
        }
        this.id = id;
        this.name = name.trim();

        if (availableDays == null || availableDays.isEmpty()) {
            // Sensible default working week when the caller does not supply days.
            this.availableDays = new ArrayList<>();
            this.availableDays.add("Monday");
            this.availableDays.add("Wednesday");
            this.availableDays.add("Friday");
        } else {
            // Defensive copy: external code cannot mutate the internal list.
            this.availableDays = new ArrayList<>(availableDays);
        }
    }

    /**
     * Creates a health professional using the default working week
     * (Monday, Wednesday, Friday).
     *
     * @param id   numeric-only identifier
     * @param name full name
     */
    public HealthProfessional(String id, String name) {
        this(id, name, null);
    }

    /**
     * Checks whether a text contains digits only.
     *
     * @param text the text to check
     * @return {@code true} if every character is a digit and the text is not empty
     */
    private static boolean isDigitsOnly(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the numeric identifier of the professional.
     *
     * @return the professional ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the professional.
     *
     * @return the professional name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the professional's name, keeping the object in a valid state.
     *
     * @param name new full name; must not be blank
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professional name must not be blank.");
        }
        this.name = name.trim();
    }

    /**
     * Returns an unmodifiable copy of the working weekdays so the internal
     * collection cannot be changed from outside the class.
     *
     * @return unmodifiable list of available weekdays
     */
    public List<String> getAvailableDays() {
        return Collections.unmodifiableList(availableDays);
    }

    /**
     * Returns the label describing what kind of health professional this is,
     * for example "General Practitioner". Implemented by each subclass.
     *
     * @return the professional type label
     */
    public abstract String getSpecialty();

    /**
     * Returns a short description of the unique role / capability of this
     * professional type. Implemented by each subclass to demonstrate
     * polymorphism: the same message call behaves differently per type.
     *
     * @return a description of the professional's distinctive capability
     */
    public abstract String getRoleDescription();

    /**
     * Returns the common details shared by all professionals, formatted as
     * readable text. Subclasses call this method from their own
     * {@code toString} and append their type-specific details.
     *
     * @return common details as a human-readable string
     */
    protected String commonDetails() {
        return "ID: " + id
                + " | Name: " + name
                + " | Type: " + getSpecialty()
                + " | Available: " + String.join(", ", availableDays);
    }

    /**
     * Compares two professionals by their numeric ID. Two professionals are
     * considered equal when they carry the same identifier.
     *
     * @param o the object to compare against
     * @return {@code true} if both professionals have the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional other = (HealthProfessional) o;
        return this.id.equals(other.id);
    }

    /**
     * Returns a hash code based on the ID so that equal professionals have
     * equal hash codes, as required by the {@link #equals(Object)} contract.
     *
     * @return the ID-based hash code
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Returns all details of this professional in a readable form.
     *
     * @return readable multi-field description
     */
    @Override
    public String toString() {
        return commonDetails();
    }
}
