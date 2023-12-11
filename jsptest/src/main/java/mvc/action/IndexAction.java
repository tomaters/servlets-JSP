package mvc.action;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.control.ActionForward;

public class IndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// lay out CRUD logic here

		
		//request.setAttribute(null, response);
		System.out.println("IndexAction executed");
		
		return new ActionForward("index.jsp", false);
	}

}
