package ru.job4j.ood.isp;

/**
 * Не все игры поддерживают методы из интерфейса, нужно создать интерфейс на каждые 1-2 метода.
 */
public interface Games {

    void menu(String menu);
    int showScore();
    void pause();
}

class Chess implements Games {

    private String buffer;

    @Override
    public void menu(String menu) {
        this.buffer = menu + buffer;
    }

    @Override
    public int showScore() {
        return -1;
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Pause is not available");

    }
}

class SeaFight implements Games {

    @Override
    public void menu(String menu) {
        System.out.println(menu);
    }

    @Override
    public int showScore() {
        return -1;
    }

    @Override
    public void pause() {
        System.out.println("Pause");
    }
}


