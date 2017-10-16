package org.zjl.bolg.common.until;

import java.awt.Color;
import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * @ClassName: CapchaHelper
 * @Description: 生成验证码图片工具类
 * @author zou
 * @date 2017年10月15日 下午1:22:48
 * 
 */

public class CapchaHelper {
	private static final Integer MIN_WORD_LENGTH = 4;// 验证码最小长度
	private static final Integer MAX_WORD_LENGTH = 4;// 验证码最大长度
	private static final Integer IMAGE_HEIGHT = 30;// 验证码图片高度
	private static final Integer IMAGE_WIDTH = 130;// 验证码图片宽度
	private static final Integer MIN_FONT_SIZE = 15;// 验证码最小字体
	private static final Integer MAX_FONT_SIZE = 15;// 验证码最大字体
	
	
	//生成小写字母跟0-9的数字
	private static String Rando_word() {
		char[] number = new char[36];
		int index = 0;
		for (int i = 48; i <=57; i++) {
			number[index] = (char) i;
			index++;
			
		}
		for (int i = 97; i <= 122; i++) {
			number[index] = (char) i;
			index++;
		}
		return new String(number);

	}

	
	// 验证码随机字体大小
	private static final Font[] RANDOM_FONT = new Font[] { new Font("nyala", Font.BOLD, MIN_FONT_SIZE),
			new Font("Arial", Font.BOLD, MIN_FONT_SIZE), new Font("Bell MT", Font.BOLD, MIN_FONT_SIZE),
			new Font("Credit valley", Font.BOLD, MIN_FONT_SIZE), new Font("Impact", Font.BOLD, MIN_FONT_SIZE) };

	// 验证码随机颜色
	private static final Color[] RANDOM_COLOR = new Color[] { new Color(255, 225, 255), new Color(255, 220, 220),
			new Color(220, 255, 160), new Color(220, 180, 255), new Color(138, 255, 220), new Color(220, 255, 220) };

	private static ListImageCaptchaEngine captchaEngine;

	public static CaptchaEngine getCaptchaEngine(final String imgPath) {
		if (captchaEngine == null) {
			synchronized (CapchaHelper.class) {
				if (captchaEngine == null && imgPath != null) {
					captchaEngine = new ListImageCaptchaEngine() {
						@Override
						protected void buildInitialFactories() {
							RandomListColorGenerator randomListColorGenerator = new RandomListColorGenerator(
									RANDOM_COLOR);
							BackgroundGenerator backgroundGenerator = new FileReaderRandomBackgroundGenerator(
									IMAGE_WIDTH, IMAGE_HEIGHT, imgPath);
							String rando_word = Rando_word();
							WordGenerator wordGenerator = new RandomWordGenerator(rando_word);
							FontGenerator fontGenerator = new RandomFontGenerator(MIN_FONT_SIZE, MAX_FONT_SIZE,
									RANDOM_FONT);
							TextDecorator[] textDecorator = new TextDecorator[] {};
							TextPaster textPaster = new DecoratedRandomTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH,
									randomListColorGenerator, textDecorator);
							WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator,
									textPaster);
							addFactory(new GimpyFactory(wordGenerator, wordToImage));
						}
					};
				}
			}
		}
		return captchaEngine;
	}
}
