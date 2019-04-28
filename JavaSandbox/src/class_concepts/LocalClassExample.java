package class_concepts;

public class LocalClassExample {

	// Regex to validate a phone number digit
	static String regularExpression = "[^0-9]";

	// The only method of the LocalClassExample class, other than the main
	public static boolean validatePhoneNumber(String phoneNumber) {

		int numberLength = 10;

		// Local class defined within a method
		class PhoneNumber {

			boolean valid = false;

			PhoneNumber(String phoneNumber) {
				// numberLength = 7;
				String currentNumber = phoneNumber.replaceAll(regularExpression, "");
				if (currentNumber.length() == numberLength) {
					valid = true;
				}
			}
		}

		PhoneNumber numberToValidate = new PhoneNumber(phoneNumber);
		return numberToValidate.valid;
	}
}
