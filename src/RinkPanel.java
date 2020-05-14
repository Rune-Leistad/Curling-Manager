import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RinkPanel extends JPanel {
	public BufferedImage rinkImage; 
	private JLabel imageLab;
	
	public RinkPanel() {
		imageLab = new JLabel(new ImageIcon(loadBufferedImage("Images/CurlingRink.png")));
		this.add(imageLab);

	}
	
	// make a RinkPanel() that reverses CurlingRink.png
	
	public void setBackgroundImage(String path) {
		rinkImage = loadBufferedImage(path);
	}
	
	private BufferedImage loadBufferedImage(String string) {
	    try {
	        BufferedImage bi = ImageIO.read(this.getClass().getResource(string));
	        return bi;
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
}
