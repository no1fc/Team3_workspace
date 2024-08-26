package model.board;

public class BoardDTO {
   int board_id;           // 게시판 글 번호
   String board_title;     // 게시판제목
   String board_content;   // 글 내용
   int board_cnt;          // 조회수
   String board_location;  // 게시글의 위치 지역
   String writer;         // 작성자
   String serchKeyword;    // 사용자 텍스트 기반 검색 
   String condition;       // 개발자 데이터 검색 조건 지역, 작성자, 날짜 범위 기능이면 필요
   
   public int getBoard_id() {
      return board_id;
   }
   public void setBoard_id(int board_id) {
      this.board_id = board_id;
   }
   public String getBoard_title() {
      return board_title;
   }
   public void setBoard_title(String board_title) {
      this.board_title = board_title;
   }
   public String getBoard_content() {
      return board_content;
   }
   public void setBoard_content(String board_content) {
      this.board_content = board_content;
   }
   public int getBoard_cnt() {
      return board_cnt;
   }
   public void setBoard_cnt(int board_cnt) {
      this.board_cnt = board_cnt;
   }
   public String getBoard_location() {
      return board_location;
   }
   public void setBoard_location(String board_location) {
      this.board_location = board_location;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public String getSerchKeyword() {
      return serchKeyword;
   }
   public void setSerchKeyword(String serchKeyword) {
      this.serchKeyword = serchKeyword;
   }
   public String getCondition() {
      return condition;
   }
   public void setCondition(String condition) {
      this.condition = condition;
   }
   @Override
   public String toString() {
      return "BoardDTO [board_id=" + board_id + ", board_title=" + board_title + ", board_content=" + board_content
            + ", board_cnt=" + board_cnt + ", board_location=" + board_location + ", writer=" + writer
            + ", serchKeyword=" + serchKeyword + ", condition=" + condition + "]";
   }
   
}
