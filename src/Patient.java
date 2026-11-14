/**
 * A patient attending the health service.
 *
 * <p>A patient is modelled as its own class because the name and contact
 * number are patient-specific facts that an appointment merely references.
 * This keeps patient details separate from the appointment itself.</p>
 */
public class Patient {

    /** Full name of the patient. */
    private final String name;

    /** Mobile phone number (digits only) used to contact the patient. */
    private final String mobilePhone;

    /**
     * Creates a patient after validating the contact details.
     *
     * @param name        full patient name; must not be blank
     * @param mobilePhone mobile number; digits only, 7 to 15 digits
     * @throws IllegalArgumentException if name or phone number are invalid
     */
    public Patient(String name, String mobilePhone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Patient name must not be blank.");
        }
        if (mobilePhone == null || !isValidPhone(mobilePhone.trim())) {
            throw new IllegalArgumentException("Mobile phone must be 7 to 15 digits.");
        }
        this.name = name.trim();
        this.mobilePhone = mobilePhone.trim();
    }

    /**
     * Checks whether a phone number is 7 to 15 digits long and contains digits only.
     *
     * @param phone the number to check
     * @return {@code true} if the number is a valid digit-only mobile number
     */
    private static boolean isValidPhone(String phone) {
        if (phone.length() < 7 || phone.length() > 15) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the patient's name.
     *
     * @return patient name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's mobile phone number.
     *
     * @return mobile phone number
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * {@inheritDoc}
     *
     * @return patient name and contact number in readable form
     */
    @Override
    public String toString() {
        return "Patient: " + name + " | Mobile: " + mobilePhone;
    }
}
