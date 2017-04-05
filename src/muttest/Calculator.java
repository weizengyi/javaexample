package muttest;

public class Calculator {
	
	public double add(double a, double b) {
		return a + b;
	}

	public double sub(double a, double b) {
		return a - b + 1;// 假设程序错误
	}

	public double multi(double a, double b) {
		return a * b;
	}

	public double div(double a, double b) {
		return a / b;
	}

}
