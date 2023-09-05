function searchBoxShelterForm() {
    console.log('searchBoxShelterForm() CALLED!!');

    let form = document.search_box_shelter_form;
    if (form.s_name.value == '') {
        alert('검색어를 입력해주세요.');
        form.s_name.focus();

    } else {
        form.submit();

    }

}