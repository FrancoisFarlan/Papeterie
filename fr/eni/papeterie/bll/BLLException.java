package fr.eni.papeterie.bll;

public class BLLException extends Exception{

	
	private static final long serialVersionUID = 1L;

	
	public BLLException(String message, Throwable cause) {
		super(message, cause);
		
	}


	public BLLException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	public BLLException(Throwable cause) {
		super(cause);
	}
	
	

}
