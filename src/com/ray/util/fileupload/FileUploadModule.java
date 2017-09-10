package com.ray.util.fileupload;
import java.io.File;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
  
/** 
 * 文件上传通用接口 
 *  
 * fileType :限制的文件类型。 fileSize :限制的文件大小,默认5M,-1就是无限制大小 
 *  
 
 *  
 * crateFilePolicy : 文件创建策略,为true的话，根据url没有路径一直创建,默认启用 。 fileOverPolicy 
 * :文件覆盖策略,为true的话，相同文件不会覆盖将会改名字,默认不启用。 
 * <p> 
 * cachePathString :缓冲区文件夹 cacheSize缓冲区大小，默认5M。 
 *  
 
 *  
 * @author fule 
 * @Time 2012 - 7 - 23 
 *  
 */  
public interface FileUploadModule {  
    /** 
     * 一兆的大小 
     */  
    long TRILLION = 1024 * 1024;  
    /** 
     * 文件限制性的大小(默认的5M) 
     */  
    long DEFAULT_SIZE = TRILLION * 5;  
    /** 
     * 启用 
     */  
    boolean ENABLE = true;  
    /** 
     * 不启用 
     */  
    boolean DISABLE = false;  
  
    /** 
     * 设置文件上传的类型 
     *  
     * @param type 
     */  
    void setFileType(String[] type);  
  
    /** 
     * 设置缓存区磁盘位置,如果文件比较大,建议设置缓冲 
     *  
     * @param cachePathString 
     */  
    void setCachePathString(String cachePathString);  
	public void setFileOverPolicy(boolean b );  
    /** 
     * 设置缓存区大小，默认的为5M 
     *  
     * @param cacheSize 
     */  
    void setCacheSize(long cacheSize);  
  
      
    public void setFileSize(long fileSize);  
  
    /** 
     * 得到文件类型 
     *  
     * @param file 
     * @return 
     */  
    public String getFileExt(File file);  
  
    /** 
     * 文件类型检查 当fileType为空将返回true 可通过setFileType()设置文件类型 
     *  
     * @see FileUploadUtil 的 fileType属性 
     * @param file 
     *            传入的文件 
     * @return 是否为指定类型(true) 
     */  
    public boolean validateFileType(File file);  
  
    /** 
     * 递归的根据路径创建文件夹 
     *  
     * @param url 
     * @return 
     * @throws Exception 
     */  
  
    public boolean createFolder(String url) throws Exception;  
  
    /** 
     * 创建文件副本 
     *  
     * @param file 
     * @return 
     */  
    public File createCopyFile(File file);  
  
    /** 
     * 文件上传 参数urlString是具体指定的目录 
     *  
     * 如果该对象属性值为空 ,将不使用缓存，无文件类型限制，上传大小默认为5M，目录规则默认为没有目录递归创建,相同文件名将覆盖源文件 
     * 如需改变通过设置属性来改变 
     *  
 
     * 此方法如文件上传错误或者文件类型不匹配将抛出异常 
     *  
     * @param request 
     *            当前请求 
     * @param urlString 
     *            urlString是具体指定的目录 
     * @throws Exception 
     */  
    public void uploadFiles(HttpServletRequest request, String urlString)  
            throws Exception;  
  
    /** 
     * 获得普通表单域的值 
     * @return 
     */  
    public Map<String, String> getFormMap();

	/**@desc ：
	 *  
	 * @param enable2 void 
	 */

}  