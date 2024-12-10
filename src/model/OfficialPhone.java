package model;

public class OfficialPhone extends Phone{
    private int warrantyTime;
    private String warrantyScope;

    public OfficialPhone(String id, String name, double price, int quantity, String manufacturer, int warrantyTime, String warrantyScope) {
        super(id, name, price, quantity, manufacturer);
        this.warrantyTime = warrantyTime;
        this.warrantyScope = warrantyScope;
    }

    @Override
    public void displayInfo() {
        System.out.println("Điện thoại chính hãng:");
        System.out.println("ID: " + getId());
        System.out.println("Tên: " + getName());
        System.out.println("Giá bán: " + getPrice());
        System.out.println("Số lượng: " + getQuantity());
        System.out.println("Nhà sản xuất: " + getManufacturer());
        System.out.println("Thời gian bảo hành: " + warrantyTime + " tháng");
        System.out.println("Phạm vi bảo hành: " + warrantyScope);
    }

    public int getWarrantyTime() {
        return warrantyTime;
    }

    public String getWarrantyScope() {
        return warrantyScope;
    }
}

