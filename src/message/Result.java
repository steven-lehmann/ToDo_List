package message;

import ToDo_Server.Client;

public class Result extends Message {
	
	/**
	 * This constructor is used by most messages
	 */
	public Result(Class<?> msgClass, boolean result) {
		super(new String[] {"Result", msgClass.getSimpleName(), Boolean.toString(result)});		
	}

	@Override
	public void process(Client client) {
		// Auto-generated method stub
		
	}
	

}
