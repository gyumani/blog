package com.gyumani.blog.controller.api;

import com.gyumani.blog.config.auth.PrincipalDetail;
import com.gyumani.blog.dto.ResponseDto;
import com.gyumani.blog.model.Board;
import com.gyumani.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail princiapl){
        boardService.writeTxt(board, princiapl.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}
