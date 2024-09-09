package model.battle_record;

import java.util.ArrayList;

import model.reply.ReplyDTO;

public class Battle_recordDAO {
	public boolean insert(Battle_recordDTO replyDTO) {
		return true;
	}
	public boolean update(Battle_recordDTO replyDTO) {
		return true;
	}
	public boolean delete(Battle_recordDTO replyDTO) {
		return true;
	}

	public Battle_recordDTO selectOne(Battle_recordDTO replyDTO){
		System.out.println("reply.ReplyDAO.selectOne 시작");
		Battle_recordDTO data = null;
		return data;
	}

	public ArrayList<Battle_recordDTO> selectAll(Battle_recordDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<Battle_recordDTO> datas = new ArrayList<Battle_recordDTO>();
		return datas;

	}
}
