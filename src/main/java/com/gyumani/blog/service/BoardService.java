package com.gyumani.blog.service;

import com.gyumani.blog.model.Board;
import com.gyumani.blog.model.User;
import com.gyumani.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void writeTxt(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> listTxt(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board viewTxt(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void deleteTxt(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateTxt(int id, Board requsetBoard){
        Board board=boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 수정 실패: 해당 아이디를 찾을 수 없습니다.");
                });
        board.setTitle(requsetBoard.getTitle());
        board.setContent(requsetBoard.getContent());
    }

}
