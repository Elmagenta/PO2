package ripasso;

public class Singleton {

    public static class SingleObject {
        private static SingleObject instance = null;

        private SingleObject() {}

        public static SingleObject getInstance() {
            if (instance == null) {
                instance = new SingleObject();
            }

            return instance;
        }

        public void showMessage() {
            System.out.println("Hello World!\n");
        }
    }

    public static void main(String[] args) {

        //illegal construct
        //Compile Time Error: The constructor SingleObject() is not visible
        //SingleObject object = new SingleObject();

        //Get the only object available
        SingleObject object = SingleObject.getInstance();

        object.showMessage();
    }
}
