function reviewModifyConfirm(editor) {
    console.log('writeReviewConfirm() CALLED!!');

    let form = document.write_review_form;
    if (form.r_b_title.value == '') {
        alert('제목을 작성해주세요');
        form.b_title.focus();

    } else if (editor.getData() == undefined) {
        alert('본문을 작성해주세요');
        form.r_b_content.focus();

    } else {
        form.submit();

    }

}
function deleteReviewConfirm(r_b_no) {
    console.log('deleteReviewConfirm() CALLED!!');

    let rb_no = r_b_no;
    if (confirm("계정을 삭제하시겠습니까?")) {
        window.location.href = "/user/board/review_delete_confirm?r_b_no=" + rb_no;
    }

}

