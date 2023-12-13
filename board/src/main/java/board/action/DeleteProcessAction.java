package board.action;

import board.model.BoardDAO;
import board.model.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteProcessAction implements CommandAction {

	@SuppressWarnings("removal")
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String pass = request.getParameter("pass");
		BoardDAO processDB = BoardDAO.getInstance();
		int check = processDB.deleteArticle(num, pass);
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		return "/board/deleteProcess.jsp";
	}
}
