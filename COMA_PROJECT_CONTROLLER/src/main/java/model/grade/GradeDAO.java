package model.grade;

import java.util.ArrayList;

import model.reply.ReplyDTO;

public class GradeDAO {
	public boolean insert(GradeDTO replyDTO) {
		return true;
	}
	public boolean update(GradeDTO replyDTO) {
		return true;
	}
	public boolean delete(GradeDTO replyDTO) {
		return true;
	}

	public GradeDTO selectOne(GradeDTO replyDTO){
		System.out.println("reply.ReplyDAO.selectOne 시작");
		GradeDTO data = null;
		return data;
	}

	public ArrayList<GradeDTO> selectAll(GradeDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<GradeDTO> datas = new ArrayList<GradeDTO>();
		return datas;

	}
}
