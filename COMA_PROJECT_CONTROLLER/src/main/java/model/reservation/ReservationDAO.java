package model.reservation;

import java.util.ArrayList;

import model.reply.ReplyDTO;

public class ReservationDAO {
	public boolean insert(ReservationDTO replyDTO) {
		return true;
	}
	public boolean update(ReservationDTO replyDTO) {
		return true;
	}
	public boolean delete(ReservationDTO replyDTO) {
		return true;
	}

	public ReservationDTO selectOne(ReservationDTO replyDTO){
		System.out.println("reply.ReplyDAO.selectOne 시작");
		ReservationDTO data = null;
		return data;
	}

	public ArrayList<ReservationDTO> selectAll(ReservationDTO replyDTO){
		System.out.println("reply.ReplayDAO.selectAll 시작");
		ArrayList<ReservationDTO> datas = new ArrayList<ReservationDTO>();
		return datas;

	}
}
