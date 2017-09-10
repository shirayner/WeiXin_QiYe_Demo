package com.ray.pojo.message.send;  

  
/**
 * 图文消息
 * @author shirayner
 *
 */
public class NewsMessage extends BaseMessage {  
    //图文
	private News news;

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
    


}  