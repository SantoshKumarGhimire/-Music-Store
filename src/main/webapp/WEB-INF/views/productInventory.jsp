<%--
  Created by IntelliJ IDEA.
  User: santosh
  Date: 9/13/17
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/views/templates/header.jsp" %>


<div class="container-wrapper">

    <div class="container">

        <div class="page-header">
            <h1>Product Inventory</h1>

            <p class="lead">view and modify product inventory !</p>

            <table class="table table-striped table-hover" id="product-table">
                <thead>
                <tr class="bg-success">
                    <th>Photo thumb</th>
                    <th>Product Name</th>
                    <th>Category</th>
                    <th>Condition</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr id="table-content">

                        <td> <img src="<c:url value="/resources/images/${product.productId}.png"/> " alt="Image" style="width: 100%"/>
                        </td>
                        <td>${product.productName}</td>
                        <td>${product.productCategory}</td>
                        <td>${product.productCondition}</td>
                        <td>${product.productPrice} USD</td>
                        <td><a href="<spring:url value="/product/viewProduct/${product.productId}"/> "> <span
                                class="glyphicon glyphicon-info-sign"></span></a>
                            <a href="<spring:url value="/admin/product/deleteProduct/${product.productId}"/> "> <span
                                    class="glyphicon glyphicon-remove"></span></a>
                            <a href="<spring:url value="/admin/product/editProduct/${product.productId}"/> "> <span
                                    class="glyphicon glyphicon-pencil"></span></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
            <a href="<spring:url value="/admin/product/addProduct"/> " class="btn btn-primary" >Add Product  </a>
        </div>

<%@include file="/WEB-INF/views/templates/footer.jsp" %>