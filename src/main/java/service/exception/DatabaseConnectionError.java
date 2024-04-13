package service.exception;

public class DatabaseConnectionError extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DatabaseConnectionError(String msg) {
		super(msg);
	}
	
}
