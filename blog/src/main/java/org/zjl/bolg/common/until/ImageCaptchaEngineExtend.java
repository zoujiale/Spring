package org.zjl.bolg.common.until;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.jhlabs.image.WaterFilter;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.LineTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * @Title: ImageCaptchaEngineExtend.java
 * @Package org.zjl.bolg.common.until
 * @Description: ImageCaptchaEngineExtend
 * @author 704572528@qq.com
 * @date 2017年10月18日 上午12:37:40
 * @version V1.0
 */
public class ImageCaptchaEngineExtend extends ListImageCaptchaEngine {

	 protected void buildInitialFactories() {  
         
	        int minWordLength = 4;  
	         int maxWordLength = 4;  
	        int fontSize = 50;  
	        int imageWidth = 180;  
	         int imageHeight = 50;  
	  
	        // build filters  
	        WaterFilter water = new WaterFilter();  
	        water.setAmplitude(3d);//振幅    
	        water.setAntialias(true);//锯齿或平滑    
	        water.setPhase(20d);//相位    
	        water.setWavelength(70d);//波长    
	  
	        //backgroundDeformation 背景图变形    
	        ImageDeformation backDef = new ImageDeformationByFilters(  
	                new ImageFilter[] {});  
	         //textDeformation 字符图层转变形    
	        ImageDeformation textDef = new ImageDeformationByFilters(  
	                new ImageFilter[] {});  
	        //finalDeformation  最终图片变形    
	        ImageDeformation postDef = new ImageDeformationByFilters(  
	                new ImageFilter[] {  });  
	  
	        //生成单词    
	        WordGenerator dictionnaryWords = new RandomWordGenerator(  
	                "abcdefhjkmnprstuvwxyz23456789");  
	        // wordtoimage components  
	        RandomRangeColorGenerator colors = new RandomRangeColorGenerator(  
	                new int[] { 0, 150 }, new int[] { 0, 150 },  
	                new int[] { 0, 150 });  
	  
	        // 显示的字体设置，Arial,Tahoma,Verdana,Helvetica,宋体,黑体,幼圆  
	        Font[] fonts = new Font[] { new Font("Arial", 0, fontSize),  
	                new Font("Tahoma", 0, fontSize),  
	                new Font("Verdana", 0, fontSize),  
	                new Font("Helvetica", 0, fontSize),  
	                new Font("宋体", 0, fontSize), new Font("黑体", 0, fontSize),  
	                new Font("幼圆", 0, fontSize) };  
	  
	        // 设置字符以及干扰线  
	        /* 
	         * RandomRangeColorGenerator lineColors = new RandomRangeColorGenerator( 
	         * new int[] { 150, 255 }, new int[] { 150, 255 }, new int[] { 150, 255 
	         * }); 
	         */  
	  
	        /* 
	         * TextPaster randomPaster = new DecoratedRandomTextPaster(new 
	         * Integer(4), new Integer(4), colors, true, new TextDecorator[] { new 
	         * LineTextDecorator(new Integer(1), lineColors) }); 
	         */  
	  
	        // 文字显示的个数  
	        TextPaster randomPaster = new DecoratedRandomTextPaster(minWordLength,  
	                maxWordLength, colors, true, new TextDecorator[] {});  
	  
	        //背景图生成    
	        BackgroundGenerator back = new UniColorBackgroundGenerator(imageWidth,  
	                imageHeight, Color.white);  
	  
	         //字体生成    
	        FontGenerator shearedFont = new RandomFontGenerator(new Integer(30),  
	                new Integer(0), fonts);  
	  
	        // word2image 1  
	        WordToImage word2image;  
	        word2image = new DeformedComposedWordToImage(shearedFont, back,  
	                randomPaster, backDef, textDef, postDef);  
	  
	        this.addFactory(new GimpyFactory(dictionnaryWords, word2image));  
	  
	    }  

}
