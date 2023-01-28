package com.registationSystem.regSys.Exceptions;

public class IncompatibleAge extends Exception{
    private int age;

    public IncompatibleAge(String message, int age){
        super(message);
    }
}
