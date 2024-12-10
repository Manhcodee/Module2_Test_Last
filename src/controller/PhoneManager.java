package controller;

import model.*;
import storage.FileStorage;

import java.util.*;

public class PhoneManager {
    private List<Phone> phones;
    private FileStorage fileStorage;

    public PhoneManager() {
        phones = new ArrayList<>();
        fileStorage = new FileStorage();
        loadPhonesFromFile();
    }

    // Đọc danh sách điện thoại từ file
    private void loadPhonesFromFile() {
        phones = fileStorage.loadPhones();
    }

    // Lưu danh sách điện thoại vào file
    public void savePhonesToFile() {
        fileStorage.savePhones(phones);
    }

    // Thêm điện thoại mới vào danh sách
    public void addPhone(Phone phone) {
        int newId = phones.isEmpty() ? 1 : phones.get(phones.size() - 1).getId() + 1;
        phone.setId(newId);
        phones.add(phone);
        savePhonesToFile();
    }

    // Xóa điện thoại theo ID
    public void removePhoneById(int id) throws NotFoundProductException {
        Phone phoneToRemove = null;
        for (Phone phone : phones) {
            if (phone.getId() == id) {
                phoneToRemove = phone;
                break;
            }
        }
        if (phoneToRemove == null) {
            throw new NotFoundProductException("ID điện thoại không tồn tại.");
        }
        phones.remove(phoneToRemove);
        savePhonesToFile();
    }

    // Hiển thị tất cả điện thoại
    public void displayAllPhones() {
        if (phones.isEmpty()) {
            System.out.println("Không có điện thoại nào trong danh sách.");
        } else {
            for (Phone phone : phones) {
                System.out.println(phone.toString());
            }
        }
    }

    // Tìm kiếm điện thoại theo ID hoặc tên
    public void searchPhone(String keyword) {
        List<Phone> result = new ArrayList<>();
        for (Phone phone : phones) {
            if (phone.getId() == Integer.parseInt(keyword) || phone.getName().to
