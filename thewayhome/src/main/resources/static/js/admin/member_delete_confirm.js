function deleteConfirm() {
    console.log('deleteConfirm() CALLED!!');

        var result = confirm("회원 탈퇴를 하시겠습니까?");
        if(result) {
            alert("탈퇴 처리되었습니다.");
        } else {
            alert("탈퇴 취소되었습니다.");
        }

}