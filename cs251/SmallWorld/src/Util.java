import java.util.List;

public class Util {

	public static<T> T randomItem( List<T> stuff ) {
		if (stuff == null || stuff.size() == 0)
			return null;
		int i = (int) (Math.random() * stuff.size());
		return stuff.get(i);
	}

	
}
