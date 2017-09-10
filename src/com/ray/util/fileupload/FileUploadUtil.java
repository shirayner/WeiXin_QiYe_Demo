package com.ray.util.fileupload;
import java.io.File;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
  
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
  
/** 
 * 文件上传工具类 
 *  
 * @Time 2012 - 7 - 23 
 * @author fule 
 *          
 *         fileType :限制的文件类型。 fileSize :限制的文件大小,默认5M,-1就是无限制大小 
 *          
 
 *          
 *         crateFilePolicy : 文件创建策略,为true的话，根据url没有路径一直创建,默认启用 。 fileOverPolicy 
 *         :文件覆盖策略,为true的话，相同文件不会覆盖将会改名字,默认不启用。 
 *         <p> 
 *         cachePathString :缓冲区文件夹 cacheSize缓冲区大小，默认5M。 
 *          
 
 */  
public class FileUploadUtil implements FileUploadModule {  
  
    // 限制的文件类型  
    private String[] fileType;  
  
    // 限制的文件大小,默认5M,-1就是无限制大小  
    private long fileSize = DEFAULT_SIZE;  
  
    // 设置文件创建策略,为true的话，根据url没有路径一直创建,默认启用  
    private boolean crateFilePolicy = ENABLE;  
  
    // 设置文件覆盖策略,为true的话，相同文件不会覆盖将会改名字,默认不启用  
    private boolean fileOverPolicy = DISABLE;  
  
    // 设置缓冲区文件夹  
    private String cachePathString;  
  
    // 设置缓冲区大小，默认5M  
    private long cacheSize = DEFAULT_SIZE;  
  
    // 文件上传处理类  
    private ServletFileUpload sfu;  
  
    // 磁盘工厂  
    private DiskFileItemFactory factory = new DiskFileItemFactory();  
  
    // 表单域的值  
    private Map<String, String> formMap;  
  
    public FileUploadUtil() {  
    }  
  
    public FileUploadUtil(String[] fileType, long fileSize,  
            boolean crateFilePolicy, boolean fileOverPolicy,  
            String cachePathString, long cacheSize) {  
        super();  
        this.fileType = fileType;  
        this.fileSize = fileSize;  
        this.crateFilePolicy = crateFilePolicy;  
        this.fileOverPolicy = fileOverPolicy;  
        this.cachePathString = cachePathString;  
        this.cacheSize = cacheSize;  
    }  
  
    public FileUploadUtil(String[] fileType, long fileSize,  
            boolean crateFilePolicy, boolean fileOverPolicy) {  
        super();  
        this.fileType = fileType;  
        this.fileSize = fileSize;  
        this.crateFilePolicy = crateFilePolicy;  
        this.fileOverPolicy = fileOverPolicy;  
    }  
  
    public FileUploadUtil(String[] fileType, long fileSize) {  
        super();  
        this.fileType = fileType;  
        this.fileSize = fileSize;  
    }  
  
    public Map<String, String> getFormMap() {  
        return formMap;  
    }  
  
    public void setFormMap(Map<String, String> formMap) {  
        this.formMap = formMap;  
    }  
  
    public String getCachePathString() {  
        return cachePathString;  
    }  
  
    public void setCachePathString(String cachePathString) {  
        this.cachePathString = cachePathString;  
    }  
  
    public long getCacheSize() {  
        return cacheSize;  
    }  
  
    public void setCacheSize(long cacheSize) {  
        this.cacheSize = cacheSize;  
    }  
  
    public boolean isFileOverPolicy() {  
        return fileOverPolicy;  
    }  
  
    public void setFileOverPolicy(boolean fileOverPolicy) {  
        this.fileOverPolicy = fileOverPolicy;  
    }  
  
    public boolean isCrateFilePolicy() {  
        return crateFilePolicy;  
    }  
  
    public void setCrateFilePolicy(boolean crateFilePolicy) {  
        this.crateFilePolicy = crateFilePolicy;  
    }  
  
    public long getFileSize() {  
        return fileSize;  
    }  
  
    public void setFileSize(long fileSize) {  
        this.fileSize = fileSize;  
    }  
  
    public void setFileType(String[] fileType) {  
  
        this.fileType = fileType;  
    }  
  
    public String[] getFileType() {  
        return fileType;  
    }  
  
    public ServletFileUpload getServletFileUpload() {  
        if (sfu == null) {  
  
            return sfu = new ServletFileUpload(factory);  
        } else {  
            return sfu;  
        }  
    }  
  
    /** 
     * // 得到文件类型 
     *  
     * @param file 
     * @return 
     */  
    public String getFileExt(File file) {  
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);  
    }  
  
    /** 
     * 检查文件类型 
     *  
     * @param fileType 
     *            需要文件类型 
     * @param file 
     *            传入的文件 
     * @return 是否为指定类型(true) 
     */  
    public boolean validateFileType(File file) {  
        if (fileType == null) {  
            return true;  
        }  
        for (int i = 0, len = fileType.length; i < len; i++) {  
            if (fileType[i].equals(getFileExt(file))) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 递归的根据路径创建文件夹 
     *  
     * @param url 
     * @return 
     * @throws Exception 
     */  
  
    public boolean createFolder(String url) throws Exception {  
        boolean boo = true;  
        // String path = url.substring(0, url.lastIndexOf("\\"));  
        File file = new File(url);  
        if (!file.exists()) {  
            if (!file.mkdirs()) {  
                boo = false;  
                throw new Exception("文件夹创建失败");  
            }  
        }  
        return boo;  
    }  
  
    /** 
     * 创建文件副本 
     *  
     * @param file 
     * @return 
     */  
    public File createCopyFile(File file) {  
        String name = file.getName().substring(0, file.getName().indexOf("."));  
        String basePath = file.getPath().substring(0, file.getPath().lastIndexOf("\\")+1);  
        if (name.lastIndexOf("1") != -1) {  
            String b = name.substring(0, name.lastIndexOf("1"));  
            String bString = name.substring(name.lastIndexOf("1"));  
            int a = Integer.valueOf(bString);  
            name = b + String.valueOf((a + 1));  
        } else {  
            name += "1";  
        }  
        return new File(basePath+name+"."+ getFileExt(file));  
    }  
  
    /** 
     * 文件上传 参数urlString是具体指定的目录 如果该对象属性值为空 
     * 将不使用缓存，无文件类型限制，上传大小默认为5M，目录规则默认为没有目录递归创建 相同文件名将覆盖源文件 
     * 此方法如文件上传错误或者文件类型不匹配将抛出异常 
     *  
     * @param request 
     *            当前请求 
     * @param urlString 
     *            urlString是具体指定的目录 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public void uploadFiles(HttpServletRequest request, String urlString)  
            throws Exception {  
        if (!ServletFileUpload.isMultipartContent(request))  
            return;  
        if (crateFilePolicy) {  
            createFolder(urlString);  
            createFolder(getCachePathString());  
        }  
        ServletFileUpload sfu = getServletFileUpload();  
        sfu.setFileSizeMax(fileSize);  
        if (cachePathString != null) {  
            factory.setRepository(new File(cachePathString));  
            factory.setSizeThreshold((int) cacheSize);  
        }  
        List<FileItem> items = sfu.parseRequest(request);  
        Map<String, String> map = new HashMap<String, String>();  
        if (!fileOverPolicy) {  
            for (FileItem ft : items) {  
                if (!ft.isFormField()) {  
                    File file = new File(urlString,  
                            new File(ft.getName()).getName());  
                    if (fileType != null) {  
                        if (validateFileType(file)) {  
                            ft.write(file);  
                        } else {  
                            throw new Exception("文件类型错误");  
                        }  
                    } else {  
                        ft.write(file);  
                    }  
                    ft.delete();  
                } else {  
                    map.put(ft.getFieldName(), ft.getString("utf-8"));  
                }  
            }  
        } else {  
            for (FileItem ft : items) {  
                if (!ft.isFormField()) {  
                    File file = new File(urlString,  
                            new File(ft.getName()).getName());  
                    if (fileType != null) {  
                        if (validateFileType(file)) {  
                            ft.write(createCopyFile(file));  
                        } else {  
                            throw new Exception("文件类型错误");  
                        }  
                    } else {  
                        ft.write(createCopyFile(file));  
  
                    }  
                    ft.delete();  
                } else {  
                    map.put(ft.getFieldName(), ft.getString("utf-8"));  
                }  
            }  
        }  
        setFormMap(map);//设置表单域的值  
    }  
}  