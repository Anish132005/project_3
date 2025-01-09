package in.co.rays.project_3.dto;

import java.util.Date;

public class FavoriteListDto extends BaseDTO {

	private String product;
	private Date addedDate;
	private String customerUserName;
	private String notesOrComments;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public String getNotesOrComments() {
		return notesOrComments;
	}

	public void setNotesOrComments(String notesOrComments) {
		this.notesOrComments = notesOrComments;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
