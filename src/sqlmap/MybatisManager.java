package sqlmap;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//mybatis로 sql을 실행할 수 있는 객체(sqlSession)를 만드는 SqlSessionFactory 리턴 코드
public class MybatisManager {
	private static SqlSessionFactory instance;
	private MybatisManager() {} //외부에서 접근할수 없음 private 
	public static SqlSessionFactory getInstance() {
		Reader reader=null;
		try {
			reader=Resources.getResourceAsReader("sqlmap/sqlMapConfig.xml");
			instance=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(reader !=null)reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
}
