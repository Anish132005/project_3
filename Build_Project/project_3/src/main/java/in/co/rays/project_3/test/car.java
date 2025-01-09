package in.co.rays.project_3.test;

public class car {
	String Brand;
	int year;
	
	car(String Brand, int year ){
		this.Brand = Brand;
		this.year = year;	
		
	}
	void display() {
		System.out.println("Brand:" + Brand + " year: " +  year);
	}
	public static void main(String[] args) {
		 car myCar = new car("Toyota", 2022);
		 myCar.display();
	}

}
 