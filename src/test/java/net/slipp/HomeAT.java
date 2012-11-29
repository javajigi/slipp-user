package net.slipp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.support.AbstractBaseAT;

import org.junit.Test;

public class HomeAT extends AbstractBaseAT {
	@Test
	public void index() {
		driver.get("http://localhost:8080");
		assertThat(driver.getTitle(), is("SLiPP"));
	}
}
