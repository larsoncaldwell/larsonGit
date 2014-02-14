public class ShapeTester
{
    public static void main(String[] args)
    {
	Rectangle theSquare = new Square(5);
        Circle theCircle = new Circle(2);
	Rectangle adapter = new CircleAdapter(theCircle);
	int[] c = theCircle.getRadii();
	int[] s = theSquare.getSides();
	int[] a = adapter.getSides();

	System.out.println("Circle: " + c[0] + " " + c[1]);
	System.out.println("Square: " + s[0] + " " + s[1]);
        System.out.println("Circle (addapted as a square): " 
                           + a[0] + " " + a[1]);
    }
}