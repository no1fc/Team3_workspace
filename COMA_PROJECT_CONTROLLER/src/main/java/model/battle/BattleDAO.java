package model.battle;

import java.util.ArrayList;


public class BattleDAO {
	public boolean insert(BattleDTO replyDTO) {
		return true;
	}
	public boolean update(BattleDTO replyDTO) {
		return true;
	}
	public boolean delete(BattleDTO replyDTO) {
		return true;
	}

	public BattleDTO selectOne(BattleDTO replyDTO){
		System.out.println("reply.ReplyDAO.selectOne 시작");
		BattleDTO data = null;
		return data;
	}

	public ArrayList<BattleDTO> selectAll(BattleDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<BattleDTO> datas = new ArrayList<BattleDTO>();
		return datas;

	}
}
