package org.example;

public class DataProcessor {
    //Реалізації події - це по суті CallBack
    interface OnProcessData {
        void onSuccess(String result);
        void onError(Exception e);
    }

    public void processData(String data, OnProcessData callback) {
        try {
            String result = data.toUpperCase();
            callback.onSuccess(result); //Коли оброляємо дані - ми повертає інформацію про подію.
        }catch (Exception e) {
            callback.onError(e); //повертає помилку, якщо щось пішло не так через подію - вказівник
        }
    }
}
