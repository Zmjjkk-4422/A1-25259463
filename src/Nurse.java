import java.util.List;

/**
 * A nurse who supports doctors on a ward and may be trained to take blood
 * samples.
 *
 * <p>This class extends {@link HealthProfessional} and adds the ward the
 * nurse works on and the blood-sampling capability.</p>
 */
public class Nurse extends HealthProfessional {

    /** Ward or unit the nurse is assigned to, for example "Emergency". */
    private final String ward;

    /** Whether this nurse is trained to take blood samples. */
    private final boolean canTakeBloodSample;

    /**
     * Creates a nurse with all details supplied.
     *
     * @param id                 numeric-only identifier
     * @param name               full name
     * @param availableDays      working weekdays; {@code null} uses the default week
     * @param ward               ward or unit the nurse works on
     * @param canTakeBloodSample {@code true} if this nurse can take blood samples
     */
    public Nurse(String id, String name, List<String> availableDays,
                 String ward, boolean canTakeBloodSample) {
        super(id, name, availableDays);
        if (ward == null || ward.isBlank()) {
            throw new IllegalArgumentException("Ward must not be blank.");
        }
        this.ward = ward.trim();
        this.canTakeBloodSample = canTakeBloodSample;
    }

    /**
     * Creates a nurse who can take blood samples, using the default working
     * week.
     *
     * @param id   numeric-only identifier
     * @param name full name
     * @param ward ward or unit the nurse works on
     */
    public Nurse(String id, String name, String ward) {
        this(id, name, null, ward, true);
    }

    /**
     * {@inheritDoc}
     *
     * @return the label "Nurse"
     */
    @Override
    public String getSpecialty() {
        return "Nurse";
    }

    /**
     * {@inheritDoc}
     *
     * @return the nurse's ward and blood-sampling capability in plain language
     */
    @Override
    public String getRoleDescription() {
        return "Works on the " + ward + " ward"
                + (canTakeBloodSample ? " and can take blood samples." : " and does not take blood samples.");
    }

    /**
     * {@inheritDoc}
     *
     * @return common nurse details plus ward and blood-sampling capability
     */
    @Override
    public String toString() {
        return commonDetails()
                + " | Ward: " + ward
                + " | Takes blood samples: " + (canTakeBloodSample ? "Yes" : "No");
    }
}
