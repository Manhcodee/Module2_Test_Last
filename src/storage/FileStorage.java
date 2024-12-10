package storage;

import model.*;

import java.io.*;
import java.util.*;

public class FileStorage {
    private static final String FILE_PATH = "data/mobiles.csv";

    // Đọc danh sách điện thoại từ file
    public List<Phone> loadPhones() {
        List<Phone> phones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8 && parts[0].matches("\\d+")) {
                    phones.add(new OfficialPhone(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]), parts[4], Integer.parseInt(parts[5]), parts[6]));
                } else if (parts.length == 7 && parts[0].matches("\\d+")) {
                    phones.add(new ImportedPhone(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]), parts[4], parts[5], parts[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phones;
    }

    // Lưu danh sách điện thoại vào file
    public void savePhones(List<Phone> phones) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Phone phone : phones) {
                if (phone instanceof OfficialPhone) {
                    OfficialPhone officialPhone = (OfficialPhone) phone;
                    writer.write(officialPhone.getId() + "," + officialPhone.getName() + ","
                            + officialPhone.getPrice() + "," + officialPhone.getQuantity() + ","
                            + officialPhone.getManufacturer() + "," + officialPhone.getWarrantyTime() + ","
                            + officialPhone.getWarrantyScope());
                } else if (phone instanceof ImportedPhone) {
                    ImportedPhone importedPhone = (ImportedPhone) phone;
                    writer.write(importedPhone.getId() + "," + importedPhone.getName() + ","
                            + importedPhone.getPrice() + "," + importedPhone.getQuantity() + ","
                            + importedPhone.getManufacturer() + "," + importedPhone.getCountry() + ","
                            + importedPhone.getStatus());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
