package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buaa.nlsde.domain.PageBean;
import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.servive.ProductService;

public class FindProductBySearchServlet extends HttpServlet {

	private ProductService productService = new ProductService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		PageBean pageBean = productService.findProductBySearch(name);
		System.out.println(pageBean.getPs().size());
		for(Product p : pageBean.getPs()){
			System.out.println(p.getName());
		}
		request.getSession().setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
