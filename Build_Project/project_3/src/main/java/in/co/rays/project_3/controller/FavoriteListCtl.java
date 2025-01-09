package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.FavoriteListDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.FavoriteListModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "FavoriteListCtl", urlPatterns = {"/ctl/FavoriteListCtl"})
public class FavoriteListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();
		map.put(1, "Computer");
		map.put(2, "Mouse");
		map.put(3, "Keyboard");
		map.put(4, "Monitor");

		request.setAttribute("pro", map);

	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "product"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("addedDate"))) {
			request.setAttribute("addedDate", PropertyReader.getValue("error.require", " addedDate"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("customerUserName"))) {
			request.setAttribute("customerUserName", PropertyReader.getValue("error.require", " customerUserName"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("notesOrComments"))) {
			request.setAttribute("notesOrComments", PropertyReader.getValue("error.require", " notesOrComments"));

			pass = false;
		}

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		FavoriteListDto dto = new FavoriteListDto();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setProduct(DataUtility.getString(request.getParameter("product")));
		dto.setAddedDate(DataUtility.getDate(request.getParameter("addedDate")));
		dto.setCustomerUserName(DataUtility.getString(request.getParameter("customerUserName")));
		dto.setNotesOrComments(DataUtility.getString(request.getParameter("notesOrComments")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		FavoriteListModelInt model = ModelFactory.getInstance().getFavoriteModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			FavoriteListDto dto;
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
		FavoriteListModelInt model = ModelFactory.getInstance().getFavoriteModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			FavoriteListDto dto = (FavoriteListDto) populateDTO(request);
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

			FavoriteListDto dto = (FavoriteListDto) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.FAVORITELIST_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FAVORITELIST_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FAVORITELIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FAVORITELIST_VIEW;
	}

}
