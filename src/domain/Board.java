package domain;

public class Board {
	private int id;  //공지 번호
	private String title;  //공지의 제목
	private String date;  //공지를 쓴 날짜
	private String content; //공지의 내용
	private int rootid; //공지의 원글 번호
	private int relevel; //공지의 댓글 수준
	private int recnt;  //댓글 우선 순위
	private int viewcnt; //조회수
	
	public Board() { //공지의 기본 생성자
		
	}

	//공지의 생성자. id와 제목, 날짜, 내용, 원글 번호, 댓글 우선순위, 조회수가 필요
	public Board(int id, String title, String date, String content, int rootid, int relevel, int recnt, int viewcnt) {  
		this.id = id;
		this.title = title;
		this.date = date;
		this.content = content;
		this.rootid = rootid;
		this.relevel = relevel;
		this.recnt = recnt;
		this.viewcnt = viewcnt;
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
	
	public int getRootid() {  //원글의 getter
		return rootid;
	}
	public void setRootid(int rootid) { //내용의 setter
		this.rootid = rootid;
	}
	public int getRelevel() { //댓글 수준 getter
		return relevel;
	}
	public void setRelevel(int relevel) { //댓글 수준 setter
		this.relevel = relevel;
	}
	public int getRecnt() {  //댓글 우선순위 getter
		return recnt;
	}
	public void setRecnt(int recnt) { //댓글 우선순위 setter
		this.recnt = recnt;
	}
	public int getViewcnt() { //조회수 getter
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) { //조회수 setter
		this.viewcnt = viewcnt;
	}
		
}
