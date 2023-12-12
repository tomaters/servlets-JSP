package board.action;

import board.model.BoardDAO;
import board.model.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateFormAction implements CommandAction {
	@SuppressWarnings("removal")
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 int num = Integer.parseInt(request.getParameter("num"));
		 String pageNum = request.getParameter("pageNum");
		 BoardDAO processDB = BoardDAO.getInstance();
		 BoardVO article = processDB.updateGetArticle(num);
		 request.setAttribute("pageNum", new Integer(pageNum));
		 request.setAttribute("article", article);
		 return "/board/updateForm.jsp";
	}
}
