package com.gyumani.blog.controller.api;

import com.gyumani.blog.config.auth.PrincipalDetail;
import com.gyumani.blog.dto.ReplySaveRequestDto;
import com.gyumani.blog.dto.ResponseDto;
import com.gyumani.blog.model.Board;
import com.gyumani.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail princiapl){
        boardService.writeTxt(board, princiapl.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.deleteTxt(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
        boardService.updateTxt(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //데이터를 받을때 컨트롤러에서 dto를 만들어서 받는게 좋다.
    //dto를 사용하지 않는 이유는!!
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){


        boardService.writeReply(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}
