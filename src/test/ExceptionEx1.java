package test;

import java.sql.SQLException;
import java.sql.SQLTransientException;

public class ExceptionEx1 {

	public static void main(String[] args) {
		A a = new B();
		try {
			a.method();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}

class B implements A{
	public void method() throws SQLTransientException{
		//보다 상위클래스의 exception을 발생시킬 수 없음.(즉 하위만)
	}
}

interface A {
	void method() throws SQLException;
}
