import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileNotFoundException;

public class GUIMenu {

    public static JFrame homePageFrame;
    public static Transcript myCourses;
    public static String data = "MyData";
    
    GUIMenu(){
    	myCourses = new Transcript();
    	
    	try {
    		myCourses.load(data);
    	}catch(IOException e) {
    		System.out.println("Empty file");
    	}catch(ClassNotFoundException e) {
    		System.out.println("Empty file");
    	}
    	
    	
    }


    
    public static void homePage() {
        homePageFrame = new JFrame("Home Page");
        homePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePageFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        homePageFrame.setLayout(new BoxLayout(homePageFrame.getContentPane(), BoxLayout.Y_AXIS));

        // Create empty panel at the top
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(38, 50, 56));
        homePageFrame.add(emptyPanel);

        // Create a panel for Insert and Delete buttons
        JButton insertButton = new JButton("Insert");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update"); // Added Update button
        customizeButton(insertButton, 300, 300, 60);
        customizeButton(deleteButton, 300, 300, 60);
        customizeButton(updateButton, 300, 300, 60); // Customize the Update button
        JPanel insertDeleteUpdatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        insertDeleteUpdatePanel.setBackground(new Color(38, 50, 56));
        insertDeleteUpdatePanel.add(insertButton);
        insertDeleteUpdatePanel.add(deleteButton);
        insertDeleteUpdatePanel.add(updateButton); // Add the Update button to the panel
        homePageFrame.add(insertDeleteUpdatePanel);

        // Create a panel for GPA and Search buttons
        JButton GPAButton = new JButton("GPA");
        JButton searchButton = new JButton("Search");
        customizeButton(GPAButton, 300, 300, 60);
        customizeButton(searchButton, 300, 300, 60);
        JPanel GPASearchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        GPASearchPanel.setBackground(new Color(38, 50, 56));
        GPASearchPanel.add(GPAButton);
        GPASearchPanel.add(searchButton);
        homePageFrame.add(GPASearchPanel);

        // Add action listeners for buttons
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertPage();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePage();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePage(); // Call the updatePage() method when the Update button is clicked
            }
        });

        GPAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GPAPage();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchPage();
            }
        });

        homePageFrame.setVisible(true);
    }

    public static void updatePage() {
        JFrame searchFrame = new JFrame("Search");

        JLabel searchLabel;
        JTextField searchText;
        JButton searchButton, backButton;
        JPanel dataPanel, buttonPanel;
        JTextArea displayArea;

        searchFrame.setLayout(new BorderLayout());
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dataPanel = new JPanel();
        dataPanel.setBackground(new Color(38, 50, 56));
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(38, 50, 56));

        searchLabel = new JLabel("Search Query:");
        searchText = new JTextField(25);

        searchButton = new JButton("Search");
        backButton = new JButton("Back");

        // Customize buttons
        customizeButton(searchButton, 100, 40, 16);
        customizeButton(backButton, 100, 40, 16);

        // Rest of your code...

        // Add components with customized appearance
        addComponentToPanelWithTextColor(dataPanel, "Search:", searchText);

        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(500, 400));

        searchFrame.add(dataPanel, BorderLayout.CENTER);
        searchFrame.add(buttonPanel, BorderLayout.EAST);
        searchFrame.add(displayArea, BorderLayout.SOUTH);

        searchFrame.setPreferredSize(new Dimension(900, 600));
        searchFrame.pack();
        searchFrame.setLocationRelativeTo(null); // Center the frame
        searchFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchFrame.dispose();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = getTextFromPanel(dataPanel, "Search:");

                try {
                    // Update the information based on the selected criterion
                    Course c = myCourses.search(searchQuery);
                    if (c != null) {
                        // Display the information
                        displayArea.setText("Course found:\n" + c.toString());

                        // Provide text fields for editing
                        JTextField nameField = new JTextField(c.getName());
                        JTextField hoursField = new JTextField(String.valueOf(c.getHours()));
                        JTextField yearField = new JTextField(String.valueOf(c.getYear()));
                        JTextField levelField = new JTextField(c.getLevel());
                        JTextField gradeField = new JTextField(c.getGrade());
                        JCheckBox isCompleteCheckbox = new JCheckBox();
                        isCompleteCheckbox.setSelected(c.getIsComplete());

                        JPanel editPanel = new JPanel(new GridLayout(6, 2));
                        editPanel.add(new JLabel("Name:"));
                        editPanel.add(nameField);
                        editPanel.add(new JLabel("Hours:"));
                        editPanel.add(hoursField);
                        editPanel.add(new JLabel("Year:"));
                        editPanel.add(yearField);
                        editPanel.add(new JLabel("Level:"));
                        editPanel.add(levelField);
                        editPanel.add(new JLabel("Grade:"));
                        editPanel.add(gradeField);
                        editPanel.add(new JLabel("Is Complete:"));
                        editPanel.add(isCompleteCheckbox);

                        int result = JOptionPane.showConfirmDialog(searchFrame, editPanel, "Edit Course Information",
                                JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            // Update the Course object with the edited information
                            c.setName(nameField.getText());
                            c.setHours(Integer.parseInt(hoursField.getText()));
                            c.setYear(Integer.parseInt(yearField.getText()));
                            c.setGrade(gradeField.getText());
                            c.setIsComplete(isCompleteCheckbox.isSelected());
                            // Update the display
                            displayArea.setText("Course updated:\n" + c.toString());
                        }
                    } else {
                        displayArea.setText("Course not found.");
                    }
                } catch (Exception ex) {
                    displayArea.setText("Error: " + ex.getMessage());
                }
            }
        });

    }



    	public static void insertPage() {
    	    JFrame insertFrame = new JFrame("Insert");
    	    insertFrame.setSize(600, 400);
    	    insertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    insertFrame.setLayout(new BorderLayout());

    	    JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // GridLayout with 2 columns, spacing of 10 pixels
    	    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	    mainPanel.setBackground(new Color(38, 50, 56));

    	    // Reordered components
    	    addComponentToPanelWithTextColor(mainPanel, "Name:", new JTextField());
    	    addComponentToPanelWithTextColor(mainPanel, "Symbol:", new JTextField());
    	    addComponentToPanelWithTextColor(mainPanel, "Grade:", new JTextField());
    	    addComponentToPanelWithTextColor(mainPanel, "Hours:", new JTextField());
    	    addComponentToPanelWithTextColor(mainPanel, "Year:", new JTextField());
    	    addComponentToPanelWithTextColor(mainPanel, "Level:", new JTextField());

    	    // Add Is Complete JComboBox
    	    JPanel isCompletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	    isCompletePanel.add(new JLabel("Is Complete:"));
    	    String[] options = {"Yes", "No"};
    	    JComboBox<String> isCompleteComboBox = new JComboBox<>(options);
    	    isCompletePanel.add(isCompleteComboBox);
    	    mainPanel.add(isCompletePanel);

    	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	    JButton backButton = new JButton("Back");
    	    JButton insertButton = new JButton("Insert");
    	    customizeButton(backButton, 100, 35, 20);
    	    customizeButton(insertButton, 100, 35, 20);
    	    buttonPanel.add(backButton);
    	    buttonPanel.add(insertButton);

    	    backButton.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            insertFrame.dispose();
    	        }
    	    });

    	    insertButton.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            // Get values from text fields and combo box
    	            String name = getTextFromPanel(mainPanel, "Name:");
    	            String symbol = getTextFromPanel(mainPanel, "Symbol:");
    	            String grade = getTextFromPanel(mainPanel, "Grade:");
    	            String hours = getTextFromPanel(mainPanel, "Hours:");
    	            String year = getTextFromPanel(mainPanel, "Year:");
    	            String level = getTextFromPanel(mainPanel, "Level:");
    	            String isComplete = (String) isCompleteComboBox.getSelectedItem();

    	            // Perform insertion logic    	            
    	            myCourses.insert(name, symbol, grade, hours, year, level, isComplete);
                    try {
                    	myCourses.save(data);
                    }catch(IOException ee) {
                		System.out.println("Empty file");
                    }
    	        }
    	    });

    	    insertFrame.add(mainPanel, BorderLayout.CENTER);
    	    insertFrame.add(buttonPanel, BorderLayout.SOUTH);

    	    // Make the frame visible
    	    insertFrame.setVisible(true);
    	}


    public static void deletePage() {
        JFrame deleteFrame = new JFrame("Delete");
        deleteFrame.setSize(400, 200);
        deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // GridLayout with 2 columns, spacing of 10 pixels
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(38, 50, 56));

        // Symbol field
        addComponentToPanelWithTextColor(mainPanel, "Symbol:", new JTextField());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        JButton deleteButton = new JButton("Delete");
        customizeButton(backButton, 100, 35, 20);
        customizeButton(deleteButton, 100, 35, 20);
        buttonPanel.add(backButton);
        buttonPanel.add(deleteButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFrame.dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get value from the text field
                String symbolToDelete = getTextFromPanel(mainPanel, "Symbol:");

                // Perform deletion logic
                try {
                	myCourses.delete(symbolToDelete);
                }catch(Exception ex) {
                	System.out.println(ex.getMessage());
                }
                try {
                	myCourses.save(data);
                }catch(IOException ee) {
            		System.out.println("Empty file");
                }
            }
        });

        deleteFrame.add(mainPanel, BorderLayout.CENTER);
        deleteFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        deleteFrame.setVisible(true);
    }

    
    public static void searchPage() {
        JFrame searchFrame = new JFrame("Search");


        JLabel searchLabel;
        JTextField searchText;
        JButton searchButton, backButton;
        JPanel dataPanel, buttonPanel;
        JTextArea displayArea;
        JComboBox<String> criterionComboBox; // Add ComboBox for criterion

        searchFrame.setLayout(new BorderLayout());
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dataPanel = new JPanel();
        dataPanel.setBackground(new Color(38, 50, 56));
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(38, 50, 56));

        searchLabel = new JLabel("Search Query:");
        searchText = new JTextField(25);

        // ComboBox for criterion
        String[] criteria = {"All","Symbol", "Hours", "Year", "Level", "Grade"};
        criterionComboBox = new JComboBox<>(criteria);

     // Inside your searchPage method
        searchButton = new JButton("Search");
        backButton = new JButton("Back");

        // Customize buttons
        customizeButton(searchButton, 100, 40, 16);
        customizeButton(backButton, 100, 40, 16);

        // Rest of your code...


        // Add components with customized appearance
        addComponentToPanelWithTextColor(dataPanel, "Search:", searchText);
        addComponentToPanelWithTextColor(dataPanel, "Search Criterion:", criterionComboBox);

        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(500, 400));

        searchFrame.add(dataPanel, BorderLayout.CENTER);
        searchFrame.add(buttonPanel, BorderLayout.EAST);
        searchFrame.add(displayArea, BorderLayout.SOUTH);

        searchFrame.setPreferredSize(new Dimension(900, 600));
        searchFrame.pack();
        searchFrame.setLocationRelativeTo(null); // Center the frame
        searchFrame.setVisible(true);
        

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchFrame.dispose();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = getTextFromPanel(dataPanel, "Search:");
                String selectedCriterion = (String) criterionComboBox.getSelectedItem();
                

                // Display the result in the JTextArea (replace this with your actual search result)
             // Assuming fixed widths for each column in the header
                int nameWidth = 25;
                int symbolWidth = 15;

                displayArea.setText(String.format("%-" + nameWidth + "s", "Name") + "\t" +
                                    String.format("%-" + symbolWidth + "s", "Symbol") + "\t" +
                                    "Year \t Level \t Hours \t Grade \t Is Complete? \n");
                
                if(selectedCriterion.equals("Symbol")) {
                	try {
                		Course c = myCourses.search(searchQuery);
                		displayArea.append(c.getName()+" \t "+c.getSymbol()+" \t "+c.getYear()+" \t "+c.getLevel()+" \t "+c.getHours()+" \t "+c.getGrade()+" \t "+c.getIsComplete()+" \t ");
                	}catch(Exception exc) {
                		System.out.println(exc.getMessage());
                		System.out.println(searchQuery);
                	}
                }
                
                else if(selectedCriterion.equals("Year")) {
                	LinkedList<Course> list = myCourses.yearSearch(searchQuery);
                	if(list.empty())
                		displayArea.append("Nothing found");
                	else {
                	    list.findFirst();
                	    while(!list.last()) {
                	        Course c = list.retrieve();
                	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
                	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

                	        displayArea.append(displayName + "\t" +
                	                          displaySymbol + "\t" +
                	                          c.getYear() + "\t " +
                	                          c.getLevel() + "\t " +
                	                          c.getHours() + "\t " +
                	                          c.getGrade() + "\t " +
                	                          c.getIsComplete() + "\n");
                	                          list.findNext();                	        
                	    }
                	    // Display the last element of the list
                	    Course c = list.retrieve();
            	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
            	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

            	        displayArea.append(displayName + "\t" +
            	                          displaySymbol + "\t" +
            	                          c.getYear() + "\t " +
            	                          c.getLevel() + "\t " +
            	                          c.getHours() + "\t " +
            	                          c.getGrade() + "\t " +
            	                          c.getIsComplete() + "\n");
            	        list.findNext();                	}
                }
                
                else if(selectedCriterion.equals("Level")) {
                	LinkedList<Course> list = myCourses.levelSearch(searchQuery);
                	if(list.empty())
                		displayArea.append("Nothing found");
                	else {
                	    list.findFirst();
                	    while(!list.last()) {
                	        Course c = list.retrieve();
                	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
                	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

                	        displayArea.append(displayName + "\t" +
                	                          displaySymbol + "\t" +
                	                          c.getYear() + "\t " +
                	                          c.getLevel() + "\t " +
                	                          c.getHours() + "\t " +
                	                          c.getGrade() + "\t " +
                	                          c.getIsComplete() + "\n");
                	                      	  list.findNext();
                	    }
                	    // Display the last element of the list
                	    Course c = list.retrieve();
            	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
            	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

            	        displayArea.append(displayName + "\t" +
            	                          displaySymbol + "\t" +
            	                          c.getYear() + "\t " +
            	                          c.getLevel() + "\t " +
            	                          c.getHours() + "\t " +
            	                          c.getGrade() + "\t " +
            	                          c.getIsComplete() + "\n");
            	                  	}
                }
                
                else if(selectedCriterion.equals("Hours")) {
                	LinkedList<Course> list = myCourses.hoursSearch(searchQuery);
                	if(list.empty())
                		displayArea.append("Nothing found");
                	else {
                	    list.findFirst();
                	    while(!list.last()) {
                	        Course c = list.retrieve();
                	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
                	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

                	        displayArea.append(displayName + "\t" +
                	                          displaySymbol + "\t" +
                	                          c.getYear() + "\t " +
                	                          c.getLevel() + "\t " +
                	                          c.getHours() + "\t " +
                	                          c.getGrade() + "\t " +
                	                          c.getIsComplete() + "\n");
                	                          list.findNext();
                	    }
                	    // Display the last element of the list
                	    Course c = list.retrieve();
            	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
            	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

            	        displayArea.append(displayName + "\t" +
            	                          displaySymbol + "\t" +
            	                          c.getYear() + "\t " +
            	                          c.getLevel() + "\t " +
            	                          c.getHours() + "\t " +
            	                          c.getGrade() + "\t " +
            	                          c.getIsComplete() + "\n");
            	                         
            	            }
                }
                
                else if(selectedCriterion.equals("Grade")) {
                	LinkedList<Course> list = myCourses.gradeSearch(searchQuery);
                	if(list.empty())
                		displayArea.append("Nothing found");
                	else {
                	    list.findFirst();
                	    while(!list.last()) {
                	        Course c = list.retrieve();
                	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
                	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

                	        displayArea.append(displayName + "\t" +
                	                          displaySymbol + "\t" +
                	                          c.getYear() + "\t " +
                	                          c.getLevel() + "\t " +
                	                          c.getHours() + "\t " +
                	                          c.getGrade() + "\t " +
                	                          c.getIsComplete() + "\n");
                	          	              list.findNext();
                	    }
                	    // Display the last element of the list
                	    Course c = list.retrieve();
            	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
            	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

            	        displayArea.append(displayName + "\t" +
            	                          displaySymbol + "\t" +
            	                          c.getYear() + "\t " +
            	                          c.getLevel() + "\t " +
            	                          c.getHours() + "\t " +
            	                          c.getGrade() + "\t " +
            	                          c.getIsComplete() + "\n");
            	               	}
                }
                
                else {
                	LinkedList<Course> list = myCourses.takeAll();
                	if(list.empty())
                		displayArea.append("Nothing found");
                	else {
                	    list.findFirst();
                	    while(!list.last()) {
                	        Course c = list.retrieve();
                	     // Assuming fixed widths for each column


                	        // Truncate and pad the name to fit the specified width
                	        String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
                	        String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

                	        displayArea.append(displayName + "\t" +
                	                          displaySymbol + "\t" +
                	                          c.getYear() + "\t " +
                	                          c.getLevel() + "\t " +
                	                          c.getHours() + "\t " +
                	                          c.getGrade() + "\t " +
                	                          c.getIsComplete() + "\n");
                	        
                	                          list.findNext();
                	    }
                	    // Display the last element of the list
                	    Course c = list.retrieve();
                	 // Assuming fixed widths for each column


                	    // Truncate and pad the name to fit the specified width
                	    String displayName = String.format("%-" + nameWidth + "s", (c.getName().length() > nameWidth) ? c.getName().substring(0, nameWidth - 3) + "..." : c.getName());
                	    String displaySymbol = String.format("%-" + symbolWidth + "s", (c.getSymbol().length() > symbolWidth) ? c.getSymbol().substring(0, symbolWidth - 3) + "..." : c.getSymbol());

                	    displayArea.append(displayName + "\t" +
                	                      displaySymbol + "\t" +
                	                      c.getYear() + "\t " +
                	                      c.getLevel() + "\t " +
                	                      c.getHours() + "\t " +
                	                      c.getGrade() + "\t " +
                	                      c.getIsComplete() + "\n");
                	}

                }   
            }
        });
    }

    
    public static void GPAPage() {
        JFrame gpaFrame = new JFrame("GPA");

        JLabel criterionLabel;
        JButton calculateButton, backButton;
        JPanel dataPanel, buttonPanel;
        JTextArea displayArea;
        JComboBox<String> criterionComboBox; // Add ComboBox for criterion


        
        
        gpaFrame.setLayout(new BorderLayout());
        gpaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dataPanel = new JPanel();
        dataPanel.setBackground(new Color(38, 50, 56));
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(38, 50, 56));

        criterionLabel = new JLabel("Select Criterion:");
        JTextField textField = new JTextField(10);


     addComponentToPanelWithTextColor(dataPanel, "Search:", textField);
        
        // ComboBox for criterion
        String[] criteria = {"All", "Year", "Level"};
        criterionComboBox = new JComboBox<>(criteria);

        // Inside your GPAPage method
        calculateButton = new JButton("Calculate GPA");
        backButton = new JButton("Back");

        // Customize buttons
        customizeButton(calculateButton, 150, 40, 16);
        customizeButton(backButton, 100, 40, 16);

        // Add components with customized appearance
        addComponentToPanelWithTextColor(dataPanel, "Select Criterion:", criterionComboBox);
        buttonPanel.add(calculateButton);
        buttonPanel.add(backButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(300, 350));

        gpaFrame.add(dataPanel, BorderLayout.CENTER);
        gpaFrame.add(buttonPanel, BorderLayout.EAST);
        gpaFrame.add(displayArea, BorderLayout.SOUTH);

        gpaFrame.setPreferredSize(new Dimension(650, 600));
        gpaFrame.pack();
        gpaFrame.setLocationRelativeTo(null); // Center the frame
        gpaFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gpaFrame.dispose();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCriterion = (String) criterionComboBox.getSelectedItem();
                String searchQuery = getTextFromPanel(dataPanel, "Search:");
                
                displayArea.setText(" All \t \t Only Complete \n");

                if (selectedCriterion.equals("Year")) {
                	int year = Integer.parseInt(searchQuery);
                	displayArea.append(myCourses.GPAInYear(year)+" \t \t "+myCourses.GPACompleteInYear(year));
                } 
                else if (selectedCriterion.equals("Level")) {
                   	int level = Integer.parseInt(searchQuery);
                	displayArea.append(myCourses.GPAInLevel(level)+" \t \t "+myCourses.GPACompleteInLevel(level));
                } 
                else {
                	displayArea.append(myCourses.AllGPA()+" \t \t "+myCourses.allCompleteGPA());
                }

            }
        });
    }

    
    
    private static String getTextFromPanel(JPanel panel, String label) {
        Component[] components = panel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel && ((JLabel) comp).getText().equals(label)) {
                ((JLabel) comp).setForeground(Color.WHITE); // Set text color to white
                int index = panel.getComponentZOrder(comp);
                if (index + 1 < components.length && components[index + 1] instanceof JTextField) {
                    return ((JTextField) components[index + 1]).getText();
                }
            }
        }
        return null;
    }

    private static void addComponentToPanelWithTextColor(JPanel panel, String label, JComponent component) {
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("MV Boli", Font.BOLD, 24)); // Set font to MV Boli, bold, size 16
        jLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(jLabel);
        panel.add(component);
    }

    public static void customizeButton(JButton button, int size1, int size2, int textSize) {
        button.setForeground(new Color(255, 253, 231));
        button.setBackground(new Color(188, 170, 164));
        button.setFont(new Font("MV Boli", Font.BOLD, textSize));
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        button.setPreferredSize(new Dimension(size1, size2));
        button.setOpaque(true); // Set opaque to true to ensure background color is displayed
    }

    
  
    public static void main(String[] args) {
    	
    	GUIMenu g = new GUIMenu();
    	
        g.homePage();
        

        
    }
}