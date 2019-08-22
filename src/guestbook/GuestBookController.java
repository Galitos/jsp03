package guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dao.GuestBookDAO;
import guestbook.dto.GuestBookDTO;

@WebServlet("/guestbook_servlet/*")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러 호출...");
		//사용자가 요청한 url 정보 
		String url=request.getRequestURI().toString();
		GuestBookDAO dao= new GuestBookDAO();
		if(url.contains("list.do")) {
			// 방명록 리스트를 받아옴
			List<GuestBookDTO> items=dao.getlist();
			request.setAttribute("list", items); // 출력하기 위해 저장
			String page="/guestbook/list.jsp"; //출력 페이지로 넘김 
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(url.contains("insert.do")) {
		//	request.setCharacterEncoding("utf-8");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String passwd=request.getParameter("passwd");
			String content=request.getParameter("content");
			GuestBookDTO dto=
					new GuestBookDTO(name, email, passwd, content);
			dao.getInsert(dto); // 레코드가 추가됨
			System.out.println("dto :"+dto);
			String page="/guestbook_servlet/list.do";
			RequestDispatcher rd=
					request.getRequestDispatcher(page);
			rd.forward(request, response);			
		}else if(url.contains("passwd_check.do")){			
			int idx=Integer.parseInt(request.getParameter("idx"));
			String passwd=request.getParameter("passwd");
			boolean result=dao.passwdCheck(idx, passwd);
			System.out.println("result:" + result);
			String page="";
		if(result) {
			page="/guestbook/edit.jsp";
			GuestBookDTO dto=dao.gbDetail(idx);
			System.out.println("idx:"+dto);
			request.setAttribute("dto", dto);
		}else {
			page="/guestbook_servlet/list.do";			
		}
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);		
		}else if(url.indexOf("update.do")!=-1) {
			
			int idx=Integer.parseInt(request.getParameter("idx"));
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String passwd=request.getParameter("passwd");
			String content=request.getParameter("content");
			GuestBookDTO dto=new GuestBookDTO();
			dto.setIdx(idx);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			dao.gbUpdate(dto);
			response.sendRedirect("/guestbook_servlet/list.do");
		}else if(url.contains("search.do")) { // 검색
			//검색 옵션
			String searchKey=request.getParameter("searchKey");
			//검색키워드
			String search=request.getParameter("search");
			// 검색된 내용이 리스트로 넘어옴
			System.out.println("search.do 실행중...");
			List<GuestBookDTO> items=dao.searchList(searchKey, search);
			System.out.println("items.....");
			// 출력 페이지로 넘기기 전에 저장
			request.setAttribute("list", items);
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("search", search);			
			// 출력 페이지로 이동
			String page="/guestbook/list.jsp"; 
			RequestDispatcher rd=
					request.getRequestDispatcher(page);
			rd.forward(request, response);			
		}
		
}//doget() 마지막

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
