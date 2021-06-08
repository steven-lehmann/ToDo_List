package ToDo_Server;

import java.io.IOException;
import java.net.Socket;
import java.time.Instant;
import java.util.logging.Logger;

import message.Message;
import message.Result;

public class Client implements Sendable {
	private Socket socket;
	private String name;
	Account account = null;
	private boolean clientReachable = true;
	private Instant lastUsage;
	private String token = null;
	private static Logger logger = Logger.getLogger("");
	
	/**
	 * Create a new client object, communicating over the given socket. Immediately
	 * start a thread to receive messages from the client.
	 */
	public Client(Socket socket) {
		this.socket = socket;
		this.lastUsage = Instant.now();

		// Create thread to read incoming messages
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					while (clientReachable) {
						Message msg = Message.receive(socket);

						// Note the syntax "Client.this" - writing "this" would reference the Runnable
						// object
						if (msg != null)
							msg.process(Client.this);
						else { // Invalid message or broken socket
							Client.this.send(new MessageError());
						}

						lastUsage = Instant.now();
					}
				} catch (Exception e) {
					logger.info("Client " + Client.this.getName() + " disconnected");
				} finally {
					// When the client is no longer reachable, remove authentication and account
					token = null;
					account = null;
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		logger.info("New client created: " + this.getName());
	
	}

	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			// Uninteresting
		}
		
	}
	
	public String toString() {
		return name + ": " + socket.toString();
	}

	
	/**
	 * Send a message to this client. In case of an exception, log the client out.
	 */
	@Override // Sendable
	public void send(Message msg) {
		try {
			msg.send(socket);
			lastUsage = Instant.now();
		} catch (IOException e) {
			logger.warning("Client " + Client.this.getName() + " unreachable; logged out");
			this.token = null;
			clientReachable = false;
		}
	}

	@Override
	public String getName() {
		String name = null;
		if (account != null) name = account.getUsername();
		return name;
	}

	public String getToken() {
		return token;
	}

	
}
