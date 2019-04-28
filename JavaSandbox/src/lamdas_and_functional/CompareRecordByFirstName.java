package lamdas_and_functional;
import java.util.Comparator;

public class CompareRecordByFirstName implements Comparator<Record> {

	@Override
	public int compare(Record lhs, Record rhs) {
		return lhs.getFirstName().compareTo(rhs.getFirstName());
	}
}
