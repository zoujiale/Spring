package com.gzycdjk.identity.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gzycdjk.commons.vo.Message;
import com.gzycdjk.commons.vo.Page;
import com.gzycdjk.identity.dao.ArticleDao;
import com.gzycdjk.identity.domain.Article;
import com.gzycdjk.identity.service.ArticleService;

/**
 * 
 * 文章的服务层
 * 
 * @author YCJKmr.zo
 *
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articledao;

	@Override
	public Page<Article> findQueryArticle(Integer pageNumber) {
		Page<Article> pg = articledao.findByArticle(pageNumber);

		return pg;
	}

	@Override
	public Message saveArticle(Article article, MultipartFile file, HttpServletRequest req) {

		String defalutImage = "default.jpg";

		Message message = new Message();

		Article ar = new Article();
		ar.setImageName(file.getOriginalFilename());
		ar.setMainBody(article.getMainBody());
		ar.setSimpleDescripe(article.getSimpleDescripe());
		ar.setTitle(article.getTitle());
		ar.setSource(article.getSource());
		ar.setCreateDate(new Date());
		// 判断上传文件是否为空,空的话用默认图片
		if ("".equals(file.getOriginalFilename())) {
			ar.setImageName(defalutImage);
		}

		String path = "D:\\javaProject\\upload";
		System.out.println(path);
		// 这是Tomocat的临时文档
		File file2 = new File(path);
		if (!file2.exists()) {
			file2.mkdirs();
		}
		int last = file.getOriginalFilename().lastIndexOf(".");
		
		String string = UUID.randomUUID().toString()+file.getOriginalFilename().substring(last);
		int i = path.lastIndexOf("\\");
		path = path.substring(i);
		String path2 = req.getContextPath()  + path;
		
		try {
		
			file.transferTo(new File(file2 +"\\"+string));
			ar.setImageUrl(path2 +"\\"+string);
			
		} catch (IllegalStateException e1) {
			message.setMessage("图片上传失败,请重新上传");
			message.setState(false);
		} catch (IOException e1) {
			message.setMessage("图片上传失败");
			message.setState(false);
		}
		
		try {
			this.articledao.save(ar);
			message.setMessage("新闻保存完毕");
			message.setState(true);
		} catch (Exception e) {
			message.setState(false);
			message.setMessage("保存出错");

		}
	
		return message;
	}

	@Override
	public void deleteByArticle(String id) {
		Article article = articledao.load(Article.class, id);
		this.articledao.delete(article);

	}

}
