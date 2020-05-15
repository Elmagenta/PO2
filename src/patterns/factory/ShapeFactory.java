package patterns.factory;

import org.jetbrains.annotations.NotNull;

public class ShapeFactory {

    public static void main(String[] args) {
        try {
            ShapeFactory factory = new ShapeFactory();
            Shape shape1 = factory.create("rectangle", 10, 11);
            Shape shape2 = factory.create("circle", 19);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public Shape create(String name, double... data) throws Exception {
        if (name.toLowerCase().equals("rectangle"))
            return new Rectangle(data[0], data[1]);
        else if(name.toLowerCase().equals("circle"))
            return new Circle(data[0]);
        else throw new Exception("Invalid shape: " + name);
    }
}
