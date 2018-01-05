package zou.common.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 分页通用查询
 * @author zou
 *
 * @param <E>
 */
@Getter@Setter
public class Page<E>{
    /**
     *  传递的参数或配置的值  
     */
	/**
	 *  当前页
	 */
    private int currentPage;
    /**
     * 每页显示数据
     */
    private int pageSize; 
  
    // 查询数据库  
    /**
     * 从数据库查出总记录数
     */
    private Long recordCount; // 总记录数  
    private List<E> recordList; // 本页的数据列表  
  
    // 计算出来的  
    private int pageCount; // 总页数  
    private int beginPageIndex; // 页面列表的开始索引  
    private int endPageIndex; // 页面列表的结束索引  
	public Page(int currentPage, int pageSize, Long recordCount, List<E> recordList) {

		pageCount = (int) ((recordCount-1) /pageSize + 1); 
		
		// 当前页 * 每页显示的数据
		this.beginPageIndex = currentPage*pageSize;
		
		this.endPageIndex = beginPageIndex + pageSize;
		
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
	
	}
	
	
	public static void main(String[] args) {
		System.out.println((1-1)*5);
	}
	 
	 
}
