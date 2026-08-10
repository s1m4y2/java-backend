package day06;

public interface Flyable {
    void fly();

    public static void main(String[] args){
        Bird bird = new Bird();
        bird.fly();
        Airplane airplane = new Airplane();
        airplane.fly();
    }
}
