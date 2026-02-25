package it.unipv.posfw.orbit.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import it.unipv.posfw.orbit.game.Game;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;

public class PublishingPage extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private String imagePath; // Used to save the image inside the 
	private JTextField titleField;
	private JTextField genreField;
	private JFormattedTextField priceField;

	public PublishingPage() {
		setResizable(false);
		setTitle("Orbit - Publish a game");
		setIconImage(new ImageIcon(new Prefab().ORBIT_ICON).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Prefab.FRAME_BG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// HEADER
		
		JPanel header = new JPanel();
		header.setBackground(Prefab.HEADER);
		header.setBounds(0, 0, 784, 60);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel headerText = new JLabel("PUBLISH A GAME");
		headerText.setForeground(Color.WHITE);
		headerText.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		headerText.setBounds(15, 0, 176, 60);
		header.add(headerText);
		
		// GAME INFO SECTION
		
		JPanel gameInfoPanel = new JPanel();
		gameInfoPanel.setBackground(Prefab.PANEL_BG);
		gameInfoPanel.setBounds(10, 90, 350, 400);
		contentPane.add(gameInfoPanel);
		gameInfoPanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("TITLE");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 16));
		titleLabel.setBounds(20, 20, 310, 30);
		gameInfoPanel.add(titleLabel);
		
		titleField = new JTextField();
		titleField.setBounds(20, 50, 310, 30);
		gameInfoPanel.add(titleField);
		titleField.setColumns(10);
		
		JLabel genreLabel = new JLabel("GENRE");
		genreLabel.setForeground(Color.WHITE);
		genreLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 16));
		genreLabel.setBackground(Color.WHITE);
		genreLabel.setBounds(20, 90, 310, 30);
		gameInfoPanel.add(genreLabel);
		
		genreField = new JTextField();
		genreField.setColumns(10);
		genreField.setBounds(20, 120, 310, 30);
		gameInfoPanel.add(genreField);
		
		JLabel priceLabel = new JLabel("BASE PRICE");
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 16));
		priceLabel.setBackground(Color.WHITE);
		priceLabel.setBounds(20, 160, 310, 30);
		gameInfoPanel.add(priceLabel);
		
		priceField = new JFormattedTextField();
		try {    
		    MaskFormatter priceMask = new MaskFormatter("##" + "." + "##" + "€"); // Defines a mask for a the price
		    priceMask.setPlaceholderCharacter('0'); // Sets a placeholder value
		    priceMask.install(priceField); // Applies the mask
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		priceField.setColumns(10);
		priceField.setBounds(20, 190, 310, 30);
		gameInfoPanel.add(priceField);
		
		JButton publishGameBtn = new JButton("PUBLISH GAME");
		publishGameBtn.setForeground(Color.WHITE);
		publishGameBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		publishGameBtn.setFocusable(false);
		publishGameBtn.setFocusPainted(false);
		publishGameBtn.setBorderPainted(false);
		publishGameBtn.setBackground(new Color(180, 160, 220));
		publishGameBtn.setBounds(20, 340, 200, 40);
		
		// Action Listener
		publishGameBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	double price;
		    	String imgName;
		    	String filePath;
		    	try {
		    		
		    		checkValidInfo();
		    		// Create a game object with the inserted infos
		    		imgName = generateFileName();
		    		price = priceToDouble();
		    		saveImageToSrc(imgName); // create a copy of the image in the resources folder
		    		filePath = "/images/grids/" + imgName + ".png";
		    		Game game = new Game(titleField.getText(), price, genreField.getText(), filePath);
		    		FacadeUI.getInstance().publishGame(game);
		    	}
		    	catch (NullPointerException npe) {
		    		JOptionPane.showMessageDialog(null, "Insert valid informations", "Error", JOptionPane.ERROR_MESSAGE);
		    	}
		    }
		});
		gameInfoPanel.add(publishGameBtn);
		
		// IMAGE UPLOADING SECTION
		
		JLabel imagePreview = new JLabel();
		imagePreview.setIcon(new ImageIcon(new Prefab().PLACEHOLDER_COVER));
		imagePreview.setBounds(450, 90, 100, 140);
		contentPane.add(imagePreview);
		
		JButton uploadImgBtn = new JButton("CHOOSE COVER");
		uploadImgBtn.setForeground(Color.WHITE);
		uploadImgBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		uploadImgBtn.setFocusable(false);
		uploadImgBtn.setFocusPainted(false);
		uploadImgBtn.setBorderPainted(false);
		uploadImgBtn.setBackground(Prefab.BUTTON_PURPLE);
		
		uploadImgBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	uploadImageFile(imagePreview);
		    }
		});
		
		uploadImgBtn.setBounds(400, 260, 200, 40);
		contentPane.add(uploadImgBtn);
		
		JLabel noteLabel = new JLabel("Note: the image must be 100x140 pixels");
		noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noteLabel.setForeground(Color.WHITE);
		noteLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		noteLabel.setVerticalAlignment(SwingConstants.TOP);
		noteLabel.setBounds(372, 310, 250, 54);
		contentPane.add(noteLabel);

	}
	
	
	// Helpers
	
	private void uploadImageFile(JLabel imgLabel) {
        JFileChooser fileChooser = new JFileChooser();
        
        // Filter: only .png format accepted
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image File (PNG)", "png");
        fileChooser.setFileFilter(filter);
        
        int result = fileChooser.showOpenDialog(null);
        
        // When the publisher chooses a valid file run this code
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            imagePath = path; // Set the image path as a global variable
            ImageIcon imageIcon = new ImageIcon(path);
            
            // Resize the image to the label's width and height
            Image image = imageIcon.getImage(); // Convert the ImageIcon into an Image to be able to resize it
            Image newimg = image.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),  java.awt.Image.SCALE_SMOOTH); 
            imageIcon = new ImageIcon(newimg);  // Reconvert to ImageIcon to be able to use it for the label
            imgLabel.setIcon(imageIcon);
        }

	}
	
	private boolean saveImageToSrc(String imgFileName) {
	    String srcFolderPath = "res/images/grids"; // Target Folder
	    String fileName = imgFileName + ".png"; // generates a file named "game_title.png"

	    try {
	        // 2. Read image file
	        File fileImage = new File(imagePath);
	        BufferedImage image = ImageIO.read(fileImage);
	        
	        // Check if the image exists
	        if (image == null) {
	            System.out.println("Source image not valid");
	            return false;
	        }
	        
	        File directory = new File(srcFolderPath); // Folder pathName
	        File file = new File(directory, fileName); // Actual file

	        // Save the image
	        ImageIO.write(image, "png", file);
	        System.out.println("Image saved successfully in: " + file.getPath()); // Debug
	        return true;

	    } catch (IOException e) {
	        System.out.println("Error while saving the image: " + e.getMessage());
	        return false;
	    }
	}
	
	private String generateFileName(){
		// create a valid file name using the standard "game_title"
		String imgName = titleField.getText();
    	imgName = imgName.toLowerCase();
    	imgName = imgName.replaceAll(" ", "_");
    	imgName = imgName.replaceAll(":", "");
    	imgName = imgName.replaceAll(",", "");
    	System.out.println(imgName); // Debug
    	
    	return imgName;
	}
	
	private void checkValidInfo() throws NullPointerException{
		String title = titleField.getText();
		String genre = genreField.getText();
		
    	// If the name is not valid, throw an exception
    	if(title == null || title.isBlank() || title.isEmpty()) {
    		throw new NullPointerException();
    	}
    	
    	// If the name is not valid, throw an exception
    	if(genre == null || genre.isBlank() || genre.isEmpty()) {
    		throw new NullPointerException();
    	}
    	
	}
	
	private double priceToDouble() {
		String priceStr = priceField.getText();
		double pricedbl;
		priceStr = priceStr.replaceAll("€", "");
		System.out.println(priceStr);
		pricedbl = Double.valueOf(priceStr);
		
		return pricedbl;
	}
	
}