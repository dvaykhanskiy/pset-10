import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledDocument;
import javax.swing.event.ListSelectionEvent;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class DictionaryApp {

	private JFrame frmDic;
	private JTextField txtSearch;
	private final Action add_action = new Add();
	private final Action remove_action = new Remove();

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
		frmDic = new JFrame("Dictionary");
		frmDic.setBounds(100, 100, 450, 300);
		frmDic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDic.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setAction(add_action);
		btnAdd.setBounds(0, 0, 77, 29);
		frmDic.getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setAction(remove_action);
		btnRemove.setBounds(67, 0, 77, 29);
		frmDic.getContentPane().add(btnRemove);
		
		txtSearch = new JTextField();
		txtSearch.setText("Search:");
		txtSearch.setBounds(10, 30, 130, 26);
		frmDic.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
	}

	private class Add extends AbstractAction {
		public Add() {
			putValue(NAME, "Add");
			putValue(SHORT_DESCRIPTION, "Adds a word");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("add");
		}
	}
	private class Remove extends AbstractAction {
		public Remove() {
			putValue(NAME, "Remove");
			putValue(SHORT_DESCRIPTION, "Removes a word");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("remove");
		}
	}
}
