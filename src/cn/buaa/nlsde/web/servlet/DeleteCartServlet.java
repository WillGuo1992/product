package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.servive.ProductService;

public class DeleteCartServlet extends HttpServlet {
	private ProductService productService = new ProductService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Product product = productService.findProductById(id);
		Map<Product,Integer> carts = (Map<Product, Integer>) request.getSession().getAttribute("carts");
		carts.remove(product);
		request.getSession().setAttribute("carts", carts);
		response.sendRedirect(request.getContextPath()+ "/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
