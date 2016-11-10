import acm.program.ConsoleProgram;

public class CommaSeparatedDigits extends ConsoleProgram {
	public void run() {
		while (true) {
		String digits = readLine("Enter a numeric string: ");
		if (digits.length() == 0) break;
		println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String digits){
		int counter = 0;
		String result = "";
		if(isNumeric(digits)){
			for(int i = digits.length() - 1; i >= 0; i--){
				counter++;
				if(counter % 3 == 0 && counter != digits.length()){
					result = "," + digits.charAt(i) + result;
				}else{
					result = digits.charAt(i) + result;
				}
			}
		}else {
			result = "Enter a numeric string on the Screen.";
		}
		
		return result;
		
	}
	
	private boolean isNumeric(String digits){
		boolean test = true;
		for(int i = 0; i < digits.length(); i++){
			if(!Character.isDigit(digits.charAt(i))){ 
				test = false;
				break;
			}
		}
		
		return test;
	}
	
}