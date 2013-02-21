package net.slipp.service.user;

public class NotFoundExistedUserException extends Exception {
	private static final long serialVersionUID = -8630727195769215691L;
	
	private String userId;
	
	public NotFoundExistedUserException(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s는 존재하지 않는 아이디입니다.", this.userId);
	}
}
