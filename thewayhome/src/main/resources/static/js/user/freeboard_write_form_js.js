$(document).ready(function(){
    console.log('ready!!');

});

function writePost(editor) {
    console.log('freeBoardWrite() CALLED!!');

    let form = document.free_board_write_confirm;

    if (form.b_title.value == '') {
        alert('INPUT title');
        form.b_title.focus();

    } else if (editor.getData() == '') {
        alert('INPUT content');
        form.b_content.focus();

    } else if(form.b_category.value == "0") {
        alert("실종/목격을 선택해야 합니다.");
        form.b_category.focus();
    } else {
        form.submit();

    }



}