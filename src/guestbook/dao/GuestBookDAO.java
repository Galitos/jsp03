package guestbook.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import guestbook.dto.GuestBookDTO;
import sqlmap.MybatisManager;

public class GuestBookDAO {
		
	
	//방명록 수정
	public void gbUpdate(GuestBookDTO dto) {
		SqlSession session=MybatisManager.getInstance().openSession();
		session.update("guestbook.gbUpdate", dto);
		session.commit();
		session.close();
	}
	
	
	//비밀번호 체크 함수
	public boolean passwdCheck(int idx, String passwd) {
		boolean result=false;
		SqlSession session=MybatisManager.getInstance().openSession();
//		GuestBookDTO dto = new GuestBookDTO();
//		dto.setIdx(idx);
//		dto.setPasswd(passwd);
//		int count=session.selectOne("guestbook.passwdCheck", dto);
		Map<String,Object> map = new HashMap<>();
		map.put("idx", idx);
		map.put("passwd", passwd);
		int count=session.selectOne("guestbook.passwdCheck", map);
		result=count==1 ? true : false; //1이면 true 저장(result) ,0이면 false
		session.close();
		return result;
	}
	//방명록 상세 내용을 리턴하는 함수
	public GuestBookDTO gbDetail(int idx) {
		GuestBookDTO dto= new GuestBookDTO();
		SqlSession session=MybatisManager.getInstance().openSession();
		dto=session.selectOne("guestbook.gbDetail", idx);
		session.close();
		return dto;
	}
	
	//검색옵션과 검색키워드를 입력 받아 해당하는 내용들을 리턴하는 함수
	public List<GuestBookDTO> searchList(String searchKey, String search){
		SqlSession session=MybatisManager.getInstance().openSession(); //sql실행 객체
		List<GuestBookDTO> list=null; //리스트 생성
		if(searchKey.equals("name_content")) { //이름+내용으로 찾는 경우
			list=session.selectList("guestbook.searchListAll", "%"+search+"%");
		}else { //이름으로 찾기, 내용으로 찾기
			//mybatis 함수에  2개 이상의 값을 전달할 경우 dto 또는 map 사용 
			Map<String, Object> map = new HashMap<>();
			map.put("searchKey", searchKey);
			map.put("search","%"+search+"%");
			list=session.selectList("guestbook.searchList", map);
		}
		session.close();
		return list;
	}
	
	
	// 목록을 출력해서 리턴하는 함수
	public List<GuestBookDTO> getlist(){
	SqlSession session=MybatisManager.getInstance().openSession();
	List<GuestBookDTO> list=session.selectList("guestbook.gbList");
	session.close();
	return list;
	}
	
	// 글을 입력하는 함수
	public void getInsert(GuestBookDTO dto) {
		//sql 실행 객체 생성		
		SqlSession session=MybatisManager.getInstance().openSession();
		session.insert("guestbook.gbInsert", dto);//레코드 추가
		session.commit();// 저장
		session.close(); // 종료
	}
}
