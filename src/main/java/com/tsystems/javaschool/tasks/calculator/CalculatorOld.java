package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorOld {

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
    		List<String> strList = getListCharacters(statement);
    		strList = multDiv(strList);
    		if(!strList.isEmpty())
    			strList = addSub(strList);
    		if(!strList.isEmpty())
    			strList = delParentheses(strList);
    		
    		return checkResultOnDouble(strList.get(0));
    	}
        return null;
    }
    
    private String checkResultOnDouble(String result) {
    	
    	String[] strings = result.split("\\.");
    	if(strings[1].equals("0"))
    		return Integer.valueOf(strings[0]).toString();
    	return result;
    }

    private List<String> delParentheses(List<String> strList) {
    	List<String> resultList = new ArrayList<>();
    	int count = 0;
    	for(int i = 0; i < strList.size(); i++) {
    		if((i + 2) < strList.size() && strList.get(i).matches("\\(") && strList.get(i + 1).matches("[-\\d.]+") && strList.get(i + 2).matches("\\)")) {
    			resultList.add(strList.get(i + 1));
    			i = i + 2;
    			count++;
    		} else {
    			resultList.add(strList.get(i));
    		}
    	}
    	if(count != 0) {
    			resultList = multDiv(resultList);
    		if(!resultList.isEmpty())
    			resultList = addSub(resultList);
    		if(!resultList.isEmpty())
    			resultList = delParentheses(resultList);
    	}
    		resultList = multDiv(resultList);
    	if(!resultList.isEmpty())
    		resultList = addSub(resultList);
    	return resultList;
    }
    
    private List<String> multDiv(List<String> strList) {
    	List<String> resultList = new ArrayList<>();
    	int count = 0;
    	for(int i = 0; i < strList.size(); i++) {
    		if((i + 2) < strList.size() && strList.get(i).matches("[-\\d.]+") && strList.get(i + 1).matches("\\*|\\/") && strList.get(i + 2).matches("[-\\d.]+")) {
    			double a = Double.valueOf(strList.get(i));
    			double b = Double.valueOf(strList.get(i + 2));
    			if(strList.get(i + 1).matches("\\*")) {
    				resultList.add(Double.toString(a * b));
    			} else {
    				if(b != 0.0)
    					resultList.add(Double.toString(a / b));
    				else {
    					resultList.clear();
    					return resultList;
    				}
    			}
    			count++;
    			i = i + 2;
    		} else if((i + 2) < strList.size() && strList.get(i).matches("[-\\d.]+") && strList.get(i + 1).matches("\\+|\\-|\\*|\\/") && strList.get(i + 2).matches("\\(")) {
    			resultList.add(strList.get(i));
    			resultList.add(strList.get(i + 1));
    			resultList.add(strList.get(i + 2));
    			i = i + 2;
    		} else if((i + 2) < strList.size() && strList.get(i).matches("\\)") && strList.get(i + 1).matches("\\+|\\-|\\*|\\/") && strList.get(i + 2).matches("[-\\d.]+")) {
    			resultList.add(strList.get(i));
    			resultList.add(strList.get(i + 1));
    			resultList.add(strList.get(i + 2));
    			i = i + 2;
        	}else {
    			resultList.add(strList.get(i));
    		}
    	}
    	if(count != 0) {
    		resultList = multDiv(resultList);
    	}
    	return resultList;
    }
    
    private List<String> addSub(List<String> strList) {
    	List<String> resultList = new ArrayList<>();
    	int count = 0;
    	for(int i = 0; i < strList.size(); i++) {
    		if((i + 2) < strList.size() && strList.get(i).matches("[-\\d.]+") && strList.get(i + 1).matches("\\+|\\-") && strList.get(i + 2).matches("[-\\d.]+")) {
    			double a = Double.valueOf(strList.get(i));
    			double b = Double.valueOf(strList.get(i + 2));
    			if(strList.get(i + 1).matches("\\+")) {
    				resultList.add(Double.toString(a + b));
    			} else {
    				resultList.add(Double.toString(a - b));
    			}
    			count++;
    			i = i + 2;
    		} else if((i + 2) < strList.size() && strList.get(i).matches("[-\\d.]+") && strList.get(i + 1).matches("\\+|\\-|\\*|\\/") && strList.get(i + 2).matches("\\(")) {
    			resultList.add(strList.get(i));
    			resultList.add(strList.get(i + 1));
    			resultList.add(strList.get(i + 2));
    			i = i + 2;
    		} else if((i + 2) < strList.size() && strList.get(i).matches("\\)") && strList.get(i + 1).matches("\\+|\\-|\\*|\\/") && strList.get(i + 2).matches("[-\\d.]+")) {
    			resultList.add(strList.get(i));
    			resultList.add(strList.get(i + 1));
    			resultList.add(strList.get(i + 2));
    			i = i + 2;
        	} else {
    			resultList.add(strList.get(i));
    		}
    	}
    	if(count != 0) {
    		resultList = addSub(resultList);
    	}
    	return resultList;
    }

    private List<String> getListCharacters(String statement) {
    	char[] charArray = statement.toCharArray();
		List<String> strList = new ArrayList<String>();
    	for(int i = 0; i < charArray.length; i++) {
			if(charArray[i] == '+') strList.add("+");
			else if(charArray[i] == '-') strList.add("-");
			else if(charArray[i] == '*') strList.add("*");
			else if(charArray[i] == '/') strList.add("/");
			else if(charArray[i] == '(') strList.add("(");
			else if(charArray[i] == ')') strList.add(")");
			else if (charArray[i] >= '0' && charArray[i] <= '9') {
				StringBuilder sb = new StringBuilder();
				int k = 0;
				for(k = i; k < charArray.length; k++) {
					if(charArray[k] != '+' && charArray[k] != '-' && charArray[k] != '*'
							&& charArray[k] != '/' && charArray[k] != '(' && charArray[k] != ')'){
						sb.append(charArray[k]);
					} else {
						strList.add(sb.toString());
						i = k - 1;
						break;
					}
				}
				if(k == charArray.length) 
					strList.add(sb.toString());
			}
		}
		return strList;
    }    
    
    private boolean checkStringForForbiddenCharacter(String statement) {
    	if(statement == null || statement.length() == 0 || statement.isBlank() || statement.isEmpty())
    		return false;
        if(statement.matches("[\\d.\\+\\-\\*\\/\\(\\)]+"))
        	return true;
    	return false;
    }
    
    public static void main(String... args) {
    	CalculatorOld c = new CalculatorOld();
    	//System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
    	System.out.println(c.evaluate("10/(5-5)")); // Result: 29
    	//System.out.println(c.evaluate(" -12)1//(")); // Result: null    	
    }
}
