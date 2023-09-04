function deleteFreeBoardConfirm(no) {
    console.log('deleteFreeBoardConfirm() CALLED!!');

    let fb_no = no;
    if (confirm("게시물을 삭제하시겠습니까?")) {
        window.location.href = "/user/board/free_board_delete_confirm?fb_no=" + fb_no;
    }

}