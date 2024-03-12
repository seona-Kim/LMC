package com.lmc.movie.model.vo;

public class Movie {
	
	private int movieNo;			//	MOVIE_NO	NUMBER
	private String movieName;		//	MOVIE_NAME	VARCHAR2(255 BYTE)
	private String movieContent;	//	MOVIE_CONTENT	VARCHAR2(4000 BYTE)
	private String movieContent2;	//	MOVIE_CONTENT2	VARCHAR2(4000 BYTE)
	private String movieImg;		//	MOVIE_IMG	VARCHAR2(1000 BYTE)
	private String status;			//	STATUS	VARCHAR2(1 BYTE)
	
	// 생성자 부
	
	public Movie() {}

	
	public Movie(int movieNo, String movieName, String movieContent, String movieContent2, String movieImg,
			String status) {
		super();
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.movieContent = movieContent;
		this.movieContent2 = movieContent2;
		this.movieImg = movieImg;
		this.status = status;
	}
	
	
	
	public Movie(int movieNo, String movieName, String movieContent, String movieContent2, String movieImg) {
		super();
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.movieContent = movieContent;
		this.movieContent2 = movieContent2;
		this.movieImg = movieImg;
	}


	// 메소드 부

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieContent() {
		return movieContent;
	}

	public void setMovieContent(String movieContent) {
		this.movieContent = movieContent;
	}

	public String getMovieContent2() {
		return movieContent2;
	}

	public void setMovieContent2(String movieContent2) {
		this.movieContent2 = movieContent2;
	}

	public String getMovieImg() {
		return movieImg;
	}

	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Movie [movieNo=" + movieNo + ", movieName=" + movieName + ", movieContent=" + movieContent
				+ ", movieContent2=" + movieContent2 + ", movieImg=" + movieImg + ", status=" + status + "]";
	}
	
}
