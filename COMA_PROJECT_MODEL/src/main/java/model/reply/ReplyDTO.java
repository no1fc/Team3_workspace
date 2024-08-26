package model.reply;

public class ReplyDTO {
	int reply_id;           //댓글 ID
	String reply_content;   //댓글 내용
	String reply_member_id; //댓글 작성자
	int reply_board_id;     //댓글이 속한 게시판 ID
	String condition;       //개발자 데이터
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_member_id() {
		return reply_member_id;
	}
	public void setReply_member_id(String reply_member_id) {
		this.reply_member_id = reply_member_id;
	}
	public int getReply_board_id() {
		return reply_board_id;
	}
	public void setReply_board_id(int reply_board_id) {
		this.reply_board_id = reply_board_id;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		return "ReplyDTO [reply_id=" + reply_id + ", reply_content=" + reply_content + ", reply_member_id="
				+ reply_member_id + ", reply_board_id=" + reply_board_id + ", condition=" + condition + "]";
	}

}
