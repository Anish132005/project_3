package in.co.rays.project_3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * ModelFactory decides which model implementation run (ModelFactory decide
 * karta hai kaunsa model implementation run kare.)
 * 
 * ModelFactory is factory of all model when database is jdbc it return jdbc
 * instance,if database hibernate it return hibernate instance.
 * 
 * (ModelFactory sab models ka factory hai. Jab database JDBC hota hai, toh yeh
 * JDBC instance return karta hai, aur agar Hibernate hota hai, toh yeh
 * Hibernate instance return karta hai.)
 * 
 * 
 * @author Anish Malviya
 *
 */

public final class ModelFactory {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");
	private static final String DATABASE = rb.getString("DATABASE");
	private static ModelFactory mFactory = null;
	private static HashMap modelCache = new HashMap();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}

	public MarksheetModelInt getMarksheetModel() {
		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");
		if (marksheetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				marksheetModel = new MarksheetModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				marksheetModel = new MarksheetModelJDBCImpl();
			}
			modelCache.put("marksheetModel", marksheetModel);
		}
		return marksheetModel;
	}

	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
		if (collegeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				collegeModel = new CollegeModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				collegeModel = new CollegeModelJDBCImpl();
			}
			modelCache.put("collegeModel", collegeModel);
		}
		return collegeModel;
	}

	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModelJDBCImpl();
			}
			modelCache.put("roleModel", roleModel);
		}
		return roleModel;
	}

	public UserModelInt getUserModel() {

		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				userModel = new UserModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				userModel = new UserModelJDBCImpl();
			}
			modelCache.put("userModel", userModel);
		}

		return userModel;
	}

	public ProductDetailsModelInt getProductDetailsModel() {

		ProductDetailsModelInt ProductDetailsModel = (ProductDetailsModelInt) modelCache.get("ProductDetailsModel");
		if (ProductDetailsModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				ProductDetailsModel = new ProductDetailsModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("ProductDetailsModel", ProductDetailsModel);
		}

		return ProductDetailsModel;
	}

	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel = (StudentModelInt) modelCache.get("studentModel");
		if (studentModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				studentModel = new StudentModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				studentModel = new StudentModelJDBCImpl();
			}
			modelCache.put("studentModel", studentModel);
		}

		return studentModel;
	}

	public CourseModelInt getCourseModel() {
		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");
		if (courseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				courseModel = new CourseModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				courseModel = new CourseModelJDBCImpl();
			}
			modelCache.put("courseModel", courseModel);
		}

		return courseModel;
	}

	public TimetableModelInt getTimetableModel() {

		TimetableModelInt timetableModel = (TimetableModelInt) modelCache.get("timetableModel");

		if (timetableModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				timetableModel = new TimetableModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				timetableModel = new TimetableModelJDBCImpl();
			}
			modelCache.put("timetableModel", timetableModel);
		}

		return timetableModel;
	}

	public SubjectModelInt getSubjectModel() {
		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");
		if (subjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				subjectModel = new SubjectModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				subjectModel = new SubjectModelJDBCImpl();
			}
			modelCache.put("subjectModel", subjectModel);
		}

		return subjectModel;
	}

	public FacultyModelInt getFacultyModel() {
		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");
		if (facultyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				facultyModel = new FacultyModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				facultyModel = new FacultyModelJDBCImpl();
			}
			modelCache.put("facultyModel", facultyModel);
		}

		return facultyModel;
	}

	public ClientModelInt getclientModel() {
		ClientModelInt clientModel = (ClientModelInt) modelCache.get("clientModel");
		if (clientModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				clientModel = new ClientModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { clientModel = new ClientModelJDBCImpl();
			 */
		}
		modelCache.put("clientModel", clientModel);

		return clientModel;

	}

	public ProductModelInt getProductModel() {
		ProductModelInt productModel = (ProductModelInt) modelCache.get("productModel");
		if (productModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				productModel = new ProductModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				productModel = new ProductModelHibImp();
			}
			modelCache.put("productModel", productModel);
		}
		return productModel;
	}

	

	public PurchaseOrderModelInt getPurchaseOrderModel() {

		PurchaseOrderModelInt PurchaseOrderModel = (PurchaseOrderModelInt) modelCache.get("PurchaseOrderModel");
		if (PurchaseOrderModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				PurchaseOrderModel = new PurchaseOrderModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("PurchaseOrderModel", PurchaseOrderModel);
		}

		return PurchaseOrderModel;
	}

	public CartOverViewModelInt getCartOverViewModel() {

		CartOverViewModelInt cartOverViewModel = (CartOverViewModelInt) modelCache.get("cartOverViewModel");
		if (cartOverViewModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				cartOverViewModel = new CartOverViewModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("cartOverViewModel", cartOverViewModel);
		}

		return cartOverViewModel;
	}

	public ItemInformationModelInt getItemInformationModel() {

		ItemInformationModelInt itemInformationModel = (ItemInformationModelInt) modelCache.get("itemInformationModel");
		if (itemInformationModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				itemInformationModel = new ItemInformationModelHib();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("itemInformationModel", itemInformationModel);
		}

		return itemInformationModel;
	}

	public FavoriteListModelInt getFavoriteModel() {

		FavoriteListModelInt favoriteModel = (FavoriteListModelInt) modelCache.get("favoriteModel");
		if (favoriteModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				favoriteModel = new FavroiteListModelHib();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("favoriteModel", favoriteModel);
		}

		return favoriteModel;
	}

	public CustomerModelInt getCustomerModel() {

		CustomerModelInt customerModel = (CustomerModelInt) modelCache.get("customerModel");
		if (customerModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				customerModel = new CustomerModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("customerModel", customerModel);
		}

		return customerModel;
	}

}