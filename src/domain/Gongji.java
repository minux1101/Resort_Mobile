package domain;

public class Gongji {
	private int id;  //공지의 id
	private String title;  //공지의 제목
	private String date;  //공지를 쓴 날짜
	private String content;  //공지의 내용
	
	public Gongji(String title, String content) { //공지의 생성자. 제목과, 내용이 필요
		this.title = title;
		this.content = content;
	}

	public Gongji(int id, String title, String date, String content) { //공지의 생성자. id와 제목, 날짜, 내용이 필요
		this.id = id;
		this.title = title;
		this.date = date;
		this.content = content;
	}
	
	public int getId() { //id의 getter
		return id;
	}
	public void setId(int id) {   //id의 setter
		this.id = id;
	}
	public String getTitle() {   //제목의 getter
		return title;
	}
	public void setTitle(String title) {  //제목의 setter
		this.title = title;
	}
	public String getDate() {  //날짜의 getter
		return date;
	}
	public void setDate(String date) {  //날짜의 setter
		this.date = date;
	}
	public String getContent() {   //내용의 getter
		return content;
	}
	public void setContent(String content) {   //내용의 setter
		this.content = content;
	}	
}
