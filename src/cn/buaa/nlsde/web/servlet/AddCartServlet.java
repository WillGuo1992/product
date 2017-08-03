package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.servive.ProductService;

public class AddCartServlet extends HttpServlet {
	private ProductService productService = new ProductService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		Product product = productService.findProductById(id);
		Map<Product,Integer> carts = (Map<Product, Integer>) request.getSession().getAttribute("carts");
		if(carts==null){
			carts = new HashMap<Product, Integer>();
		} 
		boolean b = carts.containsKey(product);
		if(b==true){
			int buySum = carts.get(product);
			buySum = buySum>=product.getPnum()?product.getPnum():buySum+1;
			carts.put(product, buySum);
		} else{
			carts.put(product , 1);
		}
		request.getSession().setAttribute("carts", carts);
		response.sendRedirect(request.getContextPath()+ "/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
