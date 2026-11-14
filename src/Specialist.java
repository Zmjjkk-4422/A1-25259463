import java.util.List;

/**
 * A specialist doctor who focuses on a particular medical area, for example
 * cardiology, paediatrics or radiology, and may perform surgery.
 *
 * <p>This class extends {@link HealthProfessional} and adds the specialist
 * area of practice and whether the specialist performs operations.</p>
 */
public class Specialist extends HealthProfessional {

    /** Medical area the specialist focuses on, for example "Cardiology". */
    private final String specialtyArea;

    /** Whether this specialist performs surgery. */
    private final boolean performsSurgery;

    /**
     * Creates a specialist with all details supplied.
     *
     * @param id              numeric-only identifier
     * @param name            full name
     * @param availableDays   working weekdays; {@code null} uses the default week
     * @param specialtyArea    medical area of practice
     * @param performsSurgery {@code true} if this specialist performs surgery
     */
    public Specialist(String id, String name, List<String> availableDays,
                      String specialtyArea, boolean performsSurgery) {
        super(id, name, availableDays);
        if (specialtyArea == null || specialtyArea.isBlank()) {
            throw new IllegalArgumentException("Specialty area must not be blank.");
        }
        this.specialtyArea = specialtyArea.trim();
        this.performsSurgery = performsSurgery;
    }

    /**
     * Creates a specialist using the default working week.
     *
     * @param id             numeric-only identifier
     * @param name           full name
     * @param specialtyArea   medical area of practice
     * @param performsSurgery {@code true} if this specialist performs surgery
     */
    public Specialist(String id, String name, String specialtyArea, boolean performsSurgery) {
        this(id, name, null, specialtyArea, performsSurgery);
    }

    /**
     * Creates a specialist who does not perform surgery, using the default
     * working week.
     *
     * @param id           numeric-only identifier
     * @param name         full name
     * @param specialtyArea medical area of practice
     */
    public Specialist(String id, String name, String specialtyArea) {
        this(id, name, null, specialtyArea, false);
    }

    /**
     * {@inheritDoc}
     *
     * @return the label "Specialist"
     */
    @Override
    public String getSpecialty() {
        return "Specialist";
    }

    /**
     * {@inheritDoc}
     *
     * @return the specialist area and surgical capability in plain language
     */
    @Override
    public String getRoleDescription() {
        return "Specialises in " + specialtyArea
                + (performsSurgery ? " and performs surgery." : " and does not perform surgery.");
    }

    /**
     * {@inheritDoc}
     *
     * @return common specialist details plus area and surgical capability
     */
    @Override
    public String toString() {
        return commonDetails()
                + " | Specialty area: " + specialtyArea
                + " | Performs surgery: " + (performsSurgery ? "Yes" : "No");
    }
}
