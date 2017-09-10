package com.ray.util.fileupload;

import java.util.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017-8-18 下午4:45:05
 */
public class z_UploadUtil {

	private String[] fileTypes=null;//允许上传的文件类型们
    private String dstPath=null;//文件保存路径
    private long fileSizeMax=10*1024*1024;//允许上传的文件最大值
    private String cachePath=null;//缓存路径
    private int cacheSizeMax=5*1024*1024;//最大缓存容量
    private Map<String,String> textcontent;//上传表单中的文本内容用map来保存
    
    private ServletFileUpload sfupload;//实现上传功能的主要类
    private DiskFileItemFactory factory=new DiskFileItemFactory();//定义工厂来配置上传路径、缓冲区位置等
    
    public z_UploadUtil(){};
    
    public z_UploadUtil(String dst,String cache,String[] fileTypes,long fileSizeMax,int cacheSizeMax)
    {
        super();
        this.dstPath=dst;
        this.cachePath=cache;
        this.fileTypes=fileTypes;
        this.fileSizeMax=fileSizeMax;
        this.cacheSizeMax=cacheSizeMax;
    }
    
    public z_UploadUtil(String dst,String cache,String[] fileTypes)
    {
        super();
        this.dstPath=dst;
        this.cachePath=cache;
        this.fileTypes=fileTypes;
    }
    
    public void setCachePath(String cachePath){
        this.cachePath=cachePath;
    }
    public void setCacheSize(int size){
        this.cacheSizeMax=size;
    }
    public void setDstPath(String path){
        this.dstPath=path;
    }
    public void setFileSize(long size){
        this.fileSizeMax=size;
    }
    public void setFileType(String[] types){
        this.fileTypes=types;
    }
    
  //获取文件扩展名
    public String getFileExt(File file){
        String path=file.getName();
        return path.substring(path.lastIndexOf(".")+1);
    }
    //供外界调用获取上传表单中的文本内容
    public Map<String,String> getTextContent(){
        return textcontent;
    }
    //把提取到的文本内容们赋给类本身的map对象，以供外界取用
    public void setTextContent(Map<String,String> map){
        this.textcontent=map;
    }
    //单例模式获取文件上传类，如果不存在则创建，存在则赋给sfuload以免重复创建
    public ServletFileUpload getServletFileUpload(){
        if(sfupload==null){
            sfupload=new ServletFileUpload(factory);
            return sfupload;
        }else{
            return sfupload;
        }
    }
    //判断文件是否为合法的类型
    public boolean isFileValidate(File file){
        if(fileTypes==null){return true;}
        for(int i=0;i<fileTypes.length;++i){
            if(fileTypes[i].equals(getFileExt(file))){
                return true;
            }
        }
        return false;
    }
    //创建文件目录：用于后面根据路径来创建文件保存目录、缓存目录
    public void makeDir(String url){        
            File file=new File(url);
            if(!file.exists()){
                if(!file.mkdirs()){
                    System.out.println("fail to create dir!");
                }
            }
    }
    
  //上传方法
    public  void upload(HttpServletRequest request){
        
        try{
        if(!ServletFileUpload.isMultipartContent(request)){
            return;
        }
        makeDir(dstPath);//创建文件保存目录
        makeDir(cachePath);    //创建缓存目录
        factory.setRepository(new File(cachePath));//设置缓存路径
        factory.setSizeThreshold(cacheSizeMax);//设置缓存大小
        ServletFileUpload sfu=getServletFileUpload();//获取上传类    
        sfu.setFileSizeMax(fileSizeMax);//设置上传文件允许的最大值
        
    
        List<FileItem> items=sfu.parseRequest(request);//提取请求中附带的文件们
        Map<String,String> map =new HashMap<String,String>();//创建一个map对象来提取上传表单中的文本内容
        
        //迭代提取上传的文件们
        Iterator it=items.iterator();
        while(it.hasNext()){
            FileItem  fileItem=(FileItem)it.next();
            if(fileItem.isFormField()){//如果是文本内容，则提取出来放进map里
                map.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
            }else{//如果不是文本，则为文件
                String path=fileItem.getName();//获取文件名
                long size=fileItem.getSize();//获取文件大小
                if ("".equals(path) || size == 0) {//无效文件的判断
                System.out.println("not validate file");
                return;
                }
                
                File file=new File(dstPath,new File(fileItem.getName()).getName());//根据文件名在文件保存路径下创建一个同名文件
                if(!isFileValidate(file)){//判断文件类型是否合法
                System.out.println("file type incorrect!");
                }else{
                    fileItem.write(file);//文件合法，则通过IO流把上传文件写到文件路径下

                }
            }
            }
        setTextContent(map);//表单文本内容提取完毕，把map中的内容set给类中的textContent对象。
        }catch(Exception ex){
            System.out.println(ex);
        }        
    }
	
	
}
