function createAccountForm() {
    console.log('createAccountForm() CALLED!!');

    let form = document.create_account_form;
    if (form.u_m_id.value == '') {
        alert('아이디를 입력하세요.');
        form.u_m_id.focus();

    } else if (form.u_m_pw.value == '') {
        alert('비밀번호를 입력하세요.');
        form.u_m_pw.focus();

    }  else if (form.u_m_pw_again.value == '') {
        alert('비밀번호 확인란을 입력하세요.');
        form.u_m_pw_again.focus();

    } else if (form.u_m_name.value == '') {
        alert('이름을 입력하세요.');
        form.u_m_name.focus();

    }  else if (form.u_m_mail.value == '') {
        alert('메일을 입력하세요.');
        form.u_m_mail.focus();

    } else if (form.u_m_phone.value == '') {
        alert('연락처를 입력하세요.');
        form.u_m_phone.focus();

    }  else if (form.u_m_pw_again.value != form.u_m_pw.value) {
        alert('비밀번호가 일치하지 않습니다. \n다시 확인해주세요.');
        form.u_m_pw_again.focus();

    } else {
        form.submit();

    }

}