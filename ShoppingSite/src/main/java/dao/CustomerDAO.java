package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Customer;
import bean.Product;
//import bean.Product;

public class CustomerDAO extends DAO {

	
	public Customer search(String login, String password) throws Exception{
		
		Customer customer=null;
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from customer where login=? and password=?");
		st.setString(1, login);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();
//		List<Product>型へ変換
		while(rs.next()) {
			customer=new Customer();
			customer.setId(rs.getInt("id"));
			customer.setLogin(rs.getString("login"));
			customer.setPassword(rs.getString("password"));
		}
		
		st.close();
		con.close();
		
		return customer;
	}
	
	public int insert(Product product) throws Exception{
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement(
				"insert into product values(null, ?, ?)");
		st.setString(1, product.getName());
		st.setInt(2, product.getPrice());
		int line=st.executeUpdate();
		
		st.close();
		con.close();
		return line;
				
	}
}
