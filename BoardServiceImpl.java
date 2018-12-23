package com.boe.demo.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boe.demo.mapper.mybit.BoardMapper;
import com.boe.demo.model.mybatis.Board;
import com.boe.demo.service.BoardService;

@Service
public class BoardServiceImpl  implements BoardService {
	@Autowired
	BoardMapper boardMp ;

	@Override
	public List<Board> selectAllBoard() throws Exception {
		return boardMp.selectAllBoard();
	}

	@Override
	public int insertBoard(Board board) throws Exception {
		return  boardMp.insertBoard(board);
	}

	@Override
	public Board selectBoardCol(int col) throws Exception {
		return  boardMp.selectBoardCol(col);
	}

	@Override
	public int updateBoardCol(Board board) throws Exception {
		return  boardMp.updateBoardCol(board);
	}

	@Override
	public int deleteBoard(Board board) throws Exception {
		return boardMp.deleteBoard(board);
	}
	
	
	

}
