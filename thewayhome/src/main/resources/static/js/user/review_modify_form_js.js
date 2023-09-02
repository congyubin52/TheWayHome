function reviewModifyConfirm(editor) {
    console.log('writeReviewConfirm() CALLED!!');

    let form = document.write_review_form;
    if (form.b_title.value == '') {
        alert('제목을 작성해주세요');
        form.b_title.focus();

    } else if (editor.getData() == undefined) {
        alert('본문을 작성해주세요');
        form.b_content.focus();

    } else {
        form.submit();

    }

}