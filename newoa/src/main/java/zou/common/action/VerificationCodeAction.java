package zou.common.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import zou.common.until.CaptchaServiceSingleton;

public class VerificationCodeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	
	public ByteArrayInputStream getCreatecode() throws IOException {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		
		byte[] bs = this.toImgae(req);
		
		ByteArrayInputStream stream = new ByteArrayInputStream(bs);
		
	
		return stream;
		
	}
	
	
	
	private byte[] toImgae(HttpServletRequest req) throws IOException {
		// 获取验证码背景图片的路径，这路径放了很多作为背景的图像
		 String captcha_backgrounds =
		 req.getSession().getServletContext().getRealPath("/image/captcha/");
		System.out.println(captcha_backgrounds);
		String id = req.getSession().getId();
	
		
		BufferedImage image = CaptchaServiceSingleton.getInstance().getImageChallengeForID(id,req.getLocale());
		
		
		// 获取验证码图片，这是未压缩的位图
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		// 应缩成jpg并写到输出流中
		ImageIO.write(image, "jpg", bao);

		return bao.toByteArray();

	}

	
}
