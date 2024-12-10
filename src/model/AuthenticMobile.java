package model;

public class AuthenticMobile extends Mobile {
    private int warrantyTime;
    private String warrantyRange;

    public AuthenticMobile(int id, String name, double price, int quantity, String manufacturer, int warrantyTime, String warrantyRange) {
        super(id, name, price, quantity, manufacturer);
        this.warrantyTime = warrantyTime;
        this.warrantyRange = warrantyRange;
    }

    public int getWarrantyTime() {
        return warrantyTime;
    }

    public String getWarrantyRange() {
        return warrantyRange;
    }

    @Override
    public String toCSV() {
        return getId() + "," + getName() + "," + getPrice() + "," + getQuantity() + "," +
                getManufacturer() + "," + warrantyTime + "," + warrantyRange;
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                ", Tên: " + getName() +
                ", Gia: " + getPrice() +
                ", So Luong: " + getQuantity() +
                ", San Xuat: " + getManufacturer() +
                ", Thoi gian bao hanh: " + warrantyTime +
                " ngay, pham vi bao hanh: " + warrantyRange;
    }
}