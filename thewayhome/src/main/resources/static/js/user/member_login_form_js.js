function memberLoginForm() {
    console.log('memberLoginForm() CALLED!!');

    let form = document.member_login_form;
    if (form.u_m_id.value == '') {
        alert('INPUT ID');
        form.u_m_id.focus();

    } else if (form.u_m_pw.value == '') {
        alert('INPUT PW');
        form.u_m_pw.focus();

    } else {
        form.submit();

    }

}