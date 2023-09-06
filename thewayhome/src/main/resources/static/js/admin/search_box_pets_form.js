function searchBoxPetConfirm() {
    console.log('searchBoxPetConfirm() CALLED!!');

    /*let form = document.search_box_pets_form;
    if (form.an_k_kind.value == '') {
        alert('검색어를 입력해주세요.');
        location.href= "/user/pets/all_pets_list";
        form.an_k_kind.focus();

    } else {
        form.submit();

    }*/

    var searchOption = document.getElementById("search_option").value;
    var sNameInput = document.getElementById("search").value;
    var s_no = document.getElementById("s_no").value;

    location.href= "/user/pets/pets_list?s_no="+s_no+"&searchOption="+searchOption+"&sNameInput="+sNameInput;

}