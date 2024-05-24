package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO extends DAO {
	
//	返り値がList型の戻り値が返ってくる
//	ListはProductクラスに基づいて決められた値（3つのフィールド）を持つ商品情報の集合を表す
//	つまり、serachメソッドは商品のリストをList<Product>型で返す	
	public List<Product> search(String keyword) throws Exception{
		List<Product> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement(
				"select * from product where name like ?");
		st.setString(1, "%" +keyword+ "%");
//		ResultSet型で返ってくるsqlの実行結果を
		ResultSet rs=st.executeQuery();
//		List<Product>型へ変換
		while(rs.next()) {
			Product p=new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getInt("price"));
			list.add(p);
		}
		
		st.close();
		con.close();
		
		return list;
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
