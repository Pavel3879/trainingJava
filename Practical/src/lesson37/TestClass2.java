package lesson37;

public class TestClass2 {

    public void method() {
        System.out.println(this.getClass().getName() + ".method tested");
    }

    @BeforeSuite
    public void methodBefore1() {
        System.out.println(this.getClass().getName() + ".methodBefore1 tested");
    }

    @BeforeSuite
    public void methodBefore2() {
        System.out.println(this.getClass().getName() + ".methodBefore2 tested");
    }

    @Test(priority = 1)
    public void methodTest1() {
        System.out.println(this.getClass().getName() + ".methodTest1 tested");
    }

    @Test(priority = 2)
    public void methodTest2() {
        System.out.println(this.getClass().getName() + ".methodTest2 tested");
    }

    @Test(priority = 8)
    public void methodTest8() {
        System.out.println(this.getClass().getName() + ".methodTest8 tested");
    }

    @Test(priority = 4)
    public void methodTest4() {
        System.out.println(this.getClass().getName() + ".methodTest4 tested");
    }

    @AfterSuite
    public void methodAfter() {
        System.out.println(this.getClass().getName() + ".methodAfter tested");
    }
}
