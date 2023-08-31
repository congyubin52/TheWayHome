function memberModifyForm() {
    console.log('memberModifyForm() CALLED!!');

    let form = document.member_modify_form;

    if (form.u_m_name.value == '') {
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