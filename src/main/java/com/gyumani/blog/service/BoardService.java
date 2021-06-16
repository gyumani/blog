package com.gyumani.blog.service;

import com.gyumani.blog.model.Board;
import com.gyumani.blog.model.User;
import com.gyumani.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
