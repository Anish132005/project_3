<%@page import="in.co.rays.project_3.controller.ItemInformationCtl"%>
<%@page import="in.co.rays.project_3.controller.ProductDetailsCtl"%>
<%@page import="java.util.Map"%>

<%@page import="java.util.List"%>

<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/validateInput.js"></script>
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProductDetailsList view</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=ORSView.APP_CONTEXT%>/js/utility.js"></script>

<script type="text/javascript">
	function validateMobileNo(event) {
		const input = event.target;
		input.value = input.value.replace(/[^0-9.]/g, '')
		if (input.value.length > 0 && input.value[0] <= '5') {
			input.value = '';
		}
	}
</script>

<script type="text/javascript">
	function numberLength(input) {
		if (input.value.length > 10) {
			input.value = input.value.slice(0, 10);
		}
	}
</script>
<script>
	function validateNumberKey(event, validationMessageId) {
		// Allow only digits, minus sign, and dot for latitude and longitude input
		if (!/[0-9\.\-]/.test(event.key)) {
			// Show validation message
			var validationMsg = document.getElementById(validationMessageId);
			validationMsg.style.display = 'inline';
			event.preventDefault(); // Prevent typing the invalid character
		} else {
			// Hide validation message if input is valid
			var validationMsg = document.getElementById(validationMessageId);
			validationMsg.style.display = 'none';
		}
	}
</script>

<script type="text/javascript">
	function numberLength(input) {
		if (input.value.length > 10) {
			input.value = input.value.slice(0, 10);
		}
	}
</script>
<script>
        // Include the JavaScript function here or link to an external file
        function handleDoubleInput(inputElement, errorElementId, maxLength) {
            const errorMessage = document.getElementById(errorElementId);
            let currentValue = inputElement.value;

            // Clear existing error messages
            errorMessage.textContent = '';

            // Remove all invalid characters except digits and one decimal point
            currentValue = currentValue.replace(/[^0-9.]/g, '');
            
            // Check if there's more than one decimal point
            const decimalCount = (currentValue.match(/\./g) || []).length;
            if (decimalCount > 1) {
                errorMessage.textContent = 'Only one decimal point is allowed.';
                // Remove all extra decimal points
                const parts = currentValue.split('.');
                currentValue = parts[0] + '.' + parts.slice(1).join('').replace(/\./g, '');
            }

            // Split the value into integer and decimal parts
            const parts = currentValue.split('.');
            let integerPart = parts[0] || '';
            let decimalPart = parts[1] || '';

            // Handle leading zeros in the integer part
            if (integerPart.length > 0 && integerPart[0] === '0' && integerPart.length > 1) {
                integerPart = integerPart.replace(/^0+/, '');
            }

            // Ensure the integer part does not exceed the maxLength
            if (integerPart.length > maxLength) {
                errorMessage.textContent = Input exceeds the maximum length of ${maxLength} digits.;
                integerPart = integerPart.slice(0, maxLength);
                decimalPart = '';
            } else {
                // Ensure the total length of integer and decimal parts does not exceed maxLength
                const availableLengthForDecimal = maxLength - integerPart.length;
                if (decimalPart.length > availableLengthForDecimal) {
                    errorMessage.textContent = Input exceeds the maximum length of ${maxLength} digits.;
                    decimalPart = decimalPart.slice(0, availableLengthForDecimal);
                }
            }

            // Limit decimal places to two
            if (decimalPart.length > 2) {
                errorMessage.textContent = 'Only two decimal places are allowed.';
                decimalPart = decimalPart.slice(0, 2);
            }

            // Construct the final value
            inputElement.value = ${integerPart}${decimalPart ? '.' + decimalPart : ''};

            // Add a trailing decimal point if the input ends with one
            if (currentValue.endsWith('.')) {
                inputElement.value += '.';
            }
        }
    </script>
<style type="text/css">
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
	background-image: linear-gradient(to bottom right, purple, white);
	background-repeat: no repeat;
	background-size: 100%;
	padding-bottom: 11px;
}

.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/download (3).jpeg');
	background-size: cover;
	padding-top: 6%;
}
</style>

</head>
<body class="hm">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>

		<main>
		<form action="<%=ORSView.ITEMINFORMATION_CTL%>" method="post">
			<jsp:useBean id="dto"
				class="in.co.rays.project_3.dto.ItemInformationDto" scope="request"></jsp:useBean>
			<div class="row pt-3">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card input-group-addon">
						<div class="card-body">

							<%
								long id = DataUtility.getLong(request.getParameter("id"));

								if (dto.getId() != null && id > 0) {
							%>
							<h3 class="text-center default-text text-primary">Update
								ItemInformationList</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text text-primary">Add
								ItemInformationList</h3>
							<%
								}
							%>
							<!--Body-->
							<div>

								<%
									Map map = (Map) request.getAttribute("Cate");
									System.out.println("map = " + map);
								%>



								<H4 align="center">
									<%
										if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
										}
									%>
								</H4>

								<H4 align="center">
									<%
										if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
										}
									%>

								</H4>

								<input type="hidden" name="id" value="<%=dto.getId()%>">

							</div>

							<div class="md-form">

								<span class="pl-sm-5"><b> title</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" name="title" placeholder="Enter title"
											class="form-control"
											oninput="handleLetterInput(this, 'nameError', 25)"
											onblur="validateLetterInput(this, 'nameError', 25)" rows="5"
											cols="5"
											value="<%=DataUtility.getStringData(dto.getTitle())%>">

									</div>
								</div>

								<font color="red" class="pl-sm-5" id="nameError"> <%=ServletUtility.getErrorMessage("title", request)%></font></br>



								<span class="pl-sm-5"><b>overView</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<textarea name="overView" placeholder="Enter overView"
											class="form-control"
											oninput="handleLetterInput(this, 'descriptionError', 200)"
											onblur="validateLetterInput(this, 'descriptionError', 200)"
											onkeypress="return validateInput(event)" rows="5" cols="5"><%=DataUtility.getStringData(dto.getOverView())%></textarea>

									</div>
								</div>

								<font color="red" class="pl-sm-5" id="descriptionError">

									<%=ServletUtility.getErrorMessage("overView", request)%></font></br> <span
									class="pl-sm-5"><b>purchaseDate</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" id="datepicker" name="purchaseDate"
											class="form-control" placeholder="Enter purchaseDate"
											readonly="readonly"
											value="<%=DataUtility.getDateString(dto.getPurchaseDate())%>">


									</div>

								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("purchaseDate", request)%></font></br>




								<span class="pl-sm-5"><b>cost</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-list grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="cost"
											placeholder=" Enter cost"
											oninput="handleIntegerInput(this, 'priceError', 7)"
											onblur="validateIntegerInput(this, 'priceError', 7)"
											value="<%=DataUtility.getStringData(dto.getCost()).equals("0") ? ""
					: DataUtility.getStringData(dto.getCost())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5" id="priceError"> <%=ServletUtility.getErrorMessage("cost", request)%></font></br>


								<span class="pl-sm-5"><b>category</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user-circle grey-text"
													style="font-size: 1rem;"></i>
											</div>
										</div>


										<%=HTMLUtility.getList1("category", String.valueOf(dto.getCategory()), map)%>

									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("Product", request)%></font></br>



								<%-- 
								<%
								if (dto.getId()==null||id<=0) {
								%> --%>





								<%
									if (dto.getId() != null && id > 0) {
								%>

								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=ItemInformationCtl.OP_UPDATE%>"> <input
										type="submit" name="operation" class="btn btn-warning btn-md"
										style="font-size: 17px"
										value="<%=ItemInformationCtl.OP_CANCEL%>">

								</div>
								<%
									} else {
								%>
								<div class="text-center">

									<input type="submit" name="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=ItemInformationCtl.OP_SAVE%>"> <input
										type="submit" name="operation" class="btn btn-warning btn-md"
										style="font-size: 17px"
										value="<%=ItemInformationCtl.OP_RESET%>">
								</div>

							</div>
							<%
								}
							%>
						</div>
					</div>
		</form>
		</main>
		<div class="col-md-4 mb-4"></div>

	</div>

</body>
<%@include file="FooterView.jsp"%>

</body>
</html>