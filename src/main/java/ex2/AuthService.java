package ex2;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthService {
	private final int attemptThreshold;
	private final int lockSeconds;
	private final Map<String, String> users = new HashMap<>();
	private final Map<String, Integer> wrongAttempts = new HashMap<>();
	private final Map<String, LocalDateTime> lockedUsers = new HashMap<>();

	{
		users.put("supra89kren@gmail.com", "password");
	}

	public AuthService(int attemptThreshold, int lockSeconds) {
		this.attemptThreshold = attemptThreshold;
		this.lockSeconds = lockSeconds;
	}

	public LoginResult login(String email, String password) {
		if (userIsLocked(email)) {
			return new LoginResult(false, true);
		}
		lockedUsers.remove(email);

		Optional<String> userFromDb = findUser(email, password);
		if (userFromDb.isPresent()) {
			wrongAttempts.remove(email);
			return new LoginResult(true, false);
		}

		Integer attemptsCount = wrongAttempts.compute(email, (k, v) -> v == null ? 1 : ++v);
		if (attemptThreshold >= attemptsCount) {
			wrongAttempts.put(email, attemptsCount);
			return new LoginResult(false, false);
		}

		lockedUsers.computeIfAbsent(email, k -> LocalDateTime.now());
		return new LoginResult(false, true);
	}

	private Optional<String> findUser(String email, String password) {
		return Optional.ofNullable(users.get(email))
				.filter(pwd -> pwd.equals(password));
	}

	private boolean userIsLocked(String email) {
		return lockedUsers.containsKey(email) && lockedUsers.get(email).plusSeconds(lockSeconds).isAfter(getCurrentTime());
	}

	protected LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}
}
