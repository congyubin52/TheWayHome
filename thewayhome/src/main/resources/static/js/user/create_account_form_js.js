function createAccountForm() {
    console.log('createAccountForm() CALLED!!');

    let form = document.create_account_form;
    if (form.u_m_id.value == '') {
        alert('INPUT ID');
        form.u_m_id.focus();

    } else if (form.u_m_pw.value == '') {
        alert('INPUT PW');
        form.u_m_pw.focus();

    } else if (form.u_m_name.value == '') {
        alert('INPUT NAME');
        form.u_m_name.focus();

    }  else if (form.u_m_mail.value == '') {
        alert('INPUT MAIL');
        form.u_m_mail.focus();

    } else if (form.u_m_phone.value == '') {
        alert('INPUT PHONE');
        form.u_m_phone.focus();

    } else {
        form.submit();

    }

}