package calculator;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * main class used
 * @author shahdsamour 
 *
 */

public class Calculator {
	
	/**
	 * This method checks if an index is out of bounds from a String or list
	 * @param index the index to check
	 * @param length the length of string or list
	 * @return true if character is out of bounds. false otherwise
	 */
	public boolean outOfBounds(int index, int length) {
		if(index < 0 || index >= length) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * This method checks if the character is a integer by looking at its ASCII code
	 * @param input the string input provided
	 * @param index the index of the character in the string
	 * @return true if a character is an integer. false otherwise
	 */
	
	public boolean checkCharNum(String input, int index) {
		if(input.charAt(index) >= 48 && input.charAt(index) <= 57) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if a character is a decimal point
	 * @param input the string input provided
	 * @param index the index of the character in the string
	 * @return true if a character is a decimal point. false otherwise
	 */
	
	public boolean checkCharDec(String input, int index) {
		
		if(outOfBounds(index, input.length())) {
			return false;
		}
		
		if(input.charAt(index) == '.') {
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if character is one of the five mathematical symbols used in this program, [+, -, *, /, ^]
	 * @param input the string input provided
	 * @param index the index of the character in the string
	 * @return true if a character is one of the five mathematical symbols listed. false otherwise
	 */
	
	public boolean checkCharSymb(String input, int index) {
		if(outOfBounds(index,input.length())) {
			return false;
		}
		if(input.charAt(index) == '+'|| input.charAt(index) == '/' || input.charAt(index) == '-' || input.charAt(index) == '*' || input.charAt(index) == '^') {
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if a character is a left or right parentheses
	 * @param input the string input provided
	 * @param index the index of the character in the string
	 * @return true if a character is a parentheses or not. false otherwise
	 */
	
	public boolean checkCharBracket(String input, int index) {
		if(outOfBounds(index,input.length())) {
			return false;
		}
		if(input.charAt(index) == '('|| input.charAt(index) == ')') {
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if a number is continued throughout the string (eq. has more than one digit or is a decimal)
	 * @param input the string input provided
	 * @param index the index of the character - usually the index after a number has been discovered
	 * @return true if the number continues throughout the string, false otherwise
	 */
	
	public boolean checkIfNumCont(String input, int index) {
		if(outOfBounds(index, input.length())) {
			return false;
		}
		if(checkCharNum(input, index) || checkCharDec(input, index)){
			return true;
		}
		return false;
	}
	
	/**
	 * This method finds the root of a number using the Newton method.
	 * @param root the root to which we apply
	 * @param base the base value 
	 * @param value our approximation for every recursion, initial value is equal to 1
	 * @return the approximated value once the margin of error between our new estimate and our old estimate falls below 0.00001
	 */
	
	public double newtonRoot(int root, double base, double value) {
		if(root < 0) {
			root*=-1;
			return 1/newtonRoot(root,base,value);
		}
		double pow = multiplySelf(value,root-1);
		double newValue = ( ((root-1)*value) + (base/pow));
		newValue*=(1.0/root);
		double diff = newValue-value;
		if(diff < 0) {
			diff*=-1;
		}
		if(diff <= 0.00001) {
			return newValue;
		}
		return newtonRoot(root,base,newValue);
	}
	
	/**
	 * This method rounds a number to a specificed decimal place
	 * @param num the number that needs to be rounded
	 * @param scale the decimal point to round the number to
	 * @return the rounded number
	 */
	
	public double roundNum(double num, int scale) {
		BigDecimal roundRoot =  BigDecimal.valueOf(num);
		roundRoot = roundRoot.setScale(scale, RoundingMode.HALF_EVEN);
		num = roundRoot.doubleValue();
		return num;
	}
	
	/**
	 * This method turns a decimal into an integer fraction by multiplying the number with ten until its whole
	 * @param frac an array usually of size 2 in which the first value is the numerator (the decimal) and the second value is the denominator (initially 1)
	 * @return the frac array multiplied by 10 as many times as was needed
	 */
	
	public double[] fraction(double[] frac) {
		if(frac[0] % 1!=0) {
			frac[0]*=10;
			frac[1]*=10;
			return fraction(frac);
		}
		return frac;
	}
	
	/**
	 * This method multiplies the base with itself as many times as the exponent specifies. It uses an efficient algorithm to remove any deep recursion. 
	 * @param num the number than needs to be multiplied by itself
	 * @param exponent an integer which tells the method how many times the base needs to be multiplied
	 * @return the result of the base multiplied by itself x times (where x = exponent)
	 */
	
	public double multiplySelf(double num, int exponent) {
		if(exponent == 0) {
			return 1;
		}
		
		if(exponent < 0) {
			exponent*=(-1);
			return 1/multiplySelf(num, exponent);
		}
		else{
			double half = multiplySelf(num, exponent/2);
			double result = half*half;
			if(exponent % 2 != 0) {
				result*=num;
			}
			return result;
		}
	}
	
	/**
	 * This is a power function that finds fractional exponents by finding the root (root = denominator) of a base, and then raising that result to a integer exponent (exponent = numerator);
	 * @param exponent The exponent provided that can be fractional or whole
	 * @param base the base number
	 * @return the result of the base number raised to the power of the exponent in the argument
	 */
	
	public double power(double exponent, double base) {
		exponent = roundNum(exponent, 3);
		double[] frac = {exponent, 1};
		if(exponent % 1 != 0) {
			frac[0] = exponent;
			frac = fraction(frac);
		}
		double result = newtonRoot((int) frac[1], base, 1);
		result = multiplySelf(result, (int)frac[0]);
		return result;
	}
	
	/**
	 * This method sorts numbers in a string that by adding them on a string and then parsing that string into a number. The method recurses if the number has more than one digit
	 * @param numbers the ArrayList of all the numbers in the string
	 * @param index the index of the character currently under evaluation
	 * @param num the string value of the number before it is parsed
	 * @param input the input string analysed
	 * @return the index of the last digit of the number
	 */
	
	public int sortNum(ArrayList<Double> numbers, int index, String num, String input) {
		if(checkIfNumCont(input, index)) {
			num =  num + input.charAt(index);
			return sortNum(numbers,index+1,num, input);
		}
		double number = Double.parseDouble(num);
		numbers.add(number);
		return index-1;
	}
	
	/**
	 * This method checks the priority of between two operations
	 * @param operOne the operator that occurs before 
	 * @param operTwo the operator that occurs after
	 * @return true if second operator should overtake the first one. false if not
	 */
	
	public boolean checkPriority(char operOne, char operTwo) {
		
		if(operTwo == '^') {
			return true;
		}
		
		if((operOne == '+' || operOne == '-')  && (operTwo == '*' || operTwo == '/')) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method removes whitespace from a string
	 * @param input the string input provided
	 * @return a clean string with no white space
	 */
	
	public String removeWhiteSpace(String input) {
		input = input.replaceAll(" ", "");
		input = input.replaceAll("	", "");
		return input;
	}
	
	/**
	 * This method checks if a number is negative by checking if there is a minus character  that occurs first in a string, or after an operator
	 * @param index the index of the character
	 * @param input the string input provided
	 * @return true if minus character occurs after an operator or is first in a string. false otherwise
	 */
	
	public boolean checkNegativeNumber(int index, String input){
		if(input.charAt(index) == '-') {
			int prevIndex = index-1;
			if(checkCharSymb(input, prevIndex) || index == 0) {
				return true;
				
			}
		}
		return false;
	}
	
	/**
	 * This method checks if the last operator added needs to occur before other operators. It recurses through all previous operators until a position is found
	 * @param symbols the current list of operators found
	 * @param order an ordered list that contains lists when an operator should occur by storing its index
	 * @param index an index value to compare one operator in the ordered list with the new operator
	 */
	
	public void sortOperations(ArrayList<Character> symbols, ArrayList<Integer> order, int index) {
		int lastIndex = symbols.size() - 1;
		if(!outOfBounds(index,symbols.size())) {
			int currentPrior = order.get(index);
			if(checkPriority(symbols.get(currentPrior), symbols.get(lastIndex))){
				order.add(index,lastIndex);
				order.remove(lastIndex+1);
			
			}else {
				index++;
				sortOperations(symbols,order,index);
			}
		}
	}
	
	/**
	 * This method reads the brackets in a string. It finds the index of the last occurring right parentheses and checks the index before it to see if it should use the index of the earliest occurring right parentheses
	 * @param input The string input provided
	 * @param index the index of the left parentheses
	 * @return a substring of input which spans from the left to right parentheses
	 */
	
	public String readBrackets(String input, int index) {
		if(input.charAt(index) == '(') {
			int lastIndex = input.lastIndexOf(")");
			int prevIndex = lastIndex-1;
			if(input.charAt(prevIndex) != ')'){
				lastIndex = input.indexOf(")");
			}
			String newStr = input.substring(index+1, lastIndex);
			return newStr;
		}
		return input;
	}
	
	
	/**
	 * This method sorts a string inputed, by creating a list of the numbers, operators, and the order of operators to follow
	 * @param input the string input provided
	 * @param symbols a list that contains the operators with each new operator found being added at the end
	 * @param numbers  a list that contains the numbers with each new number found being added at the end
	 * @param order a list that contains the order of the operators by storing indices
	 * @param index the current index the method is on in regard to the string argument "input"
	 */
	
	public void sortString(String input, ArrayList<Character> symbols, ArrayList<Double> numbers, ArrayList<Integer> order, int index) {
		if(!outOfBounds(index,input.length())) {
			if(checkCharNum(input, index)) {
				index = sortNum(numbers, index+1, ""+input.charAt(index), input);
				if(symbols.size() > 1) {
					sortOperations(symbols,order,0); 
				}
				
			}else if(checkCharSymb(input,index)) {
				if(checkNegativeNumber(index, input)) {
					index = sortNum(numbers, index+1, ""+input.charAt(index), input);
				}else {
					symbols.add(input.charAt(index));
					order.add(symbols.size()-1);
				}
				
			}else if(input.charAt(index) == '(') {
				String newStr = readBrackets(input,index);
				double result = evaluate(newStr);
				numbers.add(result);
				if(numbers.size() > symbols.size()) {
					sortOperations(symbols,order,0);
				}
				newStr = "(" + newStr + ")";
				if(checkCharSymb(input, index-1)) {
					newStr = input.charAt(index-1) + newStr;
					index--;
				}
				input = input.replace(newStr, "");
				index--;
			}
			index++;
			sortString(input, symbols, numbers, order, index);
		}
	}
	
	/**
	 * This method evaluates which arithmetic operation needs to be carried out between two numbers
	 * @param numOne the first number to use
	 * @param numTwo the second number to use
	 * @param operation the operator
	 * @return the result of the two numbers when an operation is performed
	 */
	
	public double evaluate(double numOne, double numTwo, char operation) {
		double result = 0;
		if(operation == '+') {
			result =  numOne + numTwo;
		}else if(operation == '-') {
			result = numOne - numTwo;
		}else if(operation == '*') {
			result = numOne*numTwo;
		}else if(operation == '/') {
			result = numOne/numTwo;
		}else if(operation == '^') {
			result = power(numTwo,numOne);
		}
		return result;
	}
	
	
	/**
	 * This method fixes indices in the order list, by decrementing the value of indices when the last index used is a smaller index compared to the others stored
	 * @param order the order list that contains operator indices
	 * @param index the index of the list order that is currently being analysed
	 * @param value the value of the index of the last operator used
	 */
	
	public void fixOrder(ArrayList<Integer> order, int index, int value) {
		if(!outOfBounds(index, order.size())) {
			int num = order.get(index);
			if(num > value) {
				order.set(index, num-1);
			}
			fixOrder(order,index+1, value);
		}
	}
	
	/**
	 * This method evaluates the all numbers and operations by following the order list
	 * @param numbers the list of numbers found 
	 * @param symbols the list of operators found
	 * @param order the list that shows the order of the operators
	 * @return the result of all operations
	 */
	
	public double evaluate(ArrayList<Double> numbers, ArrayList<Character> symbols, ArrayList<Integer> order) {
		if(numbers.size() == 0) {
			return 0;
		}
		if(numbers.size() == 1) {
			return numbers.get(0);
		}
		int index = order.get(0);
		double numOne = numbers.get(index);
		double numTwo = numbers.get(index+1);
		char operation = symbols.get(index);
		double result = evaluate(numOne, numTwo, operation);
		if(order.size() > 1) {
			numbers.set(index+1, result);
			numbers.remove(index);
			symbols.remove(index);
			fixOrder(order,1,index);
			order.remove(0);
			return evaluate(numbers,symbols,order);
		}else {
			return result;
		}
	}
	
	/**
	 * the main overloaded evaluate method from the ICalculator interface. Takes in a string, removes all white spaces, then sorts out all numbers and operations and evaluates them all
	 * @param input the string input provided
	 * @return the result of the string
	 */
	
	public double evaluate(String input) {
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		input = removeWhiteSpace(input);
		sortString(input, symbols, numbers, order, 0);
		return evaluate(numbers, symbols, order);
	}

}
