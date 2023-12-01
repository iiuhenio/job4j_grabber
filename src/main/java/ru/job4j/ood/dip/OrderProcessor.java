package ru.job4j.ood.dip;

/**
 * В данном классе верхний модуль (OrderProcessor) и нижние модули (MySQLOrderRepository и
 *  ConfirmationEmailSender) связаны напрямую, не через абстракцию.
 *  Правильней было бы создать одноименные два интрефейса (MailSender, OrderRepository),
 *  имплементировать их в уже готовых для них
 *  двух классах (MySQLOrderRepository и ConfirmationEmailSender), затем в классе OrderProcessor
 *  создать объекты этих интрерфейсов и вызывать методы, обращаясь к ним
 */
public class OrderProcessor {
    public void process(Order order) {
        MySQLOrderRepository repository = new MySQLOrderRepository();
        ConfirmationEmailSender mailSender = new ConfirmationEmailSender();

        if (order.isValid() && repository.save(order)) {
            mailSender.sendConfirmationEmail(order);
        }
    }
}

class MySQLOrderRepository {
    public boolean save(Order order) {
        MySqlConnection connection = new MySqlConnection("database.url");
        /* сохраняем заказ в базу данных */
        return true;
    }
}

class ConfirmationEmailSender {
    public void sendConfirmationEmail(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();
        /* Шлем письмо клиенту */
    }
}

class Order {
    boolean isValid() {
        return true;
    }

    String getCustomerName() {
        return "Name";
    }

    String getCustomerEmail() {
        return "E-mail";
    }
}

class MySqlConnection {

    private String address;

    public MySqlConnection(String address) {
        this.address = address;
    }
}