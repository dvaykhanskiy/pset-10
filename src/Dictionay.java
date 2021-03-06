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
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import javax.swing.event.ListSelectionEvent;

import com.google.gson.Gson;

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

public class Dictionay {

	private JFrame frmDictionary;
	private JTextField txtSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) throws FileNotFoundException {
		getWords();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dictionay window = new Dictionay();
					window.frmDictionary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static DefaultListModel<String> getWords() throws FileNotFoundException{
		Gson gson = new Gson();
        String classpathDirectory = Functions.getClasspathDir();
        BufferedReader br = new BufferedReader(new FileReader(classpathDirectory + "words.json"));
        Words[] words = gson.fromJson(br, Words[].class);
        DefaultListModel<String> listOfWords = new DefaultListModel<String>();
        for (Words word : words) {
        	listOfWords.addElement(word.getWord());
        }
       ;
        return  Functions.sortWordsAsc(listOfWords);
	}
	
	private static ArrayList<Words> getWordClass() throws FileNotFoundException{
		Gson gson = new Gson();
        String classpathDirectory = Functions.getClasspathDir();
        BufferedReader br = new BufferedReader(new FileReader(classpathDirectory + "words.json"));
        Words[] words = gson.fromJson(br, Words[].class);
        ArrayList<Words> listOfWords = new ArrayList<Words>();
        for (Words word : words) {
        	listOfWords.add(word);
        }
       ;
        return listOfWords;
	}

	
	public Dictionay() throws FileNotFoundException {
		initialize();
	}

	
	private void initialize() throws FileNotFoundException {
		frmDictionary = new JFrame();
		frmDictionary.setResizable(false);
		frmDictionary.setTitle("Dictionary");
		frmDictionary.setBounds(100, 100, 800, 600);
		frmDictionary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDictionary.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(207, 11, 566, 549);
		frmDictionary.getContentPane().add(scrollPane_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("1. Example (pos)");
		textPane.setEditable(false);
		scrollPane_2.setViewportView(textPane);
		StyledDocument doc = textPane.getStyledDocument();
		DefaultCaret caret = (DefaultCaret) textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 114, 179, 446);
		frmDictionary.getContentPane().add(scrollPane_1);
		
		JList<String> list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			boolean ranOnce = false;
			public void valueChanged(ListSelectionEvent arg0) {
				if(ranOnce) {
					ranOnce = false;
				}else {
					ranOnce = true;
					
					String selectedWord = list.getSelectedValue();
					System.out.println(selectedWord);
					
					try {
						ArrayList<Words> Words = getWordClass();
						for(Words word: Words) {
							if(word.getWord().equals(selectedWord)) {
								doc.remove(0, doc.getLength());
//								Style bigWord = textPane.addStyle()
								Definitions[] definitions = word.getDefinitions();
								int definitionCounter = 1;
								for (Definitions definition : definitions) {
									doc.insertString(doc.getLength(), definitionCounter + "." + selectedWord +" (" + definition.getPartOfSpeech() +")\n\n    "  +  definition.getDefinition() + "\n\n", null);
									definitionCounter++;
								}
								
							}
						}
					} catch (FileNotFoundException | BadLocationException e) {
						e.printStackTrace();
					}					
				}				
			}
		});
		scrollPane_1.setViewportView(list);
		
		DefaultListModel<String> DLM =  getWords();
		
		list.setModel(DLM);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("add");
			}
		});
		btnNewButton.setBounds(2, 11, 89, 23);
		frmDictionary.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Remove");
			}
		});
		btnNewButton_1.setBounds(101, 11, 89, 23);
		frmDictionary.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(490, 332, -57, -98);
		frmDictionary.getContentPane().add(scrollPane);
		
		
		

		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Asc");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(36, 78, 59, 23);
		frmDictionary.getContentPane().add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Desc");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(110, 78, 59, 23);
		frmDictionary.getContentPane().add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent event) {
		    	
		        int state = event.getStateChange();
		        if (state == ItemEvent.SELECTED) {		        	
		            System.out.println("desc");
		            try {
		            	txtSearch.setText("");
						list.setModel(Functions.reverseOrder(getWords()));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
		            
		        } else if (state == ItemEvent.DESELECTED) {
		        	System.out.println("asc");
		        	try {
		        		txtSearch.setText("");
						list.setModel(getWords());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}		 
		        }
		    }

		});
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String searched = txtSearch.getText().toLowerCase();
				System.out.println(searched);
				DefaultListModel<String> words = new DefaultListModel<String>();
				if (!rdbtnNewRadioButton.isSelected()) {		        	
				    try {
				    	words = Functions.reverseOrder(getWords());
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
					}
				    
				} else {
					try {
						words = getWords();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}		 
				}
				DefaultListModel<String> filtered = new DefaultListModel<String>();
				for(int i = 0 ; i < words.size(); i++) {
					if((words.get(i).startsWith(searched))) {
						System.out.println(words.get(i));
						filtered.addElement(words.get(i));							
					}
				}
				list.setModel(filtered);
				  
			}
		});
		txtSearch.setToolTipText("Search");
		txtSearch.setBounds(12, 45, 179, 20);
		frmDictionary.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
	}
}