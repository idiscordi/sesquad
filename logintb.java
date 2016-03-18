
public class logintb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login test = new Login("user","pass");
		System.out.println(test.trylogin());
		System.out.println(test.newaccount("electronic mail"));
		
		Login retest = new Login("1234567","7358934");
		System.out.println(retest.trylogin());
		System.out.println(retest.newaccount("herp"));
	}

}
