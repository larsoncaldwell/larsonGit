public class CircleAdapter
   implements Rectangle
{
    private Circle mCircle;

    public CircleAdapter(Circle pCircle)
    {
	mCircle = pCircle;
    }

    public int[] getSides()
    {
	return new int[]{mCircle.getRadius(), mCircle.getRadius()};
    }
}