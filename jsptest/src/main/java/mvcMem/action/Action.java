package mvcMem.action;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcMem.control.ActionForward;

public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
