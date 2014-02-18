import java.awt.Color;

public class RectangleAdapter
   implements Oval
{
    private Rectangle mRectangle;

    public RectangleAdapter(Rectangle pRectangle)
    {
	mRectangle = pRectangle;
    }

    public ShapeDimension getRadii()
    {
	return mRectangle.getSides();
    }
    
    public Color getColor()
    {
	return mRectangle.getColor();
    }

    public ShapeCoordinate getCoordinate()
    {
        return mRectangle.getCoordinate();
    }
}