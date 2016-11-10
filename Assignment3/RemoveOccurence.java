import acm.program.ConsoleProgram;

public class RemoveOccurence extends ConsoleProgram {
	public void run() {
		
		String str = readLine("Enter a some text: ");
		String string2Remove = readLine("Enter character you want to remove: ");
		char ch = string2Remove.charAt(0);
		println(removeAllOccurrences(str, ch));
		
	}
		
	private String removeAllOccurrences(String str, char ch){
		
		while(str.indexOf(ch) != -1){
			str = removeFirstOccurrence(str, ch);
		}
		return str;
	}
	
	private String removeFirstOccurrence(String str, char orig) {
		
		int index = str.indexOf(orig); 
		if (index != -1) { 
			str = str.substring(0, index) + str.substring(index + 1); 
		} 
		return str; 
	}
		
}