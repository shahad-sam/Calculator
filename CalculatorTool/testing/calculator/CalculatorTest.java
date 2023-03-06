package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class CalculatorTest {
	Calculator cal;
	String input;
	
	@BeforeEach
	void setUp(){
	    input = new String("1 + 2.05-5*2/2^3");
		cal = new Calculator();
	}
	
	//check simple methods
	
	//index outOfBounds
	@Test
	void check_out_of_bounds_negative_index() {	
		assertTrue(cal.outOfBounds(-1, input.length()));
	}
	
	@Test
	void check_out_of_bounds_large_index() {	
		assertTrue(cal.outOfBounds(22, input.length()));
	}
	
	@Test
	void check_out_of_bounds_false() {	
		assertFalse(cal.outOfBounds(5, input.length()));
	}
	
	
	
	//checkCharNum
	@Test
	void check_if_num_found() {	
		assertTrue(cal.checkCharNum(input, 0));
	}
	
	@Test
	void check_if_num_not_found() {	
		assertFalse(cal.checkCharNum(input, 2));
	}
	
	//checkCharDec
	
	@Test
	void check_if_dec_found() {	
		assertTrue(cal.checkCharDec(input, 5));
	}
	
	@Test
	void check_if_dec_not_found() {	
		assertFalse(cal.checkCharDec(input, 1));
	}
	
	//checkCharSymb
	
	@Test
	void check_if_plus_found() {	
		assertTrue(cal.checkCharSymb(input, 2));
	}
	
	@Test
	void check_if_minus_found() {	
		assertTrue(cal.checkCharSymb(input, 8));
	}
	
	@Test
	void check_if_multiply_found() {	
		assertTrue(cal.checkCharSymb(input, 10));
	}
	
	@Test
	void check_if_divide_found() {	
		assertTrue(cal.checkCharSymb(input, 12));
	}
	
	@Test
	void check_if_power_found() {	
		assertTrue(cal.checkCharSymb(input, 14));
	}
	
	@Test
	void check_if_operation_not_found() {	
		assertFalse(cal.checkCharSymb(input, 0));
	}
	
	//checkCharBracket
	
	@Test
	void check_if_left_bracket_found() {	
		assertTrue(cal.checkCharBracket("( )", 0));
	}
	
	@Test
	void check_if_right_bracket_found() {	
		assertTrue(cal.checkCharBracket("( )", 2));
	}
	
	@Test
	void check_if_brackets_not_found() {	
		assertFalse(cal.checkCharBracket("( )", 1));
	}
	
	
	//checkIfNumCont
	@Test
	void check_out_of_bounds_index_for_NumCont() {	
		assertFalse(cal.checkIfNumCont(input, 14));
	}
	
	@Test
	void check_if_decimal_found_NumCont() {	
		assertTrue(cal.checkIfNumCont(input, 5));
	}
	
	@Test
	void check_if_num_found_NumCont() {	
		assertTrue(cal.checkIfNumCont(input, 6));
	}
	
	@Test
	void check_if_NumCont_false() {	
		assertFalse(cal.checkIfNumCont(input, 8));
	}
	//check NewtonRoot
	
	@Test
	void check_if_positive_root_works() {
		
		assertEquals(cal.newtonRoot(2, 4, 1), 2, 0.01);
	}
	
	@Test
	void check_if_negative_root_works() {
		
		assertEquals(cal.newtonRoot(-2, 4, 1), Math.pow(4,-0.5),0.01);
	}
	
	@Test
	void check_if_root_works_for_double_base() {
		
		assertEquals(cal.newtonRoot(4, 1.23, 1), Math.pow(1.23,0.25),0.01);
	}
	
	//check roundNum
	@Test
	void check_if_round_to_three_dec_works() {	
		assertEquals(cal.roundNum(1.5235,3), 1.524);
	}
	@Test
	void check_if_round_to_two_dec_works() {	
		assertEquals(cal.roundNum(1.5235,2), 1.52);
	}
	@Test
	void check_if_round_to_one_dec_works() {	
		assertEquals(cal.roundNum(1.5235,1), 1.5);
	}
	@Test
	void check_if_round_to_no_dec_works() {	
		assertEquals(cal.roundNum(1.5235,0), 2);
	}
	
	//check fraction
	@Test
	void check_if_fraction_works() {	
		double val = cal.roundNum(2.0/3.0, 3);
		double[] frac = {val, 1};
		frac = cal.fraction(frac);
		assertEquals(frac[0], 667);
		assertEquals(frac[1], 1000);
	}
	@Test
	void check_if_negative_decimal_works() {	
		double val = cal.roundNum(-1.73, 3);
		double[] frac = {val, 1};
		//System.out.println(exp);
		frac = cal.fraction(frac);
		assertEquals(frac[0], -173);
		assertEquals(frac[1], 100);
	}
	@Test
	void check_if_whole_number_works() {	
		double val = cal.roundNum(98.0, 3);
		double[] frac = {val, 1};
		frac = cal.fraction(frac);
		assertEquals(frac[0], 98);
		assertEquals(frac[1], 1);
	}
	
	//check multiplySelf
	@Test
	void check_if_square_works() {	
		assertEquals(cal.multiplySelf(5, 2), 25);
	}
	
	@Test
	void check_if_cube_works() {	
		assertEquals(cal.multiplySelf(5, 3), 125);
	}
	
	@Test
	void check_if_large_exponent_works() {	
		assertEquals(cal.multiplySelf(2,173), Math.pow(2, 173));
	}
	
	@Test
	void check_if_negative_exponent_works() {
		double result = Math.pow(2, -5);
		assertEquals(cal.multiplySelf(2, -5), result);
	}
	
	@Test
	void check_if_exponent_zero_works() {
		double result = Math.pow(2, 0);
		assertEquals(cal.multiplySelf(2, 0), result);
	}
	//check power
	@Test
	void check_if_no_root_works() {	
		assertEquals(cal.power(2, 2), Math.pow(2,2),0.001);
	}
	@Test
	void check_if_simple_root_works() {	
		assertEquals(cal.power((2.0/3.0), 2), Math.pow(2,(2.0/3.0)),0.001);
	}
	@Test
	void check_if_complex_root_works() {
		assertEquals(cal.power(1.73, 2), Math.pow(2,1.73),0.001);
	}
	@Test
	void check_if_negative_root_works_for_power() {
		assertEquals(cal.power(-(1.0/5.0), 2), Math.pow(2,-(1.0/5.0)),0.001);
	}
	
	//check sortNum
	@Test 
	void check_if_sort_num_works_positive() {
		ArrayList<Double> numbers = new ArrayList<Double>();
		int index = 3;
		index = cal.sortNum(numbers, index, "2","1+234");
		assertEquals(index,4);
		assertEquals(numbers.get(0),234);
		
	}
	@Test 
	void check_if_sort_num_works_negative() {
		ArrayList<Double> numbers = new ArrayList<Double>();
		int index = 3;
		index = cal.sortNum(numbers, index, "-","1+-234");
		assertEquals(index,5);
		assertEquals(numbers.get(0),-234);
	}
	@Test 
	void check_if_sort_num_works_decimal() {
		ArrayList<Double> numbers = new ArrayList<Double>();
		int index = 2;
		index = cal.sortNum(numbers, index, "","1+2.34");
		assertEquals(index,5);
		assertEquals(numbers.get(0),2.34);
	}
	@Test 
	void check_if_sort_num_works_false() {
		ArrayList<Double> numbers = new ArrayList<Double>();
		int index = 1;
		index = cal.sortNum(numbers, index, "1","1+234");
		assertEquals(index,0);
		assertEquals(numbers.get(0),1);
	}
	
	
	//checkPriority
	@Test
	void check_add_minus() {	
		assertFalse(cal.checkPriority('+', '-'));
	}
	
	@Test
	void check_multiply_divide() {	
		assertFalse(cal.checkPriority('*', '/'));
	}
	@Test
	void check_multiply_add() {	
		assertFalse(cal.checkPriority('*', '+'));
	}
	@Test
	void check_multiply_subtract() {	
		assertFalse(cal.checkPriority('*', '-'));
	}
	@Test
	void check_divide_add() {	
		assertFalse(cal.checkPriority('/', '+'));
	}
	@Test
	void check_divide_subtract() {	
		assertFalse(cal.checkPriority('/', '-'));
	}
	@Test
	void check_add_divide() {	
		assertTrue(cal.checkPriority('+', '/'));
	}
	@Test
	void check_add_multiply() {	
		assertTrue(cal.checkPriority('+', '*'));
	}
	@Test
	void check_subtract_multiply() {	
		assertTrue(cal.checkPriority('-', '*'));
	}
	@Test
	void check_subtract_divide() {	
		assertTrue(cal.checkPriority('-', '/'));
	}
	@Test
	void check_power_multiply() {
		assertFalse(cal.checkPriority('^', '*'));
	}
	@Test
	void check_divide_power() {
		assertTrue(cal.checkPriority('/', '^'));
	}
	
	//remove whitespace
	
	@Test
	void check_white_space_removed() {
		String example = " 1 + 2 ";
		example = cal.removeWhiteSpace(example);
		assertEquals(example, "1+2");
	}
	
	@Test
	void check_tab_space_removed() {
		String example = " 1	+	2 ";
		example = cal.removeWhiteSpace(example);
		assertEquals(example, "1+2");
	}
	
	@Test
	void check_nothing_removed() {
		String example = "1+2";
		example = cal.removeWhiteSpace(example);
		assertEquals(example, "1+2");
	}
	
	@Test
	void check_index_is_zero_for_negative_num_false() {	
		assertFalse(cal.checkNegativeNumber(0, input));
	}
	
	@Test
	void check_index_is_zero_for_negative_num_true() {	
		assertTrue(cal.checkNegativeNumber(0, "-5+6"));
	}
	
	@Test
	void check_if_there_is_negative_num() {	
		assertTrue(cal.checkNegativeNumber(3, "10/-5"));
	}
	
	@Test
	void check_if_no_negative_num_subtraction() {	
		assertFalse(cal.checkNegativeNumber(8, input));
	}
	
	//check sort operations
	@Test
	void check_sort_operations_reverse() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		symbols.add('+');
		order.add(0);
		symbols.add('*');
		order.add(1);
		cal.sortOperations(symbols, order, 0);
		symbols.add('^');
		order.add(2);
		cal.sortOperations(symbols, order, 0);
		assertEquals(order.get(0),2);
		assertEquals(order.get(1),1);
		assertEquals(order.get(2),0);
	}
	
	@Test
	void check_sort_operations_mixed() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		symbols.add('*');
		order.add(0);
		symbols.add('+');
		order.add(1);
		cal.sortOperations(symbols, order, 0);
		symbols.add('^');
		order.add(2);
		cal.sortOperations(symbols, order, 0);
		assertEquals(order.get(0),2);
		assertEquals(order.get(1),0);
		assertEquals(order.get(2),1);
	}
	
	@Test
	void check_sort_operations_no_change() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		symbols.add('^');
		order.add(0);
		symbols.add('*');
		order.add(1);
		cal.sortOperations(symbols, order, 0);
		symbols.add('+');
		order.add(2);
		cal.sortOperations(symbols, order, 0);
		assertEquals(order.get(0),0);
		assertEquals(order.get(1),1);
		assertEquals(order.get(2),2);
	}
	
	//check readbrackets
		@Test
		void beginning_brackets() {
			String str = "(4+5)*9";
			String newStr = cal.readBrackets(str, 0);
			assertEquals(newStr,"4+5");
		}
		
		@Test
		void no_brackets() {
			String str = "4+5*9";
			String newStr = cal.readBrackets(str, 0);
			assertEquals(newStr, str);
		}
		
		@Test
		void read_first_bracket() {
			String str = "(18/(4+5))*9";
			String newStr = cal.readBrackets(str, 0);
			assertEquals(newStr,"18/(4+5)");
		}
	
	//check fake evaluate
	@Test
	void checkEvaluationProcess() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		numbers.add(4.0);
		numbers.add(5.0);
		numbers.add(3.0);
		numbers.add(2.0);
		symbols.add('+');
		order.add(2);
		symbols.add('*');
		order.add(1);
		symbols.add('^');
		order.add(0);
		double result = cal.evaluate(numbers,symbols,order);
		assertEquals(result,49);
	}
	
	//check sortString
	
	@Test
	void sort_string_num() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		input = cal.removeWhiteSpace(input);
		cal.sortString(input, symbols, numbers, order, 0);
		assertEquals(numbers.size(),6);
		assertEquals(numbers.get(0), 1);
		assertEquals(numbers.get(1), 2.05);
		assertEquals(numbers.get(2), 5);
		assertEquals(numbers.get(3), 2);
		assertEquals(numbers.get(4), 2);
		assertEquals(numbers.get(5), 3);
		
	}
	
	@Test
	void sort_string_symb() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		input = cal.removeWhiteSpace(input);
		//"1 + 2.05-5*2/2^3"
		cal.sortString(input, symbols, numbers, order, 0);
		assertEquals(symbols.size(),5);
		assertEquals(symbols.get(0), '+');
		assertEquals(symbols.get(1), '-');
		assertEquals(symbols.get(2), '*');
		assertEquals(symbols.get(3), '/');
		assertEquals(symbols.get(4), '^');
	}
	
	@Test
	void sort_string_sort_operators() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		input = cal.removeWhiteSpace(input);
		//"1 + 2.05-5*2/2^3"
		cal.sortString(input, symbols, numbers, order, 0);
		assertEquals(order.size(),5);
		assertEquals(order.get(0), 4);
		assertEquals(order.get(1), 2);
		assertEquals(order.get(2), 3);
		assertEquals(order.get(3), 0);
		assertEquals(order.get(4), 1);
	}
	
	@Test
	void sort_string_brackets() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		String example = new String("2 * (5+10 - (6/2)) - 7.09");
		example = cal.removeWhiteSpace(example);
		cal.sortString(example, symbols, numbers, order, 0);
		assertEquals(numbers.size(),3);
		assertEquals(numbers.get(0), 2);
		assertEquals(numbers.get(1), 12);
		assertEquals(numbers.get(2), 7.09);
	}
	
	@Test
	void sort_string_out_of_bounds() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		input = cal.removeWhiteSpace(input);
		cal.sortString(input, symbols, numbers, order, 70);
		assertEquals(numbers.size(),0);
		assertEquals(symbols.size(),0);
		assertEquals(order.size(),0);
	}
	
	//evaluate Arithmetics
	@Test
	void check_if_add_works() {
		assertEquals(cal.evaluate(1,2,'+'), 3.0);
	}
	
	@Test
	void check_if_subtract_works() {
		assertEquals(cal.evaluate(1,2,'-'), -1.0);
	}
	
	@Test
	void check_if_multiply_works() {
		assertEquals(cal.evaluate(1,2,'*'), 2.0);
	}
	
	@Test
	void check_if_divide_works() {
		assertEquals(cal.evaluate(1,2,'/'), 0.5, 0.01);
	}
	
	@Test
	void check_if_power_works() {
		assertEquals(cal.evaluate(6.8,2.5,'^'), Math.pow(6.8, 2.5), 0.01);
	}
	//fix order
	@Test
	void check_if_out_of_bounds_for_fix_order() {
		ArrayList<Integer> order = new ArrayList<Integer>();
		order.add(1);
		order.add(2);
		assertDoesNotThrow(() -> cal.fixOrder(order, 5, 0));
		
		
	}
	@Test
	void check_if_order_index_values_change() {
		ArrayList<Integer> order = new ArrayList<Integer>();
		order.add(4);
		order.add(3);
		order.add(5);
		order.add(1);
		cal.fixOrder(order, 0, 2);
		assertEquals(order.get(0), 3);
		assertEquals(order.get(1), 2);
		assertEquals(order.get(2), 4);
		assertEquals(order.get(3), 1);
	}
	@Test
	void check_if_order_index_values_stay_same() {
		ArrayList<Integer> order = new ArrayList<Integer>();
		order.add(4);
		order.add(3);
		order.add(5);
		order.add(1);
		cal.fixOrder(order, 0, 8);
		assertEquals(order.get(0), 4);
		assertEquals(order.get(1), 3);
		assertEquals(order.get(2), 5);
		assertEquals(order.get(3), 1);
	}
	
	//evaluation process
	@Test
	void check_if_evaluate_process_works() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		symbols.add('+');
		symbols.add('*');
		symbols.add('-');
		numbers.add(1.0);
		numbers.add(2.0);
		numbers.add(3.0);
		numbers.add(4.0);
		order.add(1);
		order.add(0);
		order.add(2);
		assertEquals(cal.evaluate(numbers,symbols,order),3.0);
		
	}
	
	@Test
	void check_if_evaluate_returns_single_element() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		symbols.add('+');
		symbols.add('*');
		symbols.add('-');
		numbers.add(1.47);
		order.add(1);
		order.add(0);
		order.add(2);
		assertEquals(cal.evaluate(numbers,symbols,order),1.47);
	}
	
	@Test
	void check_if_no_number_elements() {
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		symbols.add('+');
		symbols.add('*');
		symbols.add('-');
		order.add(1);
		order.add(0);
		order.add(2);
		assertEquals(cal.evaluate(numbers,symbols,order),0);
	}
	
	//main evaluate
	@Test
	void check_simple_addition_string() {
		assertEquals(cal.evaluate("1+2"), 3.0);
	}
	
	@Test
	void check_simple_subtraction_string() {
		assertEquals(cal.evaluate("1-2"), -1.0);
	}
	
	@Test
	void check_simple_multiplication_string() {
		assertEquals(cal.evaluate("1*2"), 2.0);
	}
	
	@Test
	void check_simple_division_string() {
		assertEquals(cal.evaluate("1/2"), 0.5, 0.001);
	}
	
	@Test
	void check_white_space() {
		
		assertEquals(cal.evaluate("1 + 2"), 3.0);
	}
	
	@Test
	void check_tab_white_space() {
		
		assertEquals(cal.evaluate("1	+ 	2"), 3.0);
	}
	
	@Test
	void check_double_digits() {
		assertEquals(cal.evaluate("11+ 22"), 33.0);
	}
	
	@Test
	void check_decimal() {
		assertEquals(cal.evaluate("1.05+ 2.06"), 3.11, 0.001);
	}
	@Test
	void check_start_with_negative(){
		double result = -5.05 + 3; 
		assertEquals(cal.evaluate("-5.05+3"), result, 0.001);
	}
	
	@Test
	void check_middle_negative(){
		double result = 4 + -5; 
		assertEquals(cal.evaluate("4+-5"), result, 0.001);
	}
	
	@Test
	void check_two_subtract(){
		double result = 4 - -5; 
		assertEquals(cal.evaluate("4--5"), result, 0.001);
	}
	
	@Test
	void check_power(){
		double result = 4 - -5; 
		assertEquals(cal.evaluate("4^5"), result, 1024);
	}
	
	@Test
	void check_simple_bodmas() {
		assertEquals(cal.evaluate("1+2*3"), 7.0);
	}

	@Test
	void check_a_little_harder_bodmas() {
		assertEquals(cal.evaluate("1+2+3*4+6/2"), 18.0);
	}
	
	@Test
	void check_harder_bodmas(){
		double result = 9.5-2.8*4.5+6.8+3.7/2.1+0.83*21.34; 
		assertEquals(cal.evaluate("9.5-2.8*4.5+6.8+3.7/2.1+0.83*21.34"), result, 0.001);
	}
	
	@Test 
	void check_default_bodmas(){
		//fail("ugh");
		double result = 1 +2.05-5*2/Math.pow(2, 3);
		assertEquals(cal.evaluate(input), result, 0.001);
	}
	
	@Test
	void check_evaluate_bracket_left() {
		assertEquals(cal.evaluate("(4+5)*6"), 54.0);
	}
	
	@Test
	void check_evaluate_bracket_nest() {
		assertEquals(cal.evaluate("((7+5)/(2*3))"), 2);
	}
	
	@Test
	void check_evaluate_bracket_middle() {
		assertEquals(cal.evaluate("4-(4+5)*6"), -50.0);
	}
	
	@Test
	void check_evaluate_bracket_white_space() {
		assertEquals(cal.evaluate("( 4 + 5 )  *		6"), 54.0);
	}
	
	@Test
	void check_powers() {
		double result = 16+Math.pow(2.8, 5);
		assertEquals(cal.evaluate(" 4^2 + 2.8^5"), result,0.001);
	}
	
	@Test
	void check_power_negative() {
		double result = Math.pow(4, -2)+Math.pow(-2.8, 5);
		assertEquals(cal.evaluate(" 4^-2 + -2.8^5"), result,0.001);
	}
	
	@Test
	void check_power_fractional() {
		double result = Math.pow(4, 2.5)+Math.pow(2.8, (4.0/5.0));
		assertEquals(cal.evaluate(" 4^2.5 + 2.8^(4/5)"), result,0.001);
	}
	
	@Test 
	void check_a_little_bit_of_everything() {
		String newInput = new String("4 + 5 * (6		/(4-2)) ^ 3.5 -8.4");
		double result = 4+5*Math.pow((6/2.0), 3.5)-8.4;
		assertEquals(cal.evaluate(newInput),result,0.001);
	}
	
}
