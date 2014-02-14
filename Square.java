public class Square
   implements Rectangle
{
    private int mSideLength;

    public Square(int pSideLength)
    {
	mSideLength = pSideLength;
    }

    public void setSideLength(int pSideLength)
    {
	mSideLength = pSideLength;
    }

    public int getSideLength()
    {
       return mSideLength;
    }

    public int[] getSides()
    {
	return new int[]{mSideLength, mSideLength};
    }
}