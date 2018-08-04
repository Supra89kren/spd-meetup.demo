package ex2;

public class LoginResult {
	private boolean success;
	private boolean locked;

	public LoginResult(boolean success, boolean locked) {
		this.success = success;
		this.locked = locked;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
