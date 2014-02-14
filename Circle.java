public class Circle
   implements Oval
{
    private int mRadius;

    public Circle(int pRadius)
    {
	mRadius = pRadius;
    }

    public void setRadius(int pRadius)
    {
	mRadius = pRadius;
    }

    public int getRadius()
    {
       return mRadius;
    }

    public int[] getRadii()
    {
	return new int[]{mRadius, mRadius};
    }
}