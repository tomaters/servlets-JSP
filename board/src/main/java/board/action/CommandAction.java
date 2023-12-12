package board.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// super interface that transfers requested commands
public interface CommandAction {
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
