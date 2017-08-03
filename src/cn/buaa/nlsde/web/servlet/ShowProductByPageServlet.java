package cn.buaa.nlsde.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buaa.nlsde.domain.PageBean;
import cn.buaa.nlsde.servive.ProductService;

public class ShowProductByPageServlet extends HttpServlet {

	private ProductService productService = new ProductService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String category = request.getParameter("category");
		String currentPage = request.getParameter("currentPage");
		int pageNum = 0 ;
		if(currentPage!=null)
			pageNum = Integer.valueOf(currentPage);
		int pageSize = 8 ;
		PageBean pageBean = (PageBean) request.getSession().getAttribute("pageBean");
		if(currentPage!=null && pageBean!=null){
			category = pageBean.getCategory();
		}
		pageBean = productService.findProductByPage(pageNum,pageSize,category);
		request.getSession().setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
