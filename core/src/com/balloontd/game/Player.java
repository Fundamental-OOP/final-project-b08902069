package com.balloontd.game;

public class Player {
    private float hp;
    private float money;

    public Player(float hp, float money) {
        this.hp = hp;
        this.money = money;
    }

    public synchronized float getHP() { return hp; }
    public synchronized float getMoney() { return money; }
    public synchronized void setHP(float hp) { this.hp = hp; }
    public synchronized void setMoney(float money) { this.money = money; }
    public synchronized void costHP(float cost_hp) { hp -= cost_hp; }
    public synchronized void addMoney(float added_money) { money += added_money; }
}
