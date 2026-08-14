package day10;

public class GenericBox<T>{
    private T value;
    public GenericBox(T value){
        this.value = value;
    }
    public T getValue(){
        return value;
    }

    public static void main(String[] args){
        GenericBox<String> hello = new GenericBox<>("Hello Java");
        GenericBox<Integer> number = new GenericBox<>(100);
        System.out.println(hello.getValue());
        System.out.println(number.getValue());
    }
}