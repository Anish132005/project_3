package in.co.rays.project_3.test;

public class UserStatus {
	
	public static final String ACTIVE = "Active";
	public static final String INACTIVE = "Inactive";

	public static void main(String[] args) {
		String currentUserStatus = INACTIVE;

		if (currentUserStatus.equals(ACTIVE)) {
			System.out.println("User is active.");
		} else {
			System.out.println("User is inactive.");
		}
	}
}
