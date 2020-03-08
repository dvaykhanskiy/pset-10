import java.awt.EventQueue;

import javax.swing.JFrame;

public class DictionaryApp {

	private JFrame frmDic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryApp window = new DictionaryApp();
					window.frmDic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionaryApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frmDic.
	 */
	private void initialize() {
		frmDic = new JFrame();
		frmDic.setBounds(100, 100, 450, 300);
		frmDic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
