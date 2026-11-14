import java.util.List;

/**
 * A dietitian who advises patients on nutrition and diet plans, holding a
 * recognised dietetic qualification.
 *
 * <p>This class extends {@link HealthProfessional} and adds the dietetic
 * qualification held by the professional.</p>
 */
public class Dietitian extends HealthProfessional {

    /** Dietetic qualification held, for example "Bachelor of Dietetics". */
    private final String qualification;

    /**
     * Creates a dietitian with all details supplied.
     *
     * @param id            numeric-only identifier
     * @param name          full name
     * @param availableDays  working weekdays; {@code null} uses the default week
     * @param qualification dietetic qualification held
     */
    public Dietitian(String id, String name, List<String> availableDays,
                     String qualification) {
        super(id, name, availableDays);
        if (qualification == null || qualification.isBlank()) {
            throw new IllegalArgumentException("Qualification must not be blank.");
        }
        this.qualification = qualification.trim();
    }

    /**
     * Creates a dietitian using the default working week.
     *
     * @param id            numeric-only identifier
     * @param name          full name
     * @param qualification dietetic qualification held
     */
    public Dietitian(String id, String name, String qualification) {
        this(id, name, null, qualification);
    }

    /**
     * {@inheritDoc}
     *
     * @return the label "Dietitian"
     */
    @Override
    public String getSpecialty() {
        return "Dietitian";
    }

    /**
     * {@inheritDoc}
     *
     * @return the nutrition advisory role and qualification in plain language
     */
    @Override
    public String getRoleDescription() {
        return "Advises patients on nutrition and diet plans; holds: " + qualification + ".";
    }

    /**
     * {@inheritDoc}
     *
     * @return common dietitian details plus qualification
     */
    @Override
    public String toString() {
        return commonDetails()
                + " | Qualification: " + qualification;
    }
}
