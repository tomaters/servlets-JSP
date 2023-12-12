package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import board.action.CommandAction;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// printing is for debugging
public class ControllerAction extends HttpServlet {
	
	// for serializing and deserializing objects; when serialized (converted to byte stream), object's serialVersionUID is stored into stream
	// during de-serialization (byte stream back to object), serialVersionUID checked to ensure that the versions are compatible 
	private static final long serialVersionUID = 1L;
	
	// Map to handle command key and command as a pair
	// commands and classes are mapped in CommandProcess.properties text file
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	public void init(ServletConfig config) throws ServletException {
		// read init-param values in propertyConfig within web.xml file
		String configProperties = config.getInitParameter("propertyConfig");
		// construct Properties object that contains command and class mapping information
		Properties properties = new Properties();
		// construct object to read file
		FileInputStream fileInputStream = null;
		// store path to generate location of CommandProcess.properties file
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			// read contents in CommandProcess.properties
			fileInputStream = new FileInputStream(new File(path, configProperties));
			// store into properties the key and corresponding value of the command and class name
			properties.load(fileInputStream);
			System.out.println("properties: " + properties.toString());
		} catch(IOException e) {
			throw new ServletException(e);
		} finally {
			if(fileInputStream != null) 
				try { fileInputStream.close();
				} catch(IOException e2) {}
		}
		// create loop using iterator to save key and value into String forms
		Iterator<Object> keyIterator = properties.keySet().iterator();
		// take object one at a time and save into String command and className
		while(keyIterator.hasNext()) {
			String command = (String)keyIterator.next();
			System.out.println("command: " + command);
			String className = properties.getProperty(command);
			try {
				// code to turn String into a class
				@SuppressWarnings("rawtypes")
				Class commandClass = Class.forName(className);
				// construct object for corresponding class
				@SuppressWarnings("deprecation")
				Object commandInstance = commandClass.newInstance();
				System.out.println("commandInstance: " + commandInstance.toString());
				// store object into Map commandMap
				commandMap.put(command, commandInstance);
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// direct GET method to processRequest
		processRequest(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// direct POST method to processRequest
		processRequest(request, response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandAction commandAction = null;
		try {
			// store into command the request URI
			String command = request.getRequestURI();
			System.out.println("command: " + command); // /board/writeForm.do
			// if there is a context path in the command, remove it by using substring function. but here, the name of the path and folder are the same
//			if(command.indexOf(request.getContextPath()) == 0) {
//				command = command.substring(request.getContextPath().length());
//			}
			System.out.println("command: " + command); // writeForm.do
			// retrieve corresponding command object from commandMap in form of interface CommandAction
			commandAction = (CommandAction)commandMap.get(command);
			System.out.println("commandAction: " + commandAction.toString());
			// process request using CommandAction and store data into String view
			view = commandAction.requestProcess(request, response);
		} catch(Throwable e) {
			throw new ServletException(e);
		}
		// apply RequestDispatcher to process request stored into view
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		// forward the request and response to the appropriate jsp page or servlet
		dispatcher.forward(request, response);
	}
}
