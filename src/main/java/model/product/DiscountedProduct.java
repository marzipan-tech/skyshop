package model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int percentDiscount;

    public DiscountedProduct(UUID id, String name, int percentDiscount, int basePrice) {
        super(id, name);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена не может быть меньше или равна 0");
        }
        if (percentDiscount < 0 || percentDiscount > 100) {
            throw new IllegalArgumentException("Размер скидки не может быть меньше 0 и больше 100");
        }
        this.percentDiscount = percentDiscount;
        this.basePrice = basePrice;
    }

    @Override
    public int getPrice() {
        return (int) (basePrice - basePrice / 100.0 * percentDiscount);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + percentDiscount + "%)";
    }
}

