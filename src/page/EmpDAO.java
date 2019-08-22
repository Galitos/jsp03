package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class EmpDAO {
	
	//레코드 갯수 계산
	public int empCount() {
		int count=0;
		SqlSession session=MybatisManager.getInstance().openSession();
		count=session.selectOne("emp.empCount");
		session.close();
		return count;
	}
	//empList 레코트 시작번호,끝번호
	public List<EmpDTO> empList(int start, int end){
		List<EmpDTO> items= new ArrayList<>();
		SqlSession session=MybatisManager.getInstance().openSession();
		// 네임스페이스가 emp이고 id가 empList인 태그의 sql 명령어가 실행됨
		Map<String, Object> map= new HashMap<>();
		map.put("start", start); // 레코드 시작번호
		map.put("end", end); // 레코드 끝번호
		items=session.selectList("emp.empList", map);		
		return items;
		
	}
}
