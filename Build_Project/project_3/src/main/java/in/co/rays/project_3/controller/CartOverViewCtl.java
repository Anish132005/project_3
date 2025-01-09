package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.CartOverviewDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.CartOverViewModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "CartOverViewCtl", urlPatterns = { "/ctl/CartOverViewCtl" })
public class CartOverViewCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();
		map.put(1, "mobile");
		map.put(2, "marker");
		map.put(3, "bag");
		map.put(4, "Shose");

		request.setAttribute("Prd", map);

	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("CustomerName"))) {
			request.setAttribute("CustomerName", PropertyReader.getValue("error.require", "CustomerName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Product"))) {
			request.setAttribute("Product", PropertyReader.getValue("error.require", " Product"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("TransationDate"))) {
			request.setAttribute("TransationDate", PropertyReader.getValue("error.require", " TransationDate"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("QuantityOrdered"))) {
			request.setAttribute("QuantityOrdered", PropertyReader.getValue("error.require", "QuantityOrdered"));
			pass = false;
		}

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		CartOverviewDto dto = new CartOverviewDto();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setCustomerName(DataUtility.getString(request.getParameter("CustomerName")));
		dto.setProduct(DataUtility.getString(request.getParameter("Product")));
		dto.setTransationDate(DataUtility.getDate(request.getParameter("TransationDate")));
		dto.setQuantityOrdered(DataUtility.getInt1(request.getParameter("QuantityOrdered")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		CartOverViewModelInt model = ModelFactory.getInstance().getCartOverViewModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			CartOverviewDto dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		CartOverViewModelInt model = ModelFactory.getInstance().getCartOverViewModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			CartOverviewDto dto = (CartOverviewDto) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			CartOverviewDto dto = (CartOverviewDto) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.CARTOVERVIEW_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CARTOVERVIEW_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CARTOVERVIEW_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CARTOVERVIEW_VIEW;
	}

}
