import java.util.List;

/**
 * A general practitioner (GP): a family doctor who can diagnose common
 * conditions and prescribe medicine.
 *
 * <p>This class extends {@link HealthProfessional} and adds the capability
 * specific to GPs: the authority to prescribe medication.</p>
 */
public class GeneralPractitioner extends HealthProfessional {

    /** Whether this GP is authorised to prescribe medicine. */
    private final boolean canPrescribeMedicine;

    /**
     * Creates a general practitioner with all details supplied.
     *
     * @param id                   numeric-only identifier
     * @param name                 full name
     * @param availableDays       working weekdays; {@code null} uses the default week
     * @param canPrescribeMedicine {@code true} if this GP can prescribe medicine
     */
    public GeneralPractitioner(String id, String name, List<String> availableDays,
                               boolean canPrescribeMedicine) {
        super(id, name, availableDays);
        this.canPrescribeMedicine = canPrescribeMedicine;
    }

    /**
     * Creates a general practitioner who can prescribe medicine, using the
     * default working week.
     *
     * @param id   numeric-only identifier
     * @param name full name
     */
    public GeneralPractitioner(String id, String name) {
        this(id, name, null, true);
    }

    /**
     * {@inheritDoc}
     *
     * @return the label "General Practitioner"
     */
    @Override
    public String getSpecialty() {
        return "General Practitioner";
    }

    /**
     * {@inheritDoc}
     *
     * @return the GP's prescribing capability in plain language
     */
    @Override
    public String getRoleDescription() {
        return canPrescribeMedicine
                ? "Provides primary care and can prescribe medicine."
                : "Provides primary care but cannot prescribe medicine.";
    }

    /**
     * {@inheritDoc}
     *
     * @return common GP details plus the prescribing capability
     */
    @Override
    public String toString() {
        return commonDetails()
                + " | Prescribes medicine: " + (canPrescribeMedicine ? "Yes" : "No");
    }
}
