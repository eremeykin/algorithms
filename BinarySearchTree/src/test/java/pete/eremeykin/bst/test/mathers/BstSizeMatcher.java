package pete.eremeykin.bst.test.mathers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import pete.eremeykin.bst.Bst;

public final class BstSizeMatcher extends TypeSafeMatcher<Bst<?, ?>> {

	private Integer size;

	private BstSizeMatcher(Integer size) {
		this.size = size;
	}

	public void describeTo(Description descr) {
		descr.appendText(String.format("Bst has size %s", size));
	}

	@Override
	protected void describeMismatchSafely(Bst<?, ?> item, Description mismatchDescription) {
		mismatchDescription.appendText(String.format("Bst has size %s", item.size()));
	}

	@Override
	protected boolean matchesSafely(Bst<?, ?> bst) {
		return bst.size() == size;
	}

	public static Matcher<Bst<?, ?>> hasSize(Integer size) {
		return new BstSizeMatcher(size);
	}

}
