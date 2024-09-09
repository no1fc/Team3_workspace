package model.favorite;

import java.util.ArrayList;

import model.reply.ReplyDTO;

public class FavoriteDAO {
	public boolean insert(FavoriteDTO replyDTO) {
		return true;
	}
	public boolean update(FavoriteDTO replyDTO) {
		return true;
	}
	public boolean delete(FavoriteDTO replyDTO) {
		return true;
	}

	public FavoriteDTO selectOne(FavoriteDTO replyDTO){
		System.out.println("reply.ReplyDAO.selectOne 시작");
		FavoriteDTO data = null;
		return data;
	}

	public ArrayList<FavoriteDTO> selectAll(FavoriteDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<FavoriteDTO> datas = new ArrayList<FavoriteDTO>();
		return datas;

	}
}
