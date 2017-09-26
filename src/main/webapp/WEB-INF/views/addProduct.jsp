<%--
  Created by IntelliJ IDEA.
  User: santosh
  Date: 9/13/17
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/templates/header.jsp" %>


<div class="container-wrapper">

    <div class="container">

        <div class="page-header">
            <h1>Add Product</h1>

            <p class="lead">You can add the detail information of the product here !</p>


        </div>
        <form:form action="${pageContext.request.contextPath}/admin/product/addProduct"
                   method="post" commandName="product" enctype="multipart/form-data">
        <div class="form-group">

            <label for="name">Name</label><form:errors path="productName" cssStyle="color: red"/>
            <form:input path="productName" id="name" class="form-control"/>

        </div>

        <div class="form-group">

            <label for="category">Category</label>

            <label class="checkbox-inline">

                <form:radiobutton path="productCategory" id="category" value="instrument"/> Instrument
                <form:radiobutton path="productCategory" id="category" value="record"/> Record
                <form:radiobutton path="productCategory" id="category" value="accessory"/> Accessory

            </label>

        </div>

        <div class="form-group">

            <label for="description">Description</label>
            <form:textarea path="productDescription" id="description" class="form-control"/>

        </div>

        <div class="form-group">

            <label for="price">Price</label><form:errors path="productPrice" cssStyle="color: red"/>
            <form:input path="productPrice" id="price" class="form-control"/>

        </div>

        <div class="form-group">

            <label for="condition">Condition</label>
            <label class="checkbox-inline">

                <form:radiobutton path="productCondition" id="condition" value="new"/> New
                <form:radiobutton path="productCondition" id="condition" value="used"/> Used

            </label>

        </div>

        <div class="form-group">

            <label for="status">Status</label>
            <label class="checkbox-inline">

                <form:radiobutton path="productStatus" id="status" value="active"/> Active
                <form:radiobutton path="productStatus" id="status" value="inactive"/> Inactive

            </label>

        </div>

        <div class="form-group">

            <label for="unitinstock">Unit in Stok</label><form:errors path="unitInStock" cssStyle="color: red"/>
            <form:input path="unitInStock" id="unitinstock" class="form-control"/>

        </div>

        <div class="form-group">

            <label for="manufacturer">Manufacturer</label>
            <form:input path="productManifacturer" id="manufacturer" class="form-control"/>

        </div>

        <div class="form-group">

            <label  class="control-label" for="productImage">Upload Image</label>
            <form:input id="productImage" path="productImage" type="file" class="form:input-large"/>

        </div>
        <br><br>
        <input type="submit" value="submit" class="btn btn-default">

        <a href="<c:url value="/admin/productInventory"/> " class="btn btn-default">Cancel</a>

        </form:form>

<%@include file="/WEB-INF/views/templates/footer.jsp" %>