package ToDo_Server;

import message.Message;

public interface Sendable {	
		public abstract String getName(); // The name of the target being sent a message
		public abstract void send(Message msg); // The method to sent to this target
	}


