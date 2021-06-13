Team-Members: Gowsigan Gowribalan, Femin Jose Kunnathuparambil, Steven Lehmann

JavaFX Version: JavaFX 15

Features:

Be able to serve multiple clients in parallel (like a web server)
Implement all of the API commands as described
The server listens on port 50002
Messages: Ping, CreateLogin, Login, CreateToDo, ListToDo, GetToDo, DeleteToDo, ChangePassword, Logout, (Result)
Validate data on the server: (username, password, ToDo-Attributes (z.B. due-dates today or in the future)
Support Due-Dates (optional attribute, user do not have to use a dueDate)
Hash the passwords
Use realtokens for user login (big random number)
MVC client that supports all API features (+ support due-dates)
Nice GUI: Display all ToDo entries in a list, Validate all(username, password, ToDo-title) input data (on the client)
