package mvcMem.action;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvcMem.control.ActionForward;
import mvcMem.model.StudentDAO;
import mvcMem.model.StudentVO;

public class ModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StudentDAO studentDAO = StudentDAO.getInstance();
		HttpSession httpSession = request.getSession();
		String loginID = (String)httpSession.getAttribute("loginID");
		StudentVO studentVO = studentDAO.getMember(loginID);
		request.setAttribute("id", studentVO.getId());
		request.setAttribute("pass", studentVO.getPass());
		request.setAttribute("name", studentVO.getName());
		request.setAttribute("phone1", studentVO.getPhone1());
		request.setAttribute("phone2", studentVO.getPhone2());
		request.setAttribute("phone3", studentVO.getPhone3());
		request.setAttribute("email", studentVO.getEmail());
		request.setAttribute("zipcode", studentVO.getZipcode());
		request.setAttribute("address1", studentVO.getAddress1());
		request.setAttribute("address2", studentVO.getAddress2());
		studentDAO.updateMember(studentVO);
		return new ActionForward("/mvcMem/modifyForm.jsp", false);
	}
}
