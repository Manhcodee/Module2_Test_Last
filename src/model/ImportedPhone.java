package model;

public class ImportedPhone extends Phone {
    private String country;
    private String status;

    public ImportedPhone(String id, String name, double price, int quantity, String manufacturer, String country, String status) {
        super(id, name, price, quantity, manufacturer);
        this.country = country;
        this.status = status;
    }

    @Override
    public void displayInfo() {
        System.out.println("Điện thoại xách tay:");
        System.out.println("ID: " + getId());
        System.out.println("Tên: " + getName());
        System.out.println("Giá bán: " + getPrice());
        System.out.println("Số lượng: " + getQuantity());
        System.out.println("Nhà sản xuất: " + getManufacturer());
        System.out.println("Quốc gia xách tay: " + country);
        System.out.println("Trạng thái: " + status);
    }

    public String getCountry() {
        return country;
    }

    public String getStatus() {
        return status;
    }
}
