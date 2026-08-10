package day05;

public class Developer extends Employee {
    protected String programmingLanguage;
    public Developer(String programmingLanguage, String name, double salary){
        super(name,salary);
        this.programmingLanguage = programmingLanguage;
    }
    @Override
    public void work(){
        System.out.println("Simay is developing software with Java.");
    }
}
