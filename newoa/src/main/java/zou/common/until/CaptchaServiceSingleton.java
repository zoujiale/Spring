package zou.common.until;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**   
* @Title: CaptchaServiceSingleton.java 
* @Package org.zjl.bolg.common.until 
* @Description:获取验证码图片
* @author 704572528@qq.com 
* @date 2017年10月18日 上午12:41:18 
* @version V1.0   
*/
public class CaptchaServiceSingleton {
	@Resource
	private static HttpServletRequest req;
	 // 不允许构造实例  
    private CaptchaServiceSingleton() {  
    }  
  
    private static ImageCaptchaService instance = null;  
  
    // 传入样式类  
    static {  
        instance = new DefaultManageableImageCaptchaService(  
                new FastHashMapCaptchaStore(), new ImageCaptchaEngineExtend(),  
                180, 100000, 75000);  
    }  
  
    public static ImageCaptchaService getInstance() {  
        return instance;  
    }  
}
