package page;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/page_servlet/*")
public class pageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러 호출...");
		EmpDAO dao=new EmpDAO(); //dao 객체 생성
		
		//요청한 페이지번호
		int curPage=1; //기본 페이지 주소
		if(request.getParameter("curPage") !=null) { // 페이지 번호를 클릭한 경우
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		System.out.println("요청한 페이지 번호:"+curPage);
		
		int count=dao.empCount(); // 레코드 갯수
		//전체 페이지 갯수(올림 처리를 해야 함)  math.ceil <- 올림
		int page_scale = 10; // 한 화면에 보여줄 레코드 갯수
		int totPage=(int)Math.ceil(count*1.0 / page_scale);
		System.out.println("페이지 갯수:"+totPage);
		//레코드 시작 번호
		int start=(curPage-1)* page_scale + 1;
		//레코드 마지막 번호
		int end=start + page_scale - 1;
		
		int block_scale=10; // 한 페이지에 출력할 페이지 갯수
		//페이지 블록의 갯수
		int tot_block=(int)Math.ceil(totPage * 1.0 / block_scale);
		//현재 페이지 블록 번호
		int cur_block=(int)Math.ceil((curPage-1)/block_scale)+1;
		int block_start=(cur_block-1)*block_scale +1; // 시작 페이지 번호
		int block_end=block_start + block_scale -1; // 끝 페이지 번호
		if(block_end > totPage) block_end=totPage;
		int prev_page=
				cur_block == 1? 1 : (cur_block -1) * block_scale; //이전 페이지
		int next_page=
				cur_block > tot_block ? (cur_block * block_scale)
									  :	(cur_block * block_scale)+1; // 다음 페이지
		if( next_page >= totPage ) next_page = totPage;
		System.out.println("페이지 블록 갯수:"+tot_block);
		System.out.println("현재 페이지 블록 번호:"+cur_block);
		System.out.println("페이지 시작 번호:"+block_start);
		System.out.println("페이지 끝 번호:"+block_end);
		System.out.println("이전 페이지 번호:"+prev_page);
		System.out.println("다음 페이지 번호:"+next_page);
		
		
		List<EmpDTO> list=dao.empList(start,end); // 사원 991건 목록이 넘어옴		
		request.setAttribute("list", list); // 출력전에 저장
// 페이지 나누기를 위한 관련 정보들을 저장		
		request.setAttribute("totPage", totPage);
		request.setAttribute("curBlock", cur_block);
		request.setAttribute("totBlock", tot_block);
		request.setAttribute("blockStart", block_start);
		request.setAttribute("blockEnd", block_end);
		request.setAttribute("prevPage", prev_page);
		request.setAttribute("nextPage", next_page);		
		
		
		String page="/page/list.jsp"; // 출력 페이지
		RequestDispatcher rd=request.getRequestDispatcher(page); // "" 확인해보자 출력문이 아니니
		rd.forward(request, response); 		 // 페이지 이동
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
