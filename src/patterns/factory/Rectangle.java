package patterns.factory;

class Rectangle implements Shape {
    private final double base;
    private final double height;

    Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println(String.format("Rectangle[%g x %g]", base, height));
    }

    @Override
    public double area() {
        return base * height;
    }

    @Override
    public double perimeter() {
        return (base + height) * 2;
    }
}
