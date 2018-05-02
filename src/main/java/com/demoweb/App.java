package com.demoweb;

public class App 
{
	
	public boolean isEvenNumber(int number){

		boolean result = false;
		if(number%2 == 0){
			result = true;
		}
		return result;
	}

	public boolean isEmptyString(String string){

		boolean result = false;
		if(string.trim().equals("")){
			result = true;
		}
		return result;
	}

	public boolean isStringIsInterger(String string){

		boolean result = false;
		try{
			Integer.parseInt(string.trim());
			result = true;
		}catch (Exception e) {
			result = false;
		}
		return result;

	}
}