import java.awt.Color;

public class ShapeTester
{
    public static void main(String[] args)
    {
	Rectangle theSquare = new Square(5 ,5 ,5, Color.blue);
        Oval theCircle = new Circle(2, 2, 2, Color.green);
	Oval adapter = new RectangleAdapter(theSquare);
	ShapeDimension c = theCircle.getRadii();
	ShapeDimension s = theSquare.getSides();
	ShapeDimension a = adapter.getRadii();

	System.out.println("Oval: " + c.getWidth() + " " + c.getHeight());
	System.out.println("Rectange: " + s.getWidth() + " " + s.getHeight());
        System.out.println("Rectangle (addapted as a Oval): " + a.getWidth() 
                           + " " + a.getHeight());
    }
}