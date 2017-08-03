<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${pageBean.category }&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${pageBean.category }</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${pageBean.totalCount }种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								<table cellspacing="0" class="booklist">
									<c:forEach items="${pageBean.ps }" var="product" varStatus="vs">
									<c:if test="${vs.index%4==0 }">
									<tr>
									</c:if>
										<td>
											<div class="divbookpic">
												<p>
													<a href="${pageContext.request.contextPath }/findProductById?id=${product.id}"><img src="bookcover/101.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="${pageContext.request.contextPath }/findProductById?id=${product.id}">书名:${product.name }<br />售价:${product.price } </a>
											</div>
										</td>
									<c:if test="${vs.index%4==4-1 }">
									</tr>
									</c:if>
									</c:forEach>
								</table>

								<div class="pagination">
									<ul>


									<li class="disablepage"><a
											href="${pageContext.request.contextPath  }/showProductByPage?currentPage=${pageBean.currentPage==1?1:pageBean.currentPage-1}&category=${pageBean.category}">&lt;&lt;上一页</a>
										</li>


										<li>第${pageBean.currentPage }页/共${pageBean.totalPage }页</li>

										<li class="nextPage"><a	href="${pageContext.request.contextPath  }/showProductByPage?currentPage=${pageBean.currentPage==pageBean.totalPage?pageBean.totalPage:pageBean.currentPage+1}&category=${pageBean.category}">下一页&lt;&lt;</a>
										</li>
									

									</ul>
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
