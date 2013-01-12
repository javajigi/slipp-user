package net.slipp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.slipp.support.AbstractBaseAT;

import org.junit.Test;

public class HomeAT extends AbstractBaseAT {
	@Test
	public void index() {
		driver.get("http://localhost:8080/slipp-user");
		assertThat(driver.getTitle(), is("SLiPP"));
	}
}
