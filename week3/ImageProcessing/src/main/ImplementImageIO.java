package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {
	private int testHeight;
	private int testWidth;
	private int [] testData;
	
	@Override
	public Image myRead(String filePath) throws IOException {
		// TODO Auto-generated method stub
		byte[] bitmapHead = new byte[14];
		byte[] bitmapInfo = new byte[40];
		int sizeInHead;
		int sizeInInfo;
		int width;
		int height;
		int depthOfColor;
		int blue;
		int green;
		int red;
		
		File file = new File(filePath);
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bitmapHead, 0, 14);
			fileInputStream.read(bitmapInfo, 0, 40);
			
			/*
			 所以大家应该能猜到为什么byte类型的数字要&0xff再赋值给int类型，其本质原因就是想保持二进制补码的一致性。

			当byte要转化为int的时候，高的24位必然会补1，这样，其二进制补码其实已经不一致了，&0xff可以将高的24位置为0，低8位保持原样。这样做的目的就是为了保证二进制数据的一致性。
			 */
			sizeInHead = (bitmapHead[2] & 0xff) | ((bitmapHead[3] & 0xff) << 8) | ((bitmapHead[4] & 0xff) << 16) | ((bitmapHead[5] & 0xff) << 24);
			sizeInInfo = (bitmapInfo[20] & 0xff) | ((bitmapInfo[21] & 0xff) << 8) | ((bitmapInfo[22] & 0xff) << 16) | ((bitmapInfo[23] & 0xff) << 24);
			width = (bitmapInfo[4] & 0xff) | ((bitmapInfo[5] & 0xff) << 8) | ((bitmapInfo[6] & 0xff) << 16) | ((bitmapInfo[7] & 0xff) << 24);
			height = (bitmapInfo[8] & 0xff) | ((bitmapInfo[9] & 0xff) << 8) | ((bitmapInfo[10] & 0xff) << 16) | ((bitmapInfo[11] & 0xff) << 24);
			depthOfColor = (bitmapInfo[14] & 0xff) | ((bitmapInfo[15] & 0xff) << 8);
			
			int skipNum = (depthOfColor * width / 8) % 4;
			if (skipNum != 0) {
				skipNum = 4 - skipNum;
			}
			
			if (depthOfColor == 24) {
				int data[] = new int [height * width];
				
				for (int i = height - 1; i >= 0; i--) {
					for (int j = 0; j < width; j++) {
						blue = fileInputStream.read();
						green = fileInputStream.read();
						red = fileInputStream.read();
						
						Color color = new Color(red, green, blue);
						data[i * width + j] = color.getRGB();
					}
					if (skipNum != 0) {
						fileInputStream.skip(skipNum);
					}
				}
				fileInputStream.close();
				
				testHeight = height;
				testWidth = width;
				testData = data;
				
				return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, data, 0, width));
			}
			else {
				fileInputStream.close();
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	@Override
	public Image myWrite(Image image, String filePath) throws IOException {
		// TODO Auto-generated method stub
		try {
			BufferedImage bitmapImage;
			bitmapImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		    Graphics2D bGr = bitmapImage.createGraphics();
		    bGr.drawImage(image, 0, 0, null);
		    bGr.dispose();
			ImageIO.write(bitmapImage, "bmp", new File(filePath));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public int getTestHeight() {
		return testHeight;
	}
	
	public int getTestWidth() {
		return testWidth;
	}
	
	public int[] getTestData() {
		return testData;
	}
}
