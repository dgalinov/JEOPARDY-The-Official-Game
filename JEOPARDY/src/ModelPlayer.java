public class ModelPlayer {

    private int money;
    private String name;

    public ModelPlayer(String name) {
        this.money = 0;
        this.name = name;
    }

    public ModelPlayer() {
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (this.money + money < 0) {
            this.money = 0;
        } else {
            this.money += money;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelPlayer isWinner(ModelPlayer playerTwo) {
        if (this.getMoney() > playerTwo.getMoney()) {
            return this;
        } else {
            return playerTwo;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "money=" + money +
                ", name='" + name + '\'' +
                '}';
    }
}
