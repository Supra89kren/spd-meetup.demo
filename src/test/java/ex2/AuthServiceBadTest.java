package ex2;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AuthServiceBadTest {

	@Test
	public void expectedUserToBeLockedAfterReachingMaxAtemptCount() throws Exception {
		AuthService authService = new AuthService(2, 5);
		LoginResult login1 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login1.isSuccess());
		Assert.assertEquals(FALSE, login1.isLocked());

		LoginResult login2 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login2.isSuccess());
		Assert.assertEquals(FALSE, login2.isLocked());

		LoginResult login3 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login3.isSuccess());
		Assert.assertEquals(TRUE, login3.isLocked());
	}

	@Test
	public void expectedUserToBeLockedDuring5Seconds() throws InterruptedException {
		AuthService authService = new AuthService(2, 5);
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");

		Thread.sleep(1000);
		LoginResult login4 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login4.isSuccess());
		Assert.assertEquals(TRUE, login4.isLocked());

		Thread.sleep(3000);
		LoginResult login5 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login5.isSuccess());
		Assert.assertEquals(TRUE, login5.isLocked());
	}

	@Test
	public void expectedUserToBeLockedAgainAfter5Seconds() throws InterruptedException {
		AuthService authService = new AuthService(2, 5);
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");
		LoginResult login3 = authService.login("supra89kren@gmail.com", "123456");

		Assert.assertEquals(FALSE, login3.isSuccess());
		Assert.assertEquals(TRUE, login3.isLocked());

		Thread.sleep(5000);
		LoginResult login5 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login5.isSuccess());
		Assert.assertEquals(TRUE, login5.isLocked());
	}

	@Test
	public void expectedUserToBeAbleToLoginAfter5Seconds() throws InterruptedException {
		AuthService authService = new AuthService(2, 5);
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");
		LoginResult login3 = authService.login("supra89kren@gmail.com", "123456");

		Assert.assertEquals(FALSE, login3.isSuccess());
		Assert.assertEquals(TRUE, login3.isLocked());

		Thread.sleep(6000);
		LoginResult login5 = authService.login("supra89kren@gmail.com", "password");
		Assert.assertEquals(TRUE, login5.isSuccess());
		Assert.assertEquals(FALSE, login5.isLocked());
	}
}
