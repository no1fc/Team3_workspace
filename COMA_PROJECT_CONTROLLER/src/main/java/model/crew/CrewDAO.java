package model.crew;

import java.util.ArrayList;

import model.reply.ReplyDTO;

public class CrewDAO {
	public boolean insert(CrewDTO replyDTO) {
		return true;
	}
	public boolean update(CrewDTO replyDTO) {
		return true;
	}
	public boolean delete(CrewDTO replyDTO) {
		return true;
	}

	public CrewDTO selectOne(CrewDTO replyDTO){
		System.out.println("reply.ReplyDAO.selectOne 시작");
		CrewDTO data = null;
		return data;
	}

	public ArrayList<CrewDTO> selectAll(CrewDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<CrewDTO> datas = new ArrayList<CrewDTO>();
		return datas;

	}
}
