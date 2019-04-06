<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>All Products</h2>
		<p>착한 가격으로 상품을 살펴보세요!</p>
		<table class="table table-striped" >
			<thead>
				<tr class="bg-success">
					<th>Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Manufacturer</th>
					<th>UnitInStock</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<tr>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.price}</td>
						<td>${product.manufacturer}</td>
						<td>${product.unitInStock}</td>
						<td>${product.description}</td>
						<td><a href="<c:url value="/viewProduct/${product.id}" />">
								<i class="fa fa-info-circle"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div style="margin-left: 10%; margin-right: 10%; margin-top: 50px">
			<h2>Product Detail</h2>
			<p>Here is the detail information of the product!</p>
			<div class="row">
				<div class="col-12 col-md-8">
					<c:set var="imageFilename"
						value="/resources/images/${product.name}.jpg" />
					<img src="<c:url value="${imageFilename}" />" alt="image"
						style="width: 80%" />
				</div>
				<div class="col-6 col-md-4" >
					<h3>${product.name}</h3>
					<p>${product.description}</p>
					<p>
						<b>Manufacturer</b> : ${product.manufacturer}
					</p>
					<p>
						<b>Category</b> : ${product.category}
					</p>
					<h3>${product.price}원</h3>
				</div>
			</div>
		</div>
	</div>
</div>



