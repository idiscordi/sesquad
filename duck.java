
public class duck {
	public String name;
	public int rating;
	public int wait;
	
	public duck(String n, String r){
		name = n;
		wait = 0;
		try{
			rating = Integer.parseInt(r);
		} catch(Exception e){
			System.out.println(e.toString());
			rating = -1;
		}
	}
}
