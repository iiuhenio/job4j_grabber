package ru.job4j.ood.ocp;


/**
 * Предположим, способ отправки письма подтверждения был реализован следующим образом
 * здесь нарушен OCP принцип, т.к. при изменении способов отправки уведомлений
 * придется менять существующий код.
 */
public class MessageSender {
    //Шлем уведомление клиенту
    public void send(String msg, String type) {
        if (type.equals("SMS")) {
            sendSMS(msg);
        } else if (type.equals("E-mail")) {
            sendEMAIL(msg);
        }
    }

    private void sendEMAIL(String msg) {
    }

    private void sendSMS(String msg) {
    }
}