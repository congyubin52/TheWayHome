// 실종/목격 게시판 - 삭제 확인 START
function deleteFreeBoardConfirm(no) {
    console.log('deleteFreeBoardConfirm() CALLED!!');

    let fb_no = no;
    if (confirm("게시물을 삭제하시겠습니까?")) {
        window.location.href = "/user/board/free_board_delete_confirm?fb_no=" + fb_no;
    }

}
// 실종/목격 게시판 - 삭제 확인 END

// 실종/목격 게시판 - 작성 START
$(document).ready(function(){
    console.log('ready!!');

});

function writePost(editor) {
    console.log('freeBoardWrite() CALLED!!');

    let form = document.free_board_write_confirm;

    if (form.fb_title.value == '') {
        alert('INPUT title');
        form.fb_title.focus();

    } else if (editor.getData() == '') {
        alert('INPUT content');
        form.fb_content.focus();

    } else if(form.fb_category.value == "0") {
        alert("실종/목격을 선택해야 합니다.");
        form.b_category.focus();
    } else {
        form.submit();

    }



}
// 실종/목격 게시판 - 작성 END