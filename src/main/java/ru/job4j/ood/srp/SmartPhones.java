package ru.job4j.ood.srp;

/**
 * Модель данных описывает характеристики Смартфонов разных моделей.
 * Правильнее было бы создать для каждой модели свою модель данных.
 * Также в классе есть метод, который считает общую сумму телефонов, его нужно вынести в отдельный класс
 * с бизнес-логикой.
 */
public class SmartPhones {

    private String iPhoneModel;
    private String iPhoneColor;
    private int iPhoneMemory;
    private float iPhonePrice;

    private String samsungGalaxyModel;
    private String samsungGalaxyColor;
    private int samsungGalaxyMemory;
    private float samsungGalaxyPrice;

    public float sum() {
        return iPhonePrice + samsungGalaxyPrice;
    }

    /* Getters and Setters */
}
