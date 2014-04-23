package cn.test.model;

/**
 * 
 * @ClassName: User.java
 * @Package cn.api.support.model
 * @Description: 用户实体类
 * @author admin gjrongg@gmail.com
 * @date 2014年2月21日 下午5:42:02
 * @version V1.0
 */
public class User {
	private String Name;
	private Integer uid;
	private String nick;
	private String head;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

}
