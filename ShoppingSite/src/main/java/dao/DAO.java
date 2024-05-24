package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

//DAOの共通処理であるデータベース接続を行うDAOクラスの作成
public class DAO {
	
//	データソースを保存する変数ds
//	データソースは、データベースに接続するたびに取得する必要ない
//	dsをstaticフィールドにした理由は、DAOのサブクラスのインスタンス間で、データソースを1つだけ持つようにするため
	static DataSource ds;
	
//	getConnectionメソッドを定義
//	Connection型で返す
	public Connection getConnection() throws Exception{
		if(ds==null) {
			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/book");
		}
		
		return ds.getConnection();
	}
}
