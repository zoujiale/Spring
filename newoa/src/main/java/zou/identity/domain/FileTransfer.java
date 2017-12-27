package zou.identity.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity	
@Table(name = "blog_filetransfer")
@Getter@Setter
public class FileTransfer {
	
	@Id
	@GenericGenerator(name = "uuid" ,strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
	private String id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@ManyToOne()
	@JoinColumn(columnDefinition = "user_id")
	private User user;
	
	@Column(name = "upload_date")
	private Date uploadDate;
	
	@Column(length = 50,name="file_description")
	private String fileDescription ;
	
	@Column(name = "upload_content_type")
	private String uploadContentType;// 要上传的文件的类型 
	
}
