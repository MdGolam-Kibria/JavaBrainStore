package com.CrackCode.interviewQuestion.java8.funtionalInterface;

@FunctionalInterface
interface Mouth {
    void say(String speech);
}

/**
 * This is also Functional interface (if have a single method in a interface this is by default FunctionalInterface
 * if we annotate using @FunctionalInterface annotation this annotation advised to interface this is FunctionalInterface
 * you can't write another method cause you say to the interface is @FunctionalInterface
 * <p>
 * NOTE:-
 * A functional interface is a special kind of interface with exactly one abstract method in which
 * lambda expression parameters and return types are matched. It provides target types for lambda
 * expressions and method references
 */
interface Run<R, P> {
    R runAway(P userParam);
}

public class FunInterfaceTest {
    public static void main(String[] args) {
        //way 1 (Normal way)
        Mouth mouth = new Mouth() {
            @Override
            public void say(String speech) {
                System.out.println("Normal way = " + speech);
            }
        };
        mouth.say("hi i am kibria this is normal way to talk with functional interface");


        System.out.println("---------------------- using [lambda] expression--------------");


        Mouth lambdaMouth = speech -> {
            System.out.println("Using Lambda way = " + speech);
        };
        lambdaMouth.say("using Lambda expression");


        System.out.println("------------------Using [Generic and lambda] expression----------------");

        Run<String, String> runMan = result -> result;
        String val1 = runMan.runAway("using [Lambda and generic with functional interface]");
        System.out.println(val1);


        Run<Integer, String> runManWithNumber = Integer::valueOf;
        Integer val2 = runManWithNumber.runAway("12");
        System.out.println("" + val2);
    }
}
