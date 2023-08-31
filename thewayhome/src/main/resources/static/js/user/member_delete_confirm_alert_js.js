function AlertDeleteConfirm() {
    console.log('AlertDeleteConfirm() CALLED!!');
    if (confirm("계정을 삭제하시겠습니까?")) {
        window.location.href = "/user/member/member_delete_confirm";
    }


}