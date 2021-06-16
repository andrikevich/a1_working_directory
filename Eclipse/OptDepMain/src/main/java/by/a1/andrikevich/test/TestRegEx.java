package by.a1.andrikevich.test;

import java.util.regex.Pattern;

public class TestRegEx {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("\\d+");
		String testStr = "375291155365";
		System.out.println(Pattern.matches("[0-9]+", testStr));
		System.out.println(pattern.matcher(testStr).matches());
	}

}
