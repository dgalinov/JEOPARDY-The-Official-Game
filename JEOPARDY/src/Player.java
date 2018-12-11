public class Player {

    private int money;
    private String name;

    public Player(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public Player() {
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
