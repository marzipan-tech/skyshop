package model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть меньше или равна 0");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + price;
    }
}
