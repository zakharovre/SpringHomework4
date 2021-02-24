package ru.geekbrains.hw4.exceptions;

public class NoSuchIdException extends RuntimeException{
    public NoSuchIdException(String message){
        super(message);
    }
}
