package mybatis.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis.service.FactoryService;
import mybatis.vo.EmpVO;

/*
 * Mybatis 사용 목적 중 하나가
 * DAO로부터 SQL문을 분리하는 것이다.
 * 분리된 SQL문은 SQL Mapper 파일에 작성하며 
 * DAO에서는 SqlSession 객체가 SQL Mapper 파일을 참조하게 된다.
 */
public class EmpDAO {
	public static List<EmpVO> getTotal(){
		/*
		 * 이미 생성되어 있는 Factory를 이용하여 SqlSession객체를 얻어 낸다.
		 * select 작업은 auto commit을 하지 않아도 되기 때문에 
		 * openSession() 메소드 호출시 true를 인자값으로 주지 않아도 된다.
		 */
		SqlSession ss = FactoryService.getFactory().openSession();
		
		List<EmpVO> list = ss.selectList("emp.total"); //하나의 data만 검색 => selectOne()
		ss.close();
		return list;
	}
	
	public static int add(String empno, String ename, String position) {
		/*
		 * mapper를 호출할 때  Map 자료구조로 전달해야 하므로 Map  계열의 객체 생성
		 */
		Map<String, String> map =  new Hashtable<String, String>();
		
		map.put("empno", empno); 
		map.put("ename", ename); 
		map.put("position", position); 
		
		/*
		 * SqlSession 객체를 얻을 때 openSession(true)와 같이 호풀하면 DML(insert, update, delete)실행 시 auto commit을 수행하는
		 * SqlSession 객체를 얻을 수 있다. 꼭 true 를 인자 값으로 준다.
		 */
		
		SqlSession ss = FactoryService.getFactory().openSession(true);
		int cnt = ss.insert("emp.add",map);
		ss.close();
		return cnt; 
	}
	
		 
}
