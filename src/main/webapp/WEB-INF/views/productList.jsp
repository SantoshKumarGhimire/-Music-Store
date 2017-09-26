<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/views/templates/header.jsp" %>


<div class="container-wrapper">

    <div class="container">

        <div class="page-header">
            <h1>All Products</h1>

            <p class="lead">checkout all the avaliable products now !</p>

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
                                class="glyphicon glyphicon-info-sign"></span></a></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>

<%@include file="/WEB-INF/views/templates/footer.jsp" %>