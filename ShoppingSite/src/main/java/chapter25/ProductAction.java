package chapter25;

import java.util.List;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ProductAction extends Action{
	public String execute(
				HttpServletRequest request, HttpServletResponse response
			) throws Exception{
		
		HttpSession session=request.getSession();
		
		String keyword=request.getParameter("keyword");
		if(keyword==null) {
			keyword="";
		}
		
		ProductDAO dao=new ProductDAO();
		List<Product> list=dao.search(keyword);
		
		session.setAttribute("list", list);
		
		return "product.jsp";
	}
	
}
