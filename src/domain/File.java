package domain;

public class File { 
	
	private int id; //file의 번호
	private String fileName; // 사용자가 입력한 파일 이름
	private String fileRealName; //시스템이 입력되는 파일이름
	private int rootid; // 해당 파일의 공지번호

	public File(int id, String fileName, String fileRealName, int rootid) { //파일 생성자
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.rootid = rootid;
	}

	public int getId() {  //번호의 getter
		return id;
	}
	
	public void setId(int id) { //번호의 setter
		this.id = id;
	}
	
	public String getFileName() { //사용자가 정한 파일 이름의 getter
		return fileName;
	}
	
	public void setFileName(String fileName) { //사용자가 정한 파일 이름의 setter
		this.fileName = fileName;
	}
	
	public String getFileRealName() { //시스템에 저장된 파일 이름의 getter
		return fileRealName;
	}
	
	public void setFileRealName(String fileRealName) { //시스템에 저장된 파일 이름의 setter
		this.fileRealName = fileRealName;
	}
	
	public int getRootid() { // 공지번호 getter
		return rootid;
	}
	
	public void setRootid(int rootid) { // 공지번호 setter
		this.rootid = rootid;
	}

}
