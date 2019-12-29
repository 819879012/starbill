package util;

import java.awt.Color;

public class ColorUtil {
    
	public static Color blueColor = Color.decode("#3399FF");
    public static Color grayColor = Color.decode("#999999");
    public static Color backgroundColor = Color.decode("#eeeeee");
    public static Color warningColor = Color.decode("#FF3333");
    
    public static Color getByPercentage( int percentage )
    {
    	if( percentage > 100 )
    		percentage = 100;
    	int r = 51;
    	int g = 255;
    	int b = 51;
    	float rate = percentage / 100f;
    	r = (int) ((255 - 51) * rate + 51);
    	g = 255 - r + 51;
    	Color returnColor = new Color(r,g,b);
    	return returnColor;
    }
    
}
