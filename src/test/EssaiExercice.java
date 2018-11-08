package test;

import tp3.Expression;

public class EssaiExercice {

	public static void main(String[] args) {
		System.out.println(Expression.bienParentheser("fdqbhjk([)]"));
		System.out.println(Expression.parentheseToSuffixe("(((a+b)*c)+((d-e)/f))"));
	}

}
