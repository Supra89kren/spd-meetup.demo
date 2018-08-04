package ex2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AuthServiceFineTest {
	private AuthService authService;

	@Before
	public void setUp() throws Exception {
		authService = Mockito.spy(new AuthService(2, 5));
	}

	@Test
	public void expectedUserToBeLockedAfterReachingMaxAtemptCount() throws Exception {
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
	public void expectedUserToBeLockedDuring5Seconds() {
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");

		Mockito.doReturn(LocalDateTime.now().plusSeconds(1)).when(authService).getCurrentTime();
		LoginResult login4 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login4.isSuccess());
		Assert.assertEquals(TRUE, login4.isLocked());

		Mockito.doReturn(LocalDateTime.now().plusSeconds(3)).when(authService).getCurrentTime();
		LoginResult login5 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login5.isSuccess());
		Assert.assertEquals(TRUE, login5.isLocked());
	}

	@Test
	public void expectedUserToBeLockedAgainAfter5Seconds() {
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");
		LoginResult login3 = authService.login("supra89kren@gmail.com", "123456");

		Assert.assertEquals(FALSE, login3.isSuccess());
		Assert.assertEquals(TRUE, login3.isLocked());

		Mockito.doReturn(LocalDateTime.now().plusSeconds(5)).when(authService).getCurrentTime();
		LoginResult login5 = authService.login("supra89kren@gmail.com", "123456");
		Assert.assertEquals(FALSE, login5.isSuccess());
		Assert.assertEquals(TRUE, login5.isLocked());
	}

	@Test
	public void expectedUserToBeAbleToLoginAfter5Seconds() {
		authService.login("supra89kren@gmail.com", "123456");
		authService.login("supra89kren@gmail.com", "123456");
		LoginResult login3 = authService.login("supra89kren@gmail.com", "123456");

		Assert.assertEquals(FALSE, login3.isSuccess());
		Assert.assertEquals(TRUE, login3.isLocked());

		Mockito.doReturn(LocalDateTime.now().plusSeconds(5)).when(authService).getCurrentTime();
		LoginResult login5 = authService.login("supra89kren@gmail.com", "password");
		Assert.assertEquals(TRUE, login5.isSuccess());
		Assert.assertEquals(FALSE, login5.isLocked());
	}
}
