package model.gym;

import java.util.ArrayList;

import model.reply.ReplyDTO;

public class GymDAO {
	public boolean insert(GymDTO replyDTO) {
		return true;
	}
	public boolean update(GymDTO replyDTO) {
		return true;
	}
	public boolean delete(GymDTO replyDTO) {
		return true;
	}

	public GymDTO selectOne(GymDTO replyDTO){
		System.out.println("reply.ReplyDAO.selectOne 시작");
		GymDTO data = null;
		return data;
	}

	public ArrayList<GymDTO> selectAll(GymDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<GymDTO> datas = new ArrayList<GymDTO>();
		return datas;

	}
}
