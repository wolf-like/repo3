package zuoye;

public class TestAniml  implements  IAnimal{
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }

    @Override
    public void run() {
        System.out.println("猫起飞");
    }

    @Override
    public void sleep() {
        System.out.println("猫白天睡觉");
    }

    @Override
    public void speak() {
        System.out.println("喵喵喵..");
    }

    public static void main(String[] args) {
           TestAniml cat=new TestAniml();
            cat.eat();
            cat.run();
            cat.sleep();
            cat.speak();
    }
}
