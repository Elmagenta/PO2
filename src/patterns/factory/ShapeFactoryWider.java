package patterns.factory;

import org.jetbrains.annotations.NotNull;

public class ShapeFactoryWider extends ShapeFactory {
    private final double amp;

    public ShapeFactoryWider(double amp) {
        this.amp = amp;
    }

    @Override
    @NotNull
    public Shape create(String name, double... data) throws Exception {
        if (name.toLowerCase().equals("rectangle"))
            return new Rectangle(data[0] * amp, data[1] * amp);
        else if(name.toLowerCase().equals("circle"))
            return new Circle(data[0] * amp);
        else throw new Exception("Invalid shape: " + name);
    }

    public static void main(String[] args) {
        try {
            ShapeFactory factory = new ShapeFactoryWider(4);
            Shape shape1 = factory.create("rectangle", 10, 11);
            Shape shape2 = factory.create("circle", 19);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
