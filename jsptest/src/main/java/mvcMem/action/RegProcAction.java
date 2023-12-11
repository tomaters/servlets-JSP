package mvcMem.action;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcMem.control.ActionForward;
import mvcMem.model.StudentDAO;
import mvcMem.model.StudentVO;

public class RegProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		StudentDAO studentDAO = StudentDAO.getInstance();
		StudentVO studentVO = new StudentVO(request.getParameter("id"), request.getParameter("pass"),
				request.getParameter("name"), request.getParameter("phone1"), request.getParameter("phone2"),
				request.getParameter("phone3"), request.getParameter("email"), request.getParameter("zipcode"),
				request.getParameter("address1"), request.getParameter("address2"));
		boolean flag = studentDAO.memberInsert(studentVO);
		request.setAttribute("flag", flag);
		return new ActionForward("/mvcMem/regProc.jsp", false);
	}
}
