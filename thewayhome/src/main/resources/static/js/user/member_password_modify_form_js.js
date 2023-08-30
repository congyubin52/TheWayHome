function memberPasswordModifyForm() {
    console.log('memberPasswordModifyForm() CALLED!!');

    let form = document.member_password_modify_form;
    if (form.u_m_pw.value == '') {
        alert('기존 비밀번호가 필수값 입니다.');
        form.u_m_pw.focus();

    } else if (form.u_m_Re_pw.value == '') {
        alert('변경 비밀번호가 필수값 입니다.');
        form.u_m_Re_pw.focus();

    } else if (form.u_m_Re_pw.value != form.u_m_Re_pw_confirm.value) {
        alert('변경 후 비밀번호 재입력이 틀렸습니다.');
        form.u_m_Re_pw_confirm.focus();

    }   else {
        form.submit();

    }

}