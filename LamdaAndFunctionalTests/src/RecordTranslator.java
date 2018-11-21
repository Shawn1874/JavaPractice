import java.util.function.Function;

public class RecordTranslator<T, R> implements Function<T, R> {

	private RecordWithKeyValues translate(Record original) {
		RecordWithKeyValues translated = new RecordWithKeyValues();
		translated.entries.put("Identifier", Integer.toString(original.identifier));
		translated.entries.put("FirstName", original.lastName);
		translated.entries.put("LastName", original.firstName);
		return translated;
	}

	@SuppressWarnings("unchecked")
	@Override
	public R apply(T t) {
		return (R) translate((Record) t);
	}
}
