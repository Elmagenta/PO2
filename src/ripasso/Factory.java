package ripasso;

public class Factory {

    public interface Shape {
        void draw();
    }

    public static class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    public static class Square implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    public static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    public static class ShapeFactory {

        //use getShape method to get object of type shape
        public Shape getShape(String shapeType) throws Exception {
            if (shapeType.equalsIgnoreCase("CIRCLE")) {
                return new Circle();

            }
            else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
                return new Rectangle();

            }
            else if (shapeType.equalsIgnoreCase("SQUARE")) {
                return new Square();
            }
            else throw new Exception("Invalid shape: " + shapeType);
        }
    }

    public static void main(String[] args) {
        try {
            ShapeFactory factory = new ShapeFactory();

            Shape shape1 = factory.getShape("CIRCLE");
            shape1.draw();

            Shape shape2 = factory.getShape("RECTANGLE");
            shape2.draw();

            Shape shape3 = factory.getShape("SQUARE");
            shape3.draw();

            Shape shape4 = factory.getShape("SPHERE");
            shape4.draw();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
