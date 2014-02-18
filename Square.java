import java.awt.Color;

public class Square
   implements Rectangle
{
    private int mSideLength;
    ShapeCoordinate mCoordinate;
    Color mColor;

    public Square(int pSideLength, int x ,int y, Color pColor)
    {
        mCoordinate = new ShapeCoordinate (x, y);
	mSideLength = pSideLength;
        mColor = pColor;
    }

    public int getSideLength()
    {
	return mSideLength;
    }

    public void setSideLength(int pSideLength)
    {
	mSideLength = pSideLength;
    }

    public ShapeDimension getSides()
    {
	return new ShapeDimension(mSideLength, mSideLength);
    }

    public ShapeCoordinate getCoordinate()
    {
	return mCoordinate;
    }

    public Color getColor()
    {
	return mColor;
    }
}