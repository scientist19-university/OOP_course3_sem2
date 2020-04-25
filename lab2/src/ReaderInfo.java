public class ReaderInfo {

	private int Id;
	private String FirstName;
	private String LastName;
	
	public ReaderInfo(int id, String firstName, String lastName) {
		
		Id = id;
		FirstName = firstName;
		LastName = lastName;
	}
	
	public String getReaderInfo() {
		
		 return "Reader #" + Id + ": " + FirstName + " " + LastName;
	}
	
	public static String toReaderInfo(int id, String firstName, String lastName) {
		
		return "Reader #" + id + ": " + firstName + " " + lastName;
	}
}
