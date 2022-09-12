package project;

import org.apache.commons.io.FilenameUtils;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.display.SingleImagePanel;

import com.pixelmed.display.SourceImage;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Font;

public class App extends javax.swing.JFrame {

	private static AttributeList list = new AttributeList();
	private JFrame frmMedicalImageEncryption;
	private JButton btnNewButton;
	private JButton btnCompress;
	private JButton btnDecompress;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel properties;
	private JLabel lblpixel;
	private JLabel lblsize;
	private JLabel lblentropy;
	private JLabel time;
	private JLabel ratio;
	private JLabel size;
	private JLabel size_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JFileChooser jfc;

	private JLabel testLabel;
	private JLabel testLabel_1;
	private JLabel testLabel_2;
	private JLabel testLabel_3;
	private JLabel testLabel_4;
	private JLabel testLabel_5;
	private JLabel encryptLabel;
	private JLabel encryptKey;
	private JLabel decryptLabel;

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void initialize() {
		frmMedicalImageEncryption = new JFrame();
		frmMedicalImageEncryption.setFont(new Font("Consolas", Font.BOLD, 17));
		frmMedicalImageEncryption.setForeground(new Color(240, 255, 240));
		frmMedicalImageEncryption.setTitle("MEDICAL IMAGE ENCRYPTION AND COMPRESSION");
		frmMedicalImageEncryption.setBackground(new Color(192, 192, 192));
		frmMedicalImageEncryption.getContentPane().setBackground(new Color(95, 158, 160));
		frmMedicalImageEncryption.setBounds(100, 100, 1004, 440);
		frmMedicalImageEncryption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMedicalImageEncryption.getContentPane().setLayout(null);

		btnNewButton = new JButton("Choose Image");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnNewButtonActionPerformed(evt);
			}
		});
		btnNewButton.setBounds(832, 17, 125, 23);
		frmMedicalImageEncryption.getContentPane().add(btnNewButton);

		btnCompress = new JButton("Compress");
		btnCompress.setForeground(new Color(0, 0, 0));
		btnCompress.setBackground(new Color(192, 192, 192));
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					btnCompressActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCompress.setBounds(832, 47, 125, 23);
		frmMedicalImageEncryption.getContentPane().add(btnCompress);

		btnDecompress = new JButton("Decompress");
		btnDecompress.setForeground(new Color(0, 0, 0));
		btnDecompress.setBackground(new Color(192, 192, 192));
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					btnDeCompressActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDecompress.setBounds(832, 81, 125, 23);
		frmMedicalImageEncryption.getContentPane().add(btnDecompress);

		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setForeground(new Color(0, 0, 0));
		btnEncrypt.setBackground(new Color(192, 192, 192));
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					btnEncryptActionPerformed(evt);
			}
		});
		btnEncrypt.setBounds(832, 115, 125, 23);
		frmMedicalImageEncryption.getContentPane().add(btnEncrypt);

		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setForeground(new Color(0, 0, 0));
		btnDecrypt.setBackground(new Color(192, 192, 192));
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDecryptActionPerformed(evt);
		}
		});
		btnDecrypt.setBounds(832, 151, 125, 23);
		frmMedicalImageEncryption.getContentPane().add(btnDecrypt);

		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel_1.setBounds(584, 17, 226, 161);
		frmMedicalImageEncryption.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		properties = new JLabel(" ");
		properties.setBounds(10, 11, 125, 14);
		panel_1.add(properties);

		lblpixel = new JLabel("");
		lblpixel.setBounds(139, 95, 77, 14);
		panel_1.add(lblpixel);

		lblsize = new JLabel("");
		lblsize.setBounds(139, 52, 77, 14);
		panel_1.add(lblsize);

		lblentropy = new JLabel("");
		lblentropy.setBounds(139, 133, 65, 14);
		panel_1.add(lblentropy);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 48, 125, 23);
		panel_1.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 129, 125, 23);
		panel_1.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 91, 125, 23);
		panel_1.add(lblNewLabel_3);

		panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
		panel_2.setBounds(584, 195, 226, 174);
		frmMedicalImageEncryption.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		time = new JLabel("");
		time.setBounds(10, 11, 206, 29);
		panel_2.add(time);

		ratio = new JLabel("");
		ratio.setBounds(10, 51, 206, 28);
		panel_2.add(ratio);

		size = new JLabel("");
		size.setBounds(10, 90, 206, 29);
		panel_2.add(size);
		
		size_1 = new JLabel("");
		size_1.setBounds(10, 130, 206, 33);
		panel_2.add(size_1);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 564, 358);
		frmMedicalImageEncryption.getContentPane().add(panel);
		panel.setLayout(null);

		jfc = new JFileChooser();

		testLabel = new JLabel(" ");
		testLabel_1 = new JLabel(" ");
		testLabel_2 = new JLabel(" ");
		testLabel_3 = new JLabel(" ");
		testLabel_4 = new JLabel(" ");
		testLabel_5 = new JLabel(" ");
		encryptLabel = new JLabel(" ");
		encryptKey = new JLabel(" ");
		decryptLabel = new JLabel(" ");

		lblNewLabel = new JLabel("No Image Selected");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 554, 336);
		panel.add(lblNewLabel);
		frmMedicalImageEncryption.setVisible(true);

	}

	private void btnNewButtonActionPerformed(ActionEvent evt) {

		int returnVal = jfc.showOpenDialog(this);

		if (returnVal == jfc.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			time.setText(" ");
			size.setText("");
			size_1.setText("");
			ratio.setText("");
			if (file.exists()) {
				DisplayImage(file);
			} else {
				JOptionPane.showMessageDialog(jfc, "File doesn't exist");
			}
		}

	}

	private static double getShannonEntropy_Image(BufferedImage actualImage) {
		List<String> values = new ArrayList<String>();
		int n = 0;
		Map<Integer, Integer> occ = new HashMap<>();
		for (int i = 0; i < actualImage.getHeight(); i++) {
			for (int j = 0; j < actualImage.getWidth(); j++) {
				int pixel = actualImage.getRGB(j, i);
				int alpha = (pixel >> 24) & 0xff;
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				int d = (int) Math.round(0.2989 * red + 0.5870 * green + 0.1140 * blue);
				if (!values.contains(String.valueOf(d)))
					values.add(String.valueOf(d));
				if (occ.containsKey(d)) {
					occ.put(d, occ.get(d) + 1);
				} else {
					occ.put(d, 1);
				}
				++n;
			}
		}
		double e = 0.0;
		for (Map.Entry<Integer, Integer> entry : occ.entrySet()) {
			int cx = entry.getKey();
			double p = (double) entry.getValue() / n;
			e += p * log2(p);
		}
		return -e;
	}

	private static double log2(double p) {
		double result = (double) (Math.log(p) / Math.log(2));
		return result;
	}

	private void DisplayImageProperties(File file, SourceImage sImg) {
		double bytes = file.length();
		BufferedImage bufImage = sImg.getBufferedImage();

		properties.setText("Image Properties  ");
		lblNewLabel_1.setText("Image Size: ");
		lblNewLabel_3.setText("Pixel Composition: ");
		lblNewLabel_2.setText("Pixel Entropy");

		String size = String.format("%.2f", bytes / 1048576) + " mb";
		String dimensions = String.format("%d", sImg.getHeight()) + " x " + String.format("%d", sImg.getHeight())
				+ " px";
		String entropy = String.format("%.4f", getShannonEntropy_Image(bufImage));

		lblsize.setText(size);
		lblpixel.setText(dimensions);
		lblentropy.setText(entropy);

	}

	private void DisplayImage(File file) {
		try {
			SourceImage sImg = new SourceImage(file.getAbsolutePath());
			BufferedImage img = sImg.getBufferedImage();
			ImageIcon icon = new ImageIcon(img);
			lblNewLabel.setText("");
			lblNewLabel.setIcon(icon);

			testLabel.setText(file.getPath());
			String name = "Compressed_" + file.getName();
			String name_1 = "Decompressed_" + file.getName();
			String name_2 = "Encrypted_" + file.getName();
			String name_3 = "Decrypted_" + file.getName();
			testLabel_1.setText(name);
			testLabel_3.setText(name_1);
			testLabel_4.setText(name_2);
			testLabel_5.setText(name_3);
			
			encryptLabel.setText(file.getPath());
			DisplayImageProperties(file, sImg);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(jfc, "Choose Dicom Image");
		}

	}

	private void btnCompressActionPerformed(ActionEvent evt) throws Exception {
		FileInputStream fin;
		FileOutputStream fout;
		BufferedInputStream in;
		BufferedOutputStream out;
		long startTime_total = System.nanoTime();
		double fileLen, outputFileLen;
		long i = 0;
		int count = 0;
		int currentCh = 0, prevCh = 0;
		
		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();

		File outputFile = new File(path + "\\"+testLabel_1.getText());


		String outputFilename = outputFile.getPath();
		
		encryptLabel.setText(" ");

		String inputFilename = testLabel.getText();
		if (inputFilename != " ") {
			try {
				fin = new FileInputStream(inputFilename);
				in = new BufferedInputStream(fin);

				fout = new FileOutputStream(outputFilename);
				out = new BufferedOutputStream(fout);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;

			}
			fileLen = in.available();
			out.write("RLE".getBytes());
			prevCh = in.read();
			i++;
			count = 1;
			while (i < fileLen) {

				do {
					currentCh = in.read();
					i++;
					if (prevCh == currentCh)
						count++;
					if (count >= 255)
						break;
				} while (prevCh == currentCh && i < fileLen);

				if (count >= 4 || prevCh == 255) {
					out.write(255);
					out.write((char) prevCh);
					out.write((char) count);
				} else {
					for (int k = 0; k < count; k++)
						out.write(prevCh);

				}

				if (prevCh == currentCh)
					count = 0;
				else
					count = 1;
				prevCh = currentCh;

			}
			out.close();

			File outputFilee = new File(outputFilename);
			
			encryptLabel.setText(outputFilee.getPath());
			
			testLabel_2.setText(outputFilee.getPath());

			outputFileLen = outputFilee.length();
			long endTime_total = System.nanoTime();
			float time_total = Float.parseFloat("" + (endTime_total - startTime_total));
			
			float tt = (time_total / 1000000000);
			float cratio = (float) (outputFileLen * 100) / (float) fileLen;
			JOptionPane.showMessageDialog(null, "Image Compressed Successfully");
			
			time.setText("Compression Time: "+String.format("%.3f", tt)+ " seconds");
			size.setText("New Size: "+String.format("%.2f", outputFileLen/1048576) + "mb");
			ratio.setText("Compression Ratio: "+String.format("%.2f", cratio) + "%");
		} else {
			JOptionPane.showMessageDialog(null, "Choose Dicom Image to Compress");
		}


	}

	private void btnDeCompressActionPerformed(ActionEvent evt)throws Exception{
		FileInputStream fin;
		FileOutputStream fout;
		BufferedInputStream in;
		BufferedOutputStream out;
		long startTime_total = System.nanoTime();
		double fileLen, outputFileLen;
		long i = 0;
		int currentCh = 0, prevCh = 0;
		String buf = "";
		int ch,count;

		time.setText("");
		size.setText("");
		ratio.setText("");
		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();

		File outputFile = new File(path + "\\"+testLabel_3.getText());


		String outputFilename = outputFile.getPath();

		String inputFilename = testLabel_2.getText();
		if (inputFilename != " ") {
			encryptLabel.setText(" ");
			try {
				fin = new FileInputStream(inputFilename);
				in = new BufferedInputStream(fin);

				fout = new FileOutputStream(outputFilename);
				out = new BufferedOutputStream(fout);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;

			}
			fileLen = in.available();
			
			byte[] sig = new byte["RLE".length()];
			
			in.read(sig,0,"RLE".length());
			
			buf = new String(sig);
			
			if(!"RLE".equals(buf)) return;
			
			i = "RLE".length();
			
			while(i < fileLen){
				ch = in.read(); 
				i++;
				if(ch == 255 && i < fileLen){
					ch = in.read();
					count = in.read();
					i += 2;
					for(int k=0;k<count;k++) out.write((char)ch);
				}else{
					out.write((char)ch);
				}
							
			}
			
			out.close();
			File outputFilee = new File(outputFilename);			
			encryptLabel.setText(outputFilee.getPath());
			
			outputFileLen = outputFilee.length();
			long endTime_total = System.nanoTime();
			float time_total = Float.parseFloat("" + (endTime_total - startTime_total));
			
			float tt = (time_total / 1000000000);
			float cratio = (float) (fileLen * 100) / (float) outputFileLen;
			
			JOptionPane.showMessageDialog(null, "Image Decompressed Successfully");
			
			time.setText("Decompression Time: "+String.format("%.3f", tt)+ " seconds");
			size.setText("Compressed size: "+ String.format("%.2f", fileLen/1048576) + "mb");
			ratio.setText("Decompression Ratio: "+String.format("%.2f", cratio) + "%");
			size_1.setText("New Size: "+String.format("%.2f", outputFileLen/1048576) + "mb");
			 

		} else {
			JOptionPane.showMessageDialog(null, "Choose Compressed Dicom Image");
		}
		
		
	}
	
	private void btnEncryptActionPerformed(ActionEvent evt) {
		try {
				long startTime_total = System.nanoTime();
				FileInputStream file = new FileInputStream(encryptLabel.getText());
				Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
	            FileOutputStream output = new FileOutputStream(path + "\\"+testLabel_4.getText());
	            KeyGenerator keygen = KeyGenerator.getInstance("Blowfish");            //Created key generator based upon Blowfish cipher
	            byte k[]= JOptionPane.showInputDialog("Enter Key to Encrypt").getBytes();
	            
	            String put = new String(k);
	            
	            encryptKey.setText(put);
	            
	            
	            SecretKeySpec key = new SecretKeySpec(k, "Blowfish");// Created a key
	            Cipher cip = Cipher.getInstance("Blowfish");                      // Created a cipher based upon blowfish
	            cip.init(Cipher.ENCRYPT_MODE, key);                             // Initialising cipher with secret key
	            CipherOutputStream cos = new CipherOutputStream(output, cip);
	            byte[] buf = new byte[1024];
	            int read;
	            while((read=file.read(buf))!=-1){
	                cos.write(buf,0,read);
	            }
	            long endTime_total = System.nanoTime();
	            float time_total = Float.parseFloat("" + (endTime_total - startTime_total));
	            ImageIcon image = new ImageIcon("e.jpg");
	            lblNewLabel.setIcon(null);
	            lblNewLabel.setIcon(image);
				float tt = (time_total / 1000000000);
				time.setText(" ");
				size.setText("");
				size_1.setText("");
				ratio.setText("");
				time.setText("Encryption Time: "+String.format("%.3f", tt/10)+ " seconds");
				lblNewLabel_2.setText("");
				lblentropy.setText("");
	            file.close();
	            
	            output.flush();
	            cos.close();
				
				  
				 
				
	            
	            JOptionPane.showMessageDialog(null, "The DICOM Image Encrypted Successfully");
	            decryptLabel.setText(path + "\\"+testLabel_4.getText());
	           
	                   
	      }
		
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Choose DICOM Image to Encrypt");
	}

}

	private void btnDecryptActionPerformed(ActionEvent evt) {	
		try {
			long startTime_total = System.nanoTime();
			FileInputStream file = new FileInputStream(decryptLabel.getText());
			Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            
            KeyGenerator keygen = KeyGenerator.getInstance("Blowfish");            //Created key generator based upon Blowfish cipher
            byte k[]= JOptionPane.showInputDialog("Enter Key to Decrypt").getBytes();
            
            
            String kk = new String(k);
            String eKey = encryptKey.getText();
            
            if(kk.equals(eKey)) {
            	FileOutputStream output = new FileOutputStream(path + "\\" +testLabel_5.getText());
            	SecretKeySpec key = new SecretKeySpec(k, "Blowfish"); 
            	Cipher cip = Cipher.getInstance("Blowfish");                      // Created a cipher based upon blowfish
                cip.init(Cipher.DECRYPT_MODE, key);                             // Initialising cipher with secret key
                CipherOutputStream cos = new CipherOutputStream(output, cip);
                byte[] buf = new byte[1024];
                int read;
                while((read=file.read(buf))!=-1){
                    cos.write(buf,0,read);
                }
                
                long endTime_total = System.nanoTime();
	            float time_total = Float.parseFloat("" + (endTime_total - startTime_total));
	            float tt = (time_total / 1000000000);
	            time.setText(" ");
				size.setText("");
				size_1.setText("");
				ratio.setText("");
				time.setText("Decryption Time: "+String.format("%.3f", tt/10)+ " seconds");
				lblNewLabel_2.setText("");
				lblentropy.setText("");
                file.close();
                
                output.flush();
                cos.close();
                
                
                
                JOptionPane.showMessageDialog(null, "The DICOM Image Decrypted Successfully");
                File f = new File(path + "\\"+testLabel_5.getText());
                DisplayImage(f);
            	
            }
            else {
            	JOptionPane.showMessageDialog(null, "Incorrect Key");
            }
            
            
                   
      }
	
	catch(Exception e) {
		JOptionPane.showMessageDialog(null, "Choose DICOM Image to Decrypt");
}
		
		
	}
	
	
}


