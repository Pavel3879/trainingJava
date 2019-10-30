package lesson37;

public class TestClass1 {

    public void method() {
        System.out.println(this.getClass().getName() + ".method tested");
    }

    @BeforeSuite
    public void methodBefore() {
        System.out.println(this.getClass().getName() + ".methodBefore tested");
    }

    @Test(priority = 1)
    public void methodTest11() {
        System.out.println(this.getClass().getName() + ".methodTest11 tested");
    }

    @Test(priority = 1)
    public void methodTest12() {
        System.out.println(this.getClass().getName() + ".methodTest12 tested");
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
