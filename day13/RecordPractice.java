package day13;


public class RecordPractice {
    public record Product(
        Long id,
        String name,
        double price
    ){
        public static Product product = new Product(1L, "Laptop", 45000.0);
        public boolean isExpensive() {
            return price >= 10000;
        }
        public static void main(String[] args){
            System.out.println("Product ID:" + product.id() + "Product name:" + product.name() + "Product price:" + product.price());
        }
    }

    public record User(
            String username,
            int age
    ) {
        public User {
            if (age < 18) {
                throw new IllegalArgumentException(
                        "Age must be at least 18."
                );
            }
        }
    }
}
