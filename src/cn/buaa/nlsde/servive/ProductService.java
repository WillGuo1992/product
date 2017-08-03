package cn.buaa.nlsde.servive;

import cn.buaa.nlsde.dao.ProductDao;
import cn.buaa.nlsde.domain.PageBean;
import cn.buaa.nlsde.domain.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	public PageBean findProductByPage(int pageNum, int pageSize, String category) {
		PageBean pageBean = new PageBean();
		int count = productDao.findAllCount(category);
		int totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
		if(totalPage<pageNum){
			pageNum = totalPage;
		}
		if(pageNum<1)
			pageNum = 1;
		pageBean = productDao.findByPage(pageNum,pageSize,category);
		pageBean.setCategory(category);
		pageBean.setCurrentCount(pageSize);
		pageBean.setCurrentPage(pageNum);
		pageBean.setTotalCount(count);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	public PageBean findProductBySearch(String name) {
		PageBean pageBean = new PageBean();
		pageBean = productDao.findBySearch(name);
		pageBean.setCategory("模糊查询");
		
		return pageBean;
		
	}
	public Product findProductById(String id) {
		Product product = productDao.findProductById(id);
		return product;
	}

}
