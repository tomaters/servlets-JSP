package mvcMem.action;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvcMem.control.ActionForward;
import mvcMem.model.StudentDAO;

public class DeleteProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		StudentDAO studentDAO = StudentDAO.getInstance();
		HttpSession httpSession = request.getSession();
		String loginID = (String) httpSession.getAttribute("loginID");
		String pass = request.getParameter("pass");
		int result = studentDAO.deleteMember(loginID, pass);
		if (result != 0) {
			httpSession.invalidate();
		}
		request.setAttribute("result", result);
		return new ActionForward("/mvcMem/deleteProc.jsp", false);
	}
}