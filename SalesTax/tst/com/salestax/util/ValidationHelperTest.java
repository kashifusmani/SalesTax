package com.salestax.util;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

public class ValidationHelperTest {

	@Test(expected = IllegalArgumentException.class)
	public void testValidateForNull() {
		ValidationHelper.validateForNull(null, "someobj");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateNotBlank() {
		ValidationHelper.validateNotBlank(" ", "someobj");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateIterableForNullEntries() {
		List<String> list = new ArrayList<String>();
		list.add(null);
		ValidationHelper.validateIterableForNullEntries(list, "list");
	}
}
