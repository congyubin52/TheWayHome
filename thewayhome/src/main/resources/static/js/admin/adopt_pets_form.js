
function adoptPetsConfirm() {
    console.log('adoptPetsConfirm() CALLED!!');
    let s_name  = $('#s_name').text();
    // let s_name = document.getElementById("s_name").value;
    console.log(s_name);
        alert(`${s_name} 로 문의해주세요`);
        // location.href="/user/pets/adopt_pets_form";

}


/*function adoptPetsConfirm() {
    console.log('adoptPetsConfirm() CALLED!!');
    let s_name  = $('#s_name').text();
    let s_phone = $('#s_phone').text(); // 추가
    let s_address = $('#s_address').text(); // 추가
    let s_reg_date = $('#s_reg_date').text(); // 추가

    // 모달 내부에 정보를 채워 넣습니다.
    $('#modal_s_name').text(s_name);
    $('#modal_s_phone').text(s_phone); // 추가
    $('#modal_s_address').text(s_address); // 추가
    $('#modal_s_reg_date').text(s_reg_date); // 추가

    // 모달 열기
    $('#staticBackdrop').modal('show');
}*/
