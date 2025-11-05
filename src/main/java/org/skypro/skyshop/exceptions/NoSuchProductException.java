package org.skypro.skyshop.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Продукт не найден");
    }
}
