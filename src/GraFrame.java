import javax.swing.JFrame;

public class GraFrame extends JFrame{

	GraFrame(){
	
		
		this.add(new PanelGry());
		this.setTitle("Waz");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
