package com.tsystems.javaschool.tasks.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
	/**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
    	if(checkStringForForbiddenCharacter(statement)) {
    		List<String> resultList = new ArrayList<>(); 
    		List<String> tempList = getListCharacters(statement);
    		if(tempList.isEmpty()) {
    			return null;
    		} else {
    			if(tempList.contains("(")) {
    				resultList = operations(tempList);
    				resultList = delParentheses(resultList);
    			} else {
    				resultList = operations(tempList);
    			}
    		}
    		if(!resultList.isEmpty() && resultList.indexOf("") < 0 && resultList != null) {
    			StringBuilder sBuilder = new StringBuilder();
    			resultList.forEach(element -> sBuilder.append(element));
    			return sBuilder.toString();
    		}
    	}
    	return null;
    }
    
    private List<String> delParentheses(List<String> tempList) {
    	List<String> result = new ArrayList<>();
    	boolean wasThere = false;
    	for(int i = 0; i < tempList.size(); i++) {
    		if((i + 2) < tempList.size() 
    				&& tempList.get(i).matches("\\(")
    				&& tempList.get(i + 1).matches("-?[\\d.]+")
    				&& tempList.get(i + 2).matches("\\)")) {
    			result.add(tempList.get(i + 1));
    			i = i + 2;
    			wasThere = true;
    		} else {
    			result.add(tempList.get(i));
    		}
    	}
    	if(wasThere) {
    		result = operations(result);
    		result = delParentheses(result);
    	} else {
    		result = operations(result);
    	}
    	
    	if(result.contains("(") || result.contains(")"))
    		result.clear();
    			
    	return result;
    }
    
    private List<String> operations(List<String> tempList) {
    	List<String> result = new ArrayList<>();
    	boolean wasThere = false;
    	for(int i = 0; i < tempList.size(); i++) { 
			if((i + 2) < tempList.size() 
					&& tempList.get(i).matches("-?\\d|-?[\\d.]+")
					&& tempList.get(i + 1).equals("*")
					&& tempList.get(i + 2).matches("-?\\d|-?[\\d.]+")) {
				result.add(multiplication(tempList.get(i), tempList.get(i + 2)));
				i += 2;
				wasThere = true;
			} else if((i + 2) < tempList.size() 
					&& tempList.get(i).matches("-?\\d|-?[\\d.]+")
					&& tempList.get(i + 1).equals("/")
					&& tempList.get(i + 2).matches("-?\\d|-?[\\d.]+")) {
				result.add(division(tempList.get(i), tempList.get(i + 2)));
				i += 2;
				wasThere = true;
			} else {
				result.add(tempList.get(i));
			}
		}
    	tempList = result;
    	result = new ArrayList<>();
    	for(int i = 0; i < tempList.size(); i++) { 
			if((i + 2) < tempList.size() 
					&& tempList.get(i).matches("-?\\d|-?[\\d.]+")
					&& tempList.get(i + 1).equals("+")
					&& tempList.get(i + 2).matches("-?\\d|-?[\\d.]+")) {
				result.add(addition(tempList.get(i), tempList.get(i + 2)));
				i += 2;
				wasThere = true;
			} else if((i + 2) < tempList.size() 
					&& tempList.get(i).matches("-?\\d|-?[\\d.]+")
					&& tempList.get(i + 1).equals("-")
					&& tempList.get(i + 2).matches("-?\\d|-?[\\d.]+")) {
				result.add(subtraction(tempList.get(i), tempList.get(i + 2)));
				i += 2;
				wasThere = true;
			} else {
				result.add(tempList.get(i));
			}
		}
    	if (wasThere) {
    		result = operations(result);
    	}
    	return result;
    }
    
    private String multiplication(String a, String b) {
		if(a.matches("-?[\\d]+") && b.matches("-?[\\d]+")) {
			return String.valueOf(Integer.parseInt(a) * Integer.parseInt(b));
		}
		return String.format("%.4f",Double.parseDouble(a) * Double.parseDouble(b));
    }
    
    private String division(String a, String b) {
    	if(b.equals("0") || b.equals("0.0"))
    		return "";
    	
    	if(a.matches("-?[\\d]+") && b.matches("-?[\\d]+")) {
    		double z = Double.parseDouble(a) / Double.parseDouble(b);
    		if(new BigDecimal(z).scale() >= 1 )
    			return String.format("%.4f",Double.parseDouble(a) / Double.parseDouble(b));
    		return String.valueOf(Integer.parseInt(a) / Integer.parseInt(b));
		}
		return String.format("%.4f",Double.parseDouble(a) / Double.parseDouble(b));
    }
    
    private String addition(String a, String b) {
    	if(a.matches("-?[\\d]+") && b.matches("-?[\\d]+")) {
			return String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));
		}
		return String.valueOf(Double.parseDouble(a) + Double.parseDouble(b));
    }
    
    private String subtraction(String a, String b) {
    	if(a.matches("-?[\\d]+") && b.matches("-?[\\d]+")) {
			return String.valueOf(Integer.parseInt(a) - Integer.parseInt(b));
		}
		return String.valueOf(Double.parseDouble(a) - Double.parseDouble(b));
    }
    
    private List<String> getListCharacters(String statement) {
    	List<String> result = new ArrayList<>();
    	char[] charArray = statement.toCharArray();
    	for(int i = 0; i < charArray.length; i++) {
    		if(charArray[i] == '+') result.add("+");
			else if(charArray[i] == '-') result.add("-");
			else if(charArray[i] == '*') result.add("*");
			else if(charArray[i] == '/') result.add("/");
			else if(charArray[i] == '(') result.add("(");
			else if(charArray[i] == ')') result.add(")");
			else if (charArray[i] >= '0' && charArray[i] <= '9') {
				StringBuilder digitString = new StringBuilder();
				digitString.append(charArray[i]);
				if((i + 1) < charArray.length) {
					int k = i + 1;
					for(k = i + 1; k < charArray.length; k++) {
						if((charArray[k] >= '0' && charArray[k] <= '9') || charArray[k] == '.') {
							digitString.append(charArray[k]);
						} else {
							
							break;
						}
					}
					i = k - 1;
				}
				result.add(digitString.toString());
			} else {
				result.clear();
				return result;
			}
    	}
    	return result;
    }
    
    private boolean checkStringForForbiddenCharacter(String statement) {
    	if(statement == null 
    			|| statement.length() == 0
    			|| statement.isBlank()
    			|| statement.isEmpty()
    			|| statement.contains("null")
    			|| statement.contains("\0")
    			|| statement.contains("\0.0"))
    		return false;
    	int matches = 0;
    	for(char c : statement.toCharArray()) {
    			if(matches >= 2) return false;
    			if(c == '.' || c == '+' || c == '-' || c == '*' || c == '/') matches++;
    			else matches = 0;
    	}
        if(statement.matches("[\\d.\\+\\-\\*\\/\\(\\)]+"))
        	return true;
    	return false;
    }
    
    public static void main(String... args) {
    	Calculator c = new Calculator();
    	System.out.println(c.evaluate("22/4*2.159")); // Result: 151
    	//System.out.println(c.evaluate("((1+38)*4)-5")); // Result: 151
    	//System.out.println(c.evaluate("10/(5-5)")); // Result: 29
    	//System.out.println(c.evaluate(" -12)1//(")); // Result: null    	
    }
}
