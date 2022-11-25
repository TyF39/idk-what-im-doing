
import java.util.Scanner;

public class calcmain {

	public static void main(String[] args) {
		while (true) {
			Scanner sc = new Scanner(System.in); // receives the inputed equation
			String str = sc.nextLine();
			String[] eq = intoArr(str); //breaks the equation into parts of an array
			System.out.println(eval(eq)[0]); //evaluates the equation
		}
	}

	public static String[] intoArr(String str) {
		int count = 0; //used to check parts of the inputed string
		String load = ""; //used to transfer a part of the equation into its part of the array
		int par = 0; //used what "level" of parentheses its on to make sure stuff is transfered to the array correctly
		/*sets the length for the array. doesn't work when parentheses are open 
		 because each block of parentheses should be one element*/
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				par++;
			}
			if (str.charAt(i) == ')') {
				par--;
			}
			if (par == 0 && (str.charAt(i) == '/' || str.charAt(i) == '*' || str.charAt(i) == '-' || str.charAt(i) == '+')) { 
				count += 2;
			}
		}

		count++; //frees spot for the last element
		String[] eq = new String[count]; //creates the array
		count = 0; //now count can be used to check when to start and stop stuff being added to load to then be added as an element of the array
		for (int i = 0; i < eq.length; i += 2) {
			if (!(str.charAt(count) == '(')) {
				while (count < str.length() && !(str.charAt(count) == '/' || str.charAt(count) == '*'
						|| str.charAt(count) == '-' || str.charAt(count) == '+' || str.charAt(count) == '(')) {
					load += String.valueOf(str.charAt(count));
					count++;
				}
				eq[i] = load;
				if (!(count == str.length()))
					eq[i + 1] = String.valueOf(str.charAt(count));
				load = "";
				count++;
			}
			else {
				count++;
				par++;
				load+="(";
				while (!(par==0)) {
					if (str.charAt(count)=='(') {
						par++;
					}
					if (str.charAt(count)==')') {
						par--;
					}
					load += String.valueOf(str.charAt(count));
					count++;
				}
				eq[i] = load;
				if (!(count == str.length()) && (str.charAt(count)=='*' || str.charAt(count)=='/' || str.charAt(count)=='+' || str.charAt(count)=='-')) {
					eq[i + 1] = String.valueOf(str.charAt(count));
					count++;
				}
				load = "";
			}
		}
		return eq;
	}
	public static String[] replace3(String eq[], String val, int pos) {
		int x = 0;
		String[] ans = new String[eq.length - 2];
		for (int i = 0; i < eq.length; i++) {
			if (!(i == pos - 1 || i == pos)) {
				ans[x] = eq[i];
				x++;
			}
		}
		ans[pos - 1] = val;
		return ans;
	}
	public static String[] eval(String eq[]) { //all this method worked first try
		for (int i = 0; i < eq.length; i++) {
			if (eq[i].charAt(0) == '(') {
				eq[i] = eq[i].substring(1, eq[i].length() - 1);
				eq[i] = (eval(intoArr(eq[i]))[0]);
			}
		}
		if (!(stringArrayContains(eq, "+") || stringArrayContains(eq, "-") || stringArrayContains(eq, "*")
				|| stringArrayContains(eq, "/"))) {
			return eq;
		}
		for (int i = 0; i < eq.length; i++) {
			if (eq[i].equals("*")) {
				return eval(replace3(eq, String.valueOf(Double.valueOf(eq[i - 1]) * Double.valueOf(eq[i + 1])), i));
			}
			if (eq[i].equals("/")) {
				return eval(replace3(eq, String.valueOf(Double.valueOf(eq[i - 1]) / Double.valueOf(eq[i + 1])), i));
			}
		}
		for (int i = 0; i < eq.length; i++) {
			if (eq[i].equals("+")) {
				return eval(replace3(eq, String.valueOf(Double.valueOf(eq[i - 1]) + Double.valueOf(eq[i + 1])), i));
			}
			if (eq[i].equals("-")) {
				return eval(replace3(eq, String.valueOf(Double.valueOf(eq[i - 1]) - Double.valueOf(eq[i + 1])), i));
			}
		}
		return eq;
	}
	public static void printStringArr(String[] str) {
		System.out.print("[");
		for (String i : str) {
			System.out.print(i);
			System.out.print(",");
		}
		System.out.print("]");
	}

	public static boolean stringArrayContains(String[] strs, String key) {
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(key)) {
				return true;
			}
		}
		return false;
	}
	public static String calcs(String str) {
			String[] eq = intoArr(str); //breaks the equation into parts of an array
			return (eval(eq)[0]); //evaluates the equation
			
	}
}

